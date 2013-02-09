package com.bac.oee.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;

// TODO: Auto-generated Javadoc
/**
 * Adds a session attribute to Log4J's mapped diagnositic context so log events
 * can be associated with a unique user on the site.
 * 
 * @author christopher.tai@starcomworldwide.com
 */
public class LoggingFilter implements Filter {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(LoggingFilter.class);

	/** The filter config. */
	protected FilterConfig filterConfig = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("initializing...");
		this.filterConfig = filterConfig;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		if (session != null) {
			MDC.put("session", session.getId());
		}
		chain.doFilter(request, response);
		if (session != null) {
			MDC.remove("session");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		log.info("destroying...");
		this.filterConfig = null;
	}

}