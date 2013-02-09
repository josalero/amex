/**
 * 
 */
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
 * The Class PageDataFilter.
 * 
 * @author jose aleman
 */
public class PageDataFilter implements Filter {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(PageDataFilter.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		String jspPath = req.getRequestURI();

		/*
		 * if (jspPath.contains("subpage.jsp")){
		 * 
		 * Object pageData = request.getAttribute(ViewConstants.PAGE_DATA);
		 * 
		 * if (pageData == null){ String url = req.getHeader("REFERER");
		 * 
		 * log.warn("PageData should not be null"); log.warn("[JSP] --> [" +
		 * jspPath + "]"); String urlToRedirect = "/error500.jsp" ;
		 * req.getRequestDispatcher(urlToRedirect).forward(request, response);
		 * 
		 * return;
		 * 
		 * } }
		 */

		log.debug("Filtering ... ");
		chain.doFilter(request, response);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
