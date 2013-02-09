package com.bac.oee.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// TODO: Auto-generated Javadoc
/**
 * Saves the URI from the current <object>ServletRequest</object> and saves it
 * under the key <code>URI_KEY</code>. This is done to get around problems
 * across servlet containers and their inconsistant treatment of
 * request.getRequestURI() when handling internal forwards.
 * 
 * @author christopher.tai@starcomworldwide.com
 */
public class RequestUriCaptureFilter implements Filter {

	/** The Constant log. */
	private static final Log log = LogFactory
			.getLog(RequestUriCaptureFilter.class);

	/** The Constant URI_KEY. */
	public static final String URI_KEY = "com.flag.oee.web.filter.RequestUriCaptureFilter.URI_KEY";

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
		String uri = ((HttpServletRequest) request).getRequestURI();
		String queryString = ((HttpServletRequest) request).getQueryString();
		if (queryString != null && queryString.length() > 0) {
			uri += "?" + queryString;
		}
		request.setAttribute(URI_KEY, uri);
		chain.doFilter(request, response);
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