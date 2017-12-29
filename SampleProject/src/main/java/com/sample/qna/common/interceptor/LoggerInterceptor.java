package com.sample.qna.common.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sample.qna.model.conf.SESSION_CONF;

public class LoggerInterceptor extends HandlerInterceptorAdapter {
	
	protected Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("======================================          START         ======================================");
			log.debug(" Request URI \t:  " + request.getRequestURI());
			
			Enumeration<String> headerNames = request.getHeaderNames();
			while(headerNames.hasMoreElements()) {
				String name = (String)headerNames.nextElement();
				String value = request.getHeader(name);
				log.debug(name + " : " + value);
			}
			
			Enumeration<String> paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String key = (String) paramNames.nextElement();
				String value = request.getParameter(key);
				log.debug(" RequestParameter Data ==>  " + key + " : " + value + "");
			}
			request.setAttribute(SESSION_CONF.REQUEST_URI, request.getRequestURI());
			request.setAttribute(SESSION_CONF.CONTEXT_PATH, request.getContextPath());
			log.debug(SESSION_CONF.CONTEXT_PATH + " \t:  ", request.getContextPath());
			log.debug("======================================           END          ======================================\n");
		}
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (log.isDebugEnabled()) {
			
		}
	}
	
}
