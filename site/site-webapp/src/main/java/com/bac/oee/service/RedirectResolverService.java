/**
 * 
 */
package com.bac.oee.service;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.bac.oee.util.MobileUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class RedirectResolverService.
 * 
 * @author Jose Aleman
 */
@Service
public class RedirectResolverService {

	/** The resolve302 map. */
	@Resource
	private Map<String, String> resolve302Map;

	/** The Constant MOBILE. */
	private static final String MOBILE = "MOBILE";

	/** The Constant DESKTOP. */
	private static final String DESKTOP = "DESKTOP";

	/**
	 * Instantiates a new redirect resolver service.
	 */
	public RedirectResolverService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Resolve302 redirects.
	 * 
	 * @param httpServletRequest
	 *            the http servlet request
	 * @param httpServletResponse
	 *            the http servlet response
	 * @param pageLocalePrefix
	 *            the page locale prefix
	 * @param url
	 *            the url
	 * @return the string
	 */
	public String resolve302Redirects(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String pageLocalePrefix,
			String url) {

		String requestURI = httpServletRequest.getRequestURI();
		String destinyURLPath = "";
		String preffix = DESKTOP;
		// Detect if requests come from Mobile or Desktops
		boolean isMobile = MobileUtils.detectMobileBrowser(httpServletRequest);

		if (isMobile) {
			preffix = MOBILE;
		}

		// Detect if the url is external
		String urlToRedirect = resolve302Map.get(preffix + url);
		boolean external = isThisURLExternal(urlToRedirect, httpServletRequest);

		if (requestURI.contains(url)) {
			// Validates if it is an internal url
			if (!external) {
				destinyURLPath = "/" + pageLocalePrefix;
			}
			destinyURLPath += urlToRedirect;
		}

		return destinyURLPath;
	}

	/**
	 * Checks if is this url external.
	 * 
	 * @param url
	 *            the url
	 * @param request
	 *            the request
	 * @return true, if is this url external
	 */
	private boolean isThisURLExternal(String url, HttpServletRequest request) {
		boolean external = false;

		if (url.startsWith("http") && (!url.contains(request.getServerName()))) {
			external = true;
		}

		return external;
	}

}
