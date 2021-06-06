package com.tsingtec.follow.utils;

import com.tsingtec.follow.entity.sys.Admin;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http上下文
 *
 */
public class HttpContextUtils {

    public static Admin getAdmin() {
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        return admin;
    }

	public static Integer getAid() {
		return getAdmin().getId();
	}

	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static HttpServletResponse getHttpServletResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	public static boolean isAjaxRequest(HttpServletRequest request){
		String accept = request.getHeader("accept");
		String xRequestedWith = request.getHeader("X-Requested-With");
		return ((accept != null && accept.indexOf("application/json") != -1
				|| (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1)
		));
	}

	public static String getToken(){
		return getHttpServletRequest().getHeader("token");
	}
}
