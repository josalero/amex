/**
 * 
 */
package com.bac.oee.model.level;

import java.io.StringWriter;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bac.oee.content.ContentItem;
import com.bac.oee.content.TagContainerItem;
import com.bac.oee.content.exceptions.ContentLoaderException;
import com.bac.oee.model.PageData;
import com.bac.oee.model.navigation.PageNavigationData;
import com.bac.oee.service.PageService;
import com.bac.oee.struts.ViewConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class PageBaseLevel.
 * 
 * @author Jose Aleman
 */
@Component
public class PageBaseLevel {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(PageBaseLevel.class);

	/** The locale. */
	private String id, locale;

	/** The content item. */
	private ContentItem contentItem;

	/** The page data. */
	protected PageData pageData;

	/** The page service. */
	@Autowired
	protected PageService pageService;

	/** The success code. */
	protected String successCode = null;

	/** The locale url prefix. */
	private String localeURLPrefix;

	/** The referer. */
	private String referer;

	/** The page context path. */
	private String pageContextPath;

	/** The page url. */
	private String pageUrl;

	/** The poll id set. */
	private Set<String> pollIDSet = new TreeSet<String>();

	/**
	 * Instantiates a new page base level.
	 */
	public PageBaseLevel() {

	}

	/**
	 * Instantiates a new page base level.
	 * 
	 * @param contentItem
	 *            the content item
	 * @param locale
	 *            the locale
	 */
	public PageBaseLevel(ContentItem contentItem, String locale) {
		this.id = contentItem.getId();
		this.locale = locale;
		this.contentItem = contentItem;

		this.localeURLPrefix = locale.replace("_", "-").toLowerCase();
	}

	/**
	 * Gets the locale url prefix.
	 * 
	 * @return the locale url prefix
	 */
	protected String getLocaleURLPrefix() {

		if (localeURLPrefix == null && locale != null) {
			this.localeURLPrefix = locale.replace("_", "-").toLowerCase();
		}

		return localeURLPrefix;
	}

	/**
	 * Inits the.
	 * 
	 * @param servletRequest
	 *            the servlet request
	 */
	public void init(HttpServletRequest servletRequest) {

		referer = servletRequest.getHeader("referer");

		pageContextPath = servletRequest.getScheme() + "://"
				+ servletRequest.getServerName() + ":"
				+ servletRequest.getServerPort()
				+ servletRequest.getContextPath();

		pageUrl = servletRequest.getRequestURI();
		if (!StringUtils.isEmpty(servletRequest.getQueryString())) {
			pageUrl += "?" + servletRequest.getQueryString();
		}

		if (servletRequest.getCookies() != null) {
			for (Cookie cookie : servletRequest.getCookies()) {
				String cookieValue = cookie.getName();
				if (cookieValue != null && cookieValue.contains("poll_")) {
					String pollID = cookieValue.substring("poll_".length());
					pollIDSet.add(pollID);
				}
			}
		}

	}

	/**
	 * Gets the locale.
	 * 
	 * @return the locale
	 */
	public String getLocale() {

		return locale;
	}

	/**
	 * Sets the locale.
	 * 
	 * @param locale
	 *            the new locale
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * Prepare all the stuff before the "page" is rendered.
	 */
	protected void onBeforeRender() {
		this.pageData = new PageData(this.contentItem);
	}

	/**
	 * Set values after the page is rendered.
	 */
	protected void onAfterRender() {
		PageNavigationData pageNavigationData = null;
	}

	/**
	 * Render.
	 */
	private void render() {

		this.successCode = ViewConstants.SUCCESS;
		onBeforeRender();
		resolveBodyContentData();
		// resolveTileData();
		onAfterRender();
		resolveReturnAction();
	}

	/**
	 * Resolve related content data.
	 */
	protected void resolveRelatedContentData() {
		// pageData.setRelatedContentData(getPageService().getRelatedContentData(
		// pageData, getLocale()));
	}

	/*
	 * Currently for DartTags only and for articles page (L5) we display the
	 * tags content related to the parent page. Example: The following page is a
	 * parent that has articles: /xml/en_US/partnering-locally/illinois.xml. So
	 * All the articles under that page are children of illinois. When we
	 * display the tag for a child article, we present the content associated to
	 * the parent row: Dart /xml/en_US/partnering-locally/illinois.xml
	 * enterprise:Page. See coremetrics-tag-mapping.xls in your
	 * /cmsmcontent/xml/en_US/tagging/coremetrics-tag-mapping.xls. The method
	 * below can overwrite the default behaviour
	 */
	/**
	 * Retrieve parent page id.
	 * 
	 * @param jcrPath
	 *            the jcr path
	 * @param tagItem
	 *            the tag item
	 * @return true, if successful
	 */
	private boolean retrieveParentPageId(String jcrPath,
			TagContainerItem tagItem) {
		boolean retrieve = true;

		if (jcrPath != null && jcrPath.trim().length() > 0) {
			int slashLastIndex = jcrPath.lastIndexOf("/");
			int xmlPosition = jcrPath.indexOf(".xml");
			String articleId = jcrPath.substring(slashLastIndex + 1,
					xmlPosition + 4);
			String path = "/xml/en_US/partnering-locally/" + articleId;
			try {
				TagContainerItem foundTagItem = pageService.getTagLoader()
						.retrieve("Dart" + path + "enterprise:Page");
				if (foundTagItem != null) {
					retrieve = false;
					tagItem.setName(foundTagItem.getName());
					tagItem.setPagePath(foundTagItem.getPagePath());
					tagItem.setCategory(foundTagItem.getCategory());
					tagItem.setTagName(foundTagItem.getTagName());
					tagItem.setCmsPath(foundTagItem.getCmsPath());
					tagItem.setDartSrc(foundTagItem.getDartSrc());
					tagItem.setDartType(foundTagItem.getDartType());
					tagItem.setLevel(foundTagItem.getLevel());
					tagItem.setTagType(foundTagItem.getTagType());
					tagItem.setType(foundTagItem.getType());
					tagItem.setVideoStart(foundTagItem.getVideoStart());
					tagItem.setVideoEnd(foundTagItem.getVideoEnd());
					tagItem.setVideoPause(foundTagItem.getVideoPause());
				} else {
					log.warn("No Dart Tag Item was found for the dart: "
							+ "Dart" + path + "enterprise:Page");
				}
			} catch (ContentLoaderException e) {
				log.error("An unexpected error occurred while retrieving a dart tag from the JCR repository. "
						+ e.toString());
			}
		}

		return retrieve;
	}

	/**
	 * Gets the previous page id.
	 * 
	 * @return the previous page id
	 */
	public String getPreviousPageId() {

		String previousPageId = StringUtils.EMPTY;

		if (StringUtils.isNotEmpty(referer)) {
			int slashLastIndex = referer.lastIndexOf("/");
			int htmlPosition = referer.indexOf(".html");
			try {
				previousPageId = referer.substring(slashLastIndex + 1,
						htmlPosition);
			} catch (StringIndexOutOfBoundsException ex) {
				log.error("slashLastIndex:" + slashLastIndex + " htmlPosition:"
						+ htmlPosition);
				log.error(ex.toString());
			}
		}

		return previousPageId;
	}

	/**
	 * Gets the pdf download json string.
	 * 
	 * @return the pdf download json string
	 */
	public String getPdfDownloadJsonString() {
		StringWriter stringWriter = new StringWriter();
		ObjectMapper jsonObjectMapper = new ObjectMapper();
		ObjectWriter objectWriter = jsonObjectMapper.writer();
		String jsonString = "";

		try {
			// objectWriter
			// .writeValue(stringWriter, pageData.getPdfDownloadList());
			jsonString = stringWriter.toString();
			stringWriter.close();
			if (jsonString != null && jsonString.length() > 2) {
				jsonString = jsonString.substring(1, jsonString.length() - 1);
			}
		} catch (Exception exception) {
			log.error(exception);
		}

		return jsonString;
	}

	/**
	 * This method is meant to map all models using content POJOS.
	 */
	protected void resolveBodyContentData() {

	}

	/**
	 * Should be overridable for sub classes.
	 */
	protected void resolveReturnAction() {
		this.successCode = ViewConstants.SUCCESS;
	}

	/**
	 * Gets the return action as string.
	 * 
	 * @return the return action as string
	 */
	public String getReturnActionAsString() {
		return this.successCode;
	}

	/**
	 * Gets the page data.
	 * 
	 * @return the page data
	 */
	public final PageData getPageData() {
		log.debug("getPageData() : " + getId());
		render();
		return pageData;
	}

	/**
	 * Actual Wrapper of the method getParameter of HttpServletRequest class.
	 * 
	 * @return the page context path
	 */
	// protected String getParameterValue(String key) {
	// String value = servletRequest.getParameter(key);
	//
	// if (StringUtils.isEmpty(value)) {
	// value = (String) servletRequest.getAttribute(key);
	// }
	//
	// return value;
	// }

	/**
	 * 
	 * @return
	 */
	protected String getPageContextPath() {

		return pageContextPath;
	}

	/**
	 * Gets the page url.
	 * 
	 * @return the page url
	 */
	protected String getPageURL() {
		return pageUrl;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the content item.
	 * 
	 * @return the content item
	 */
	public ContentItem getContentItem() {
		return contentItem;
	}

	/**
	 * Sets the content item.
	 * 
	 * @param contentItem
	 *            the new content item
	 */
	public void setContentItem(ContentItem contentItem) {
		this.contentItem = contentItem;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the page service.
	 * 
	 * @return the page service
	 */
	public PageService getPageService() {
		return pageService;
	}

	/**
	 * Sets the page service.
	 * 
	 * @param pageService
	 *            the new page service
	 */
	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}

	/**
	 * Sets the from.
	 * 
	 * @param setFromObject
	 *            the new from
	 */
	public void setFrom(PageBaseLevel setFromObject) {

		this.pageService = setFromObject.pageService;
	}

}
