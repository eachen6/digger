package com.digger.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by geely
 */
public class Const {
	
	public static final String FTP_SERVER_IP = "10.71.98.95";//ftp服务器ip地址
    public static final String FTP_USERNAME = "digger";//ftp服务器用户名
    public static final String FTP_PASSWORD = "123";//ftp服务器密码
    public static final String FTP_PREFIX = "ftp://10.71.98.95";//ftp服务器资源访问前缀
    public static final String HTTP_PREFIX = "http://10.71.98.95";//ftp服务器资源访问前缀
    public static final String DOWN_INTERFACE = "http://localhost:8080/digger/?";//下载文档接口

    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    
    public static final int gamecount = 5; //每页所显示的游戏数
    public static final int announcementcount = 2; //每页所显示的公告数
    public static final int pagecount = 3; //显示页码的个数
    
    public static final String IP = "ip";   //记录电脑ip
    
    public static final String subject = "游戏消费";   //记录消费商品描述,统一叫"游戏消费"
    
    //下面是支付方式，0是支付宝，1是微信
    public static final byte PAYWAY_ALI = 0;
    public static final byte PAYWAY_WX = 1;
    
    //下面是订单支付状态
    public static final int WAIT_PAY = 0;   //等待付款
    public static final int PAID = 1;       //已付款

    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
    }

    public interface Cart{
        int CHECKED = 1;//即购物车选中状态
        int UN_CHECKED = 0;//购物车中未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }

    public interface Role{
        Byte ROLE_CUSTOMER = 0; //普通用户
        Byte ROLE_ADMIN = 1;//管理员
        Byte ROLE_SERVICE = 2; //客服
    }

    public enum ProductStatusEnum{
        ON_SALE(1,"在线");
        private String value;
        private int code;
        ProductStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }


    public enum OrderStatusEnum{
        CANCELED(0,"已取消"),
        NO_PAY(10,"未支付"),
        PAID(20,"已付款"),
        SHIPPED(40,"已发货"),
        ORDER_SUCCESS(50,"订单完成"),
        ORDER_CLOSE(60,"订单关闭");


        OrderStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static OrderStatusEnum codeOf(int code){
            for(OrderStatusEnum orderStatusEnum : values()){
                if(orderStatusEnum.getCode() == code){
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }
    }
    public interface  AlipayCallback{
        String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";

        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";
    }



    public enum PayPlatformEnum{
        ALIPAY(1,"支付宝");

        PayPlatformEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    public enum PaymentTypeEnum{
        ONLINE_PAY(1,"在线支付");

        PaymentTypeEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }


        public static PaymentTypeEnum codeOf(int code){
            for(PaymentTypeEnum paymentTypeEnum : values()){
                if(paymentTypeEnum.getCode() == code){
                    return paymentTypeEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }

    }




}
