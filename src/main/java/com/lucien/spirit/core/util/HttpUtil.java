package com.lucien.spirit.core.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Filename : HttpUtil.java
 * @Package : com.lucien.spirit.core.util
 * @Description : TODO
 * @author : lijunf
 * @CreateDate : 2016年2月14日
 */
public class HttpUtil {

    /**
     * 获取HttpServletRequest对象
     * @return
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }
    
    /**
     * 获得客户端真实IP地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) { 
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip; 
    } 
}
