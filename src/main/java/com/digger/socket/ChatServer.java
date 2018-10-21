﻿package com.digger.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.digger.common.Const;
import com.digger.pojo.Friend;
import com.digger.pojo.User;
import com.digger.service.FriendService;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * websocket服务
 * @author  :  Amayadream
 * @time   :  2016.01.08 09:50
 */
@ServerEndpoint(value = "/chatServer", configurator = HttpSessionConfigurator.class)
public class ChatServer {
    private static int onlineCount = 0; //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static CopyOnWriteArraySet<ChatServer> webSocketSet = new CopyOnWriteArraySet<ChatServer>();
    private Session session;    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private User user;      //用户
    private HttpSession httpSession;    //request的session

    private static List<String> list = new ArrayList<String>();   //在线列表,记录用户名称
    private static Map routetab = new HashMap<>();  //用户名和websocket的session绑定的路由表

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1;
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.user=(User) httpSession.getAttribute(Const.CURRENT_USER);    //获取当前用户
        list.add(user.getUsername());           //将用户名加入在线列表
        //list = this.getFriends(user.getId());
        System.out.println(list);
        routetab.put(user.getUsername(), session);   //将用户名和session绑定到路由表
        //String message = getMessage("[" + user.getUsername() + "]加入聊天室,当前在线人数为"+getOnlineCount()+"位", "notice",  list);
        //broadcast(message);     //广播
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        list.remove(user.getUsername());        //从在线列表移除这个用户
        routetab.remove(user.getUsername());
        String message = getMessage("[" + user.getUsername() +"]离开了聊天室,当前在线人数为"+getOnlineCount()+"位", "notice", list);
        broadcast(message);         //广播
    }

    /**
     * 接收客户端的message,判断是否有接收人而选择进行广播还是指定发送
     * @param _message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String _message) {
        JSONObject chat = JSON.parseObject(_message);
        JSONObject message = JSON.parseObject(chat.get("message").toString());
        if(message.get("to") == null || message.get("to").equals("")){      //如果to为空,则广播;如果不为空,则对指定的用户发送消息
        	String broad_message = getMessage("信息未送达，请选择好友", "notice",  list);
        	//broadcast(broad_message);
        	singleSend(broad_message, (Session) routetab.get(message.get("from")));      //发送给自己
        	System.out.println(_message);
        }else{
            String [] userlist = message.get("to").toString().split(",");
            System.out.println("发送给，"+message.get("to").toString());
            singleSend(_message, (Session) routetab.get(message.get("from")));      //发送给自己,这个别忘了
            for(String username : userlist){
                if(!user.equals(message.get("from"))){
                	if(routetab.get(username)!=null) {
                    singleSend(_message, (Session) routetab.get(username));     //分别发送给每个指定用户
                	}
                	else {
                		//String temp_message = "{\"message\":{\"content\":\"好友不在线，稍后再来\",\"from\":\"123\",\"to\":\"\",\"time\":\"2018-10-17 16:23:55\"},\"type\":\"notice\"}";
                		String temp_message = getMessage("好友不在线，稍后再来", "notice",  list);
                		singleSend(temp_message, (Session) routetab.get(message.get("from"))); 
                	}
                }
            }
        }
    }

    /**
     * 发生错误时调用
     * @param error
     */
    @OnError
    public void onError(Throwable error){
        error.printStackTrace();
    }

    /**
     * 广播消息
     * @param message
     */
    public void broadcast(String message){
        for(ChatServer chat: webSocketSet){
            try {
                chat.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 对特定用户发送消息
     * @param message
     * @param session
     */
    public void singleSend(String message, Session session){
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 组装返回给前台的消息
     * @param message   交互信息
     * @param type      信息类型
     * @param list      在线列表
     * @return
     */
    public String getMessage(String message, String type, List list){
        JSONObject member = new JSONObject();
        member.put("message", message);
        member.put("type", type);
        member.put("list", list);
        return member.toString();
    }

    public  int getOnlineCount() {
        return onlineCount;
    }

    public  void addOnlineCount() {
        ChatServer.onlineCount++;
    }

    public  void subOnlineCount() {
        ChatServer.onlineCount--;
    }
    
    
}
