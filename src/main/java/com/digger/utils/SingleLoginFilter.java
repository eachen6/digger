package com.digger.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;

public class SingleLoginFilter implements Filter {
    private static final Log log = LogFactory.getLog(SingleLoginFilter.class);
    public static Map<String, String> optionMap = new HashMap<String, String>();


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)request).getSession();
        Object sUser = session.getAttribute("user");
        if (sUser==null) {//用户为空不拦截
            filterChain.doFilter(request, response);
            return;
        }
        JSONObject jUser = (JSONObject)sUser;
        String userId = jUser.getString("userId");
        //查看session中是否存在user的登录信息
        String sessionId = getRequestSessionId((HttpServletRequest)request);
        if(sessionId!=null&&sessionId.equals(optionMap.get(userId))) {
            filterChain.doFilter(request, response);
        } else {
            //页面提示登录失效或您的账号已在其它地点登录
            log.info("用户账号 "+userId+" 被强制挤下线,sessionId为:"+sessionId);
            ((HttpServletResponse) response).setHeader("content-type", "text/html;charset=UTF-8");
//            response.getWriter().write("{\"status\":10000,\"message\":\"您的账号已失效,或在其它地点登录\"}");
              session.removeAttribute("user");
              response.getWriter().write("<script>alert('您的账号已在其它地点登录');window.location.href='login.html'</script>");
        }
    }

    public static String getRequestSessionId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName()!=null&&cookie.getName().startsWith("JSESSIONID")) {
                return cookie.getValue();
            }
        }
        return null;
    }
    public void destroy() {
    }


    public void init(FilterConfig arg0) throws ServletException {
    }

	@Override
	public boolean isLoggable(LogRecord record) {
		// TODO Auto-generated method stub
		return false;
	}


}
