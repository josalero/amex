package com.bac.oee.struts.action;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bac.oee.content.ContentItem;
import com.bac.oee.model.PageData;
import com.bac.oee.model.PageFactory;
import com.bac.oee.model.level.PageBaseLevel;
import com.bac.oee.struts.ViewConstants;

// TODO: Auto-generated Javadoc
/**
 * PageAction encapsulates core logic and functionality that occurs across all
 * subpages.
 * 
 * @author christopher.tai@starcomworldwide.com
 */
@SuppressWarnings("serial")
public class PageAction extends AbstractBaseAction {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(PageAction.class);

	/** The page factory. */
	@Autowired
	protected PageFactory pageFactory;
	// protected String xmlFilePath = null;

	/** The survey tracking pixel map. */
	@Resource
	private Map<String, String> surveyTrackingPixelMap;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.struts.action.AbstractBaseAction#execute()
	 */
	@Override
	public String execute() {

		log.debug("##### PageAction: Entry ... requestURI = "
				+ getServletRequest().getRequestURI()
				+ " #####; getServletRequest() = " + getServletRequest()
				+ ", this = " + this);

		String status = SERVER_ERROR_ACTION;

		try {

			super.execute();
			
			Locale locale = Locale.getDefault();

			String localeString = getLocaleString(getServletRequest());

			ContentItem contentItem = (ContentItem) getServletRequest()
					.getAttribute(ViewConstants.PAGE_CONTENT_ITEM);

			PageBaseLevel pageLevel = pageFactory.getPage(contentItem,
					locale.toString(), getServletRequest());

			PageData pageData = pageLevel.getPageData();

			log.debug("PageAction: Entry ... template = "
					+ pageData.getTemplate());

			/** FLAGEN-1208 **/

			try {

				getServletRequest().setAttribute(ViewConstants.PAGE_TEMPLATE,
						"NONE");

				String tokens[] = pageData.getTemplate().split("_");
				log.debug("PageAction: Entry ... 1 " + pageData == null);

				if (tokens != null && !ArrayUtils.isEmpty(tokens)) {
					if (tokens.length == 2) {
						getServletRequest().setAttribute(
								ViewConstants.PAGE_TEMPLATE, tokens[1]);
					} else {
						getServletRequest().setAttribute(
								ViewConstants.PAGE_TEMPLATE, "NONE");
					}
				} else {
					getServletRequest().setAttribute(
							ViewConstants.PAGE_TEMPLATE, "NONE");
				}
			} catch (Exception exception) {

				log.warn("PageAction Diagnostics:\n   pageData = "
						+ pageData
						+ "\n   pageLevel = "
						+ pageLevel
						+ "\n   getServletRequest() = "
						+ getServletRequest()
						+ "\n   requestURI = "
						+ ((getServletRequest() != null) ? getServletRequest()
								.getRequestURI() : "N/A"));

				log.error("Failed to parse template info", exception);
			}

			if (!pageLevel.getReturnActionAsString().equals(
					ViewConstants.SUCCESS)) {
				return pageLevel.getReturnActionAsString();
			}

			String mastheadCategoryId = (String) getServletRequest()
					.getAttribute(ViewConstants.MASTHEAD_CATEGORY_ID);

			if (StringUtils.isEmpty(mastheadCategoryId)) {
				mastheadCategoryId = StringUtils.EMPTY;
			}

			log.debug("PageAction: Putting PageData to request: pageData = "
					+ pageData);

			// End FLAGEN-436

			getServletRequest().setAttribute(ViewConstants.PAGE_DATA, pageData);

			getServletRequest().setAttribute(ViewConstants.PAGE_CANONICAL_URL,
					getCanonicalUrl(pageData));
						
			getServletRequest().setAttribute(ViewConstants.GA_SITE_ID, googleAnalyticsSiteID);
			
			status = SUCCESS;
		} catch (Exception e) {

			log.error("Could not carry out the Struts PageAction execution!", e);

			try {

				Enumeration<String> attrNames = getServletRequest()
						.getAttributeNames();

				while (attrNames.hasMoreElements()) {
					String name = attrNames.nextElement();
					log.warn("Attribute: " + name);
					log.warn("   Value = "
							+ getServletRequest().getAttribute(name));

				}

			} catch (Exception ex) {

				log.error("Secondary exception");

			}

			return SERVER_ERROR_ACTION;
		}
		return status;

	}

	/**
	 * Gets the canonical url.
	 * 
	 * @param pageData
	 *            the page data
	 * @return the canonical url
	 */
	private String getCanonicalUrl(PageData pageData) {
		if (pageData != null
				&& StringUtils.isNotEmpty(pageData.getCanonicalUrl())) {
			return pageData.getCanonicalUrl();
		}

		String hostName = getServletRequest().getScheme() + "://"
				+ getServletRequest().getServerName();
		Object relativeJCRPagePath = getServletRequest().getAttribute(
				ViewConstants.RELATIVE_PAGE_PATH);
		String canonicalUrl = null;

		if (relativeJCRPagePath != null) {
			canonicalUrl = hostName + "/" + (String) relativeJCRPagePath;
		}

		return canonicalUrl;
	}

	/**
	 * Resolve survey tracking pixel.
	 * 
	 * @param pageData
	 *            the page data
	 */
	private void resolveSurveyTrackingPixel(PageData pageData) {
		String id = pageData.getId();

		if (pageData.getTemplate().contains("2c")
				|| pageData.getTemplate().contains("3c")) {
			id = "partnering-locally";
		}
		String pixel = surveyTrackingPixelMap.get(id);
		if (pixel != null) {
			getServletRequest().setAttribute("surveyTrackingPixel", pixel);
		}
	}
}
