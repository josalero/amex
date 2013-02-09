package com.bac.oee.struts.action;

import java.util.Stack;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.bac.oee.content.ContentItem;
import com.bac.oee.content.Metadata;
import com.bac.oee.content.TagContainerItem;
import com.bac.oee.content.exceptions.ContentLoaderException;
import com.bac.oee.model.PageData;
import com.bac.oee.service.PageService;
import com.bac.oee.service.RedirectResolverService;
import com.bac.oee.struts.ViewConstants;
import com.bac.oee.util.PageUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class FilterAction.
 */
public class FilterAction extends AbstractBaseAction {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(FilterAction.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -262084431237948953L;

	/** The referrer http header. */
	private static String REFERRER_HTTP_HEADER = "Referrer";

	/** The referrer param. */
	private static String REFERRER_PARAM = "referrer";

	/** The page service. */
	@Autowired
	@Qualifier("pageService")
	private PageService pageService;

	/** The redirect resolver service. */
	@Autowired
	private RedirectResolverService redirectResolverService;

	// @Autowired
	// private BrandMappingsManager brandMappingsManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.struts.action.AbstractBaseAction#execute()
	 */
	@Override
	public String execute() {

		String requestURI = getServletRequest().getRequestURI();
		String relativePagePath = requestURI.substring(getServletRequest()
				.getContextPath().length() + 1);

		log.info("##### HTTP REQUEST URI ACCESSED: " + requestURI + " #####");

		String localeString = getLocaleString(getServletRequest());
		String pageLocalePrefix = localeString.replace('_', '-').toLowerCase();

		String chainActionName = SERVER_ERROR_ACTION;

		try {

			// Bypass all filtering logic if redirected to page-not-found.html
			// 404 marker page
			if (relativePagePath.endsWith("/page-not-found.html")) {
				resolveCoremetricsTagging(PAGE_NOT_FOUND_ACTION);
				resolveDartTagging(PAGE_NOT_FOUND_ACTION);
				return PAGE_NOT_FOUND_ACTION;
			}

			if (relativePagePath.endsWith("server-error.html")) {
				resolveCoremetricsTagging(SERVER_ERROR_ACTION);
				resolveDartTagging(SERVER_ERROR_ACTION);
				return SERVER_ERROR_ACTION;
			}

			HttpSession httpSession = getServletRequest().getSession();
			log.debug(">>>> SESSION ID: " + httpSession.getId());
			super.execute();

			String brand = null;
			String httpReferrer = null;

			// first try to get the referrer out of the HTTP header...
			String referrer = getServletRequest().getHeader(
					REFERRER_HTTP_HEADER);

			if (StringUtils.isNotEmpty(referrer)) {
				log.debug("FilterAction: Entry ... requestURI = "
						+ getServletRequest().getRequestURI() + ", referrer = "
						+ referrer);
				httpReferrer = referrer;
			}

			// if we don't have a referrer, grab it from the HTTP parameter...
			if (StringUtils.isEmpty(httpReferrer)) {
				httpReferrer = getServletRequest().getParameter(REFERRER_PARAM);
			}

			// flag to check if we have an HTTP referrer match
			boolean referrerMatch = false;

			// if (StringUtils.isNotEmpty(httpReferrer)) {
			// log.debug("HTTP REFERRER: " + httpReferrer);
			// Map<String, List<String>> brandMappingMap = brandMappingsManager
			// .getBrandMappingMap();
			//
			// if (brandMappingMap == null || brandMappingMap.isEmpty()) {
			// log.warn("NULL or empty brand mappings! Check the XML!");
			// } else {
			// for (Map.Entry<String, List<String>> entry : brandMappingMap
			// .entrySet()) {
			// String key = entry.getKey();
			//
			// // if the HTTP Referrer matches the key, see if we have
			// // matches
			// if (key.equals(httpReferrer)) {
			// for (String pattern : entry.getValue()) {
			// Pattern p = Pattern.compile(pattern);
			// Matcher matcher = p.matcher(httpReferrer);
			//
			// if (matcher.find()) {
			// referrerMatch = true;
			// brand = key;
			// }
			// }
			// }
			// }
			//
			// if (referrerMatch) {
			// getServletRequest().getSession().setAttribute(
			// ViewConstants.HTTP_REFERRER, httpReferrer);
			// getServletRequest().getSession().setAttribute(
			// ViewConstants.BRAND, brand);
			// }
			// }
			// }

			String relativeJCRPagePath = new String(relativePagePath);
			log.debug("FIRST PAGE PATH: " + relativePagePath);

			if (relativePagePath.contains(pageLocalePrefix)) {
				relativePagePath = relativePagePath
						.substring(getPageLocalePrefix().length() + 1);
			}

			log.debug("SECOND PAGE PATH: " + relativePagePath);
			int pos = relativePagePath.lastIndexOf('.');
			relativePagePath = relativePagePath.substring(0, pos);
			getServletRequest().setAttribute(ViewConstants.RELATIVE_PAGE_PATH,
					null);

			if (relativePagePath.equals("index")) {
				chainActionName = "index";
				getServletRequest().setAttribute(
						ViewConstants.RELATIVE_PAGE_PATH, relativeJCRPagePath);
				// resolveCoremetricsTagging(relativePagePath);
				// resolveDartTagging(relativePagePath);

				// Look for the homepage content, if it exists in the JCR...
				String[] elements = relativePagePath.split("/");
				String category = elements[0];
				String pagePath = relativePagePath;
				ContentItem contentItem = resolveViewInJcr(category, pagePath,
						getServletRequest());

				if (contentItem != null) {
					getServletRequest().setAttribute(
							ViewConstants.RELATIVE_PAGE_PATH,
							relativeJCRPagePath);
					getServletRequest().setAttribute(
							ViewConstants.PAGE_CONTENT_ITEM, contentItem);
				}
			} else if (relativePagePath.equals("site-map")) {
				chainActionName = "site-map";
			} else if (relativePagePath.equals("sitemap")) {
				chainActionName = "sitemap";
			} else if (relativePagePath.startsWith("admin/")) {
				chainActionName = relativePagePath.substring(relativePagePath
						.lastIndexOf("/") + 1);
			} else if (relativePagePath.startsWith("tests/")) {
				// TODO: Articles logic
				chainActionName = relativePagePath.substring(relativePagePath
						.lastIndexOf("/") + 1);
				getServletRequest().setAttribute(ViewConstants.PAGE_TEMPLATE,
						"no-index");

				if (relativePagePath.contains("preview")) {
					ContentItem pageContentItem = resolveViewInJcr(
							"global-impact", "global-impact",
							getServletRequest());
					pageContentItem.setTemplate("level_2b_preview");
					// chainActionName = "level-2b-preview";
					getServletRequest().setAttribute(
							ViewConstants.RELATIVE_PAGE_PATH,
							relativeJCRPagePath);
					getServletRequest().setAttribute(
							ViewConstants.PAGE_CONTENT_ITEM, pageContentItem);
				}
			} else if (relativePagePath.startsWith("ajax/")) {
				// TODO: Articles logic
				chainActionName = relativePagePath.substring(relativePagePath
						.lastIndexOf("/") + 1);
			} else if (relativePagePath.contains("listing")) {
				// TODO: Articles logic
				chainActionName = "articleListing";
			} else {
				String[] elements = relativePagePath.split("/");
				String category = elements[0];
				String pagePath = relativePagePath;
				ContentItem pageContentItem = resolveViewInJcr(category,
						pagePath, getServletRequest());

				if (pageContentItem == null) {
					resolveCoremetricsTagging("pageNotFoundAction");
					resolveDartTagging("pageNotFoundAction");
					return "pageNotFoundAction";
				}

				Stack<ContentItem> contentItemStack = contentManager
						.getHierarchicalPagePath(pageContentItem,
								getLocaleString(getServletRequest()));
				getServletRequest().setAttribute(
						ViewConstants.SITE_CATEGORY_ID, category);
				getServletRequest().setAttribute(
						ViewConstants.USER_LOCALE_PREFIX, pageLocalePrefix);

				contentItemStack = contentManager.getHierarchicalPagePath(
						pageContentItem, getLocaleString(getServletRequest()));
				String level3bCategoryId = getLevel3bCategoryId(
						contentItemStack, getLocaleString(getServletRequest()));
				getServletRequest().setAttribute(
						ViewConstants.MASTHEAD_CATEGORY_ID, level3bCategoryId);

				if (pageContentItem != null) {
					getServletRequest().setAttribute(
							ViewConstants.RELATIVE_PAGE_PATH,
							relativeJCRPagePath);
					getServletRequest().setAttribute(
							ViewConstants.PAGE_CONTENT_ITEM, pageContentItem);

					chainActionName = pageContentItem.getTemplate();
					chainActionName = chainActionName.replaceAll("_", "-");
					log.debug("ChainActionName[Template]: " + chainActionName);
				}
			}

			log.debug("chainActionName: " + chainActionName);
		} catch (Exception e) {
			log.error("Unable to carry out the Struts execution!", e);
			return SERVER_ERROR_ACTION;
		}
		return chainActionName;

	}

	/**
	 * Resolve view in jcr.
	 * 
	 * @param category
	 *            the category
	 * @param pagePath
	 *            the page path
	 * @param servletRequest
	 *            the servlet request
	 * @return the content item
	 */
	private ContentItem resolveViewInJcr(String category, String pagePath,
			ServletRequest servletRequest) {

		ContentItem pageContentItem = null;

		try {
			String localeString = getLocaleString(servletRequest);
			log.debug("Parameters [category, pagePath, localeString]: "
					+ category + "," + pagePath + "," + localeString);
			pageContentItem = contentManager.retrievePage(category, pagePath,
					localeString);
			if (pageContentItem == null) {
				log.warn("Unable to find a node reference for the page path: "
						+ pagePath);
			}
		} catch (Exception e) {
			log.warn("Unable to find a node reference for the page path: "
					+ pagePath, e);
		}

		return pageContentItem;
	}

	/**
	 * Gets the level3b category id.
	 * 
	 * @param contentItemStack
	 *            the content item stack
	 * @param locale
	 *            the locale
	 * @return the level3b category id
	 */
	protected String getLevel3bCategoryId(Stack<ContentItem> contentItemStack,
			String locale) {
		if (contentItemStack == null) {
			return null;
		}

		ContentItem level3bContentItem = null;
		String level3bCategoryId = null;
		int size = contentItemStack.size();
		int counter = 0;

		if (size > 1) {
			ContentItem contentItem = null;

			while (!contentItemStack.empty()) {
				counter++;
				contentItem = contentItemStack.pop();

				if (counter == size) {
					level3bContentItem = contentItem;
				}
			}

			String jcrPath = level3bContentItem.getJcrPath();
			log.debug("LEVEL 3B Content Item JCR Path: " + jcrPath);
			level3bCategoryId = PageUtils.getSubCategory(jcrPath, locale);
		}

		log.debug(">>>>> LEVEL 3B CATEGORY ID: " + level3bCategoryId);
		return level3bCategoryId;
	}

	/**
	 * Resolve coremetrics tagging.
	 * 
	 * @param action
	 *            the action
	 */
	protected void resolveCoremetricsTagging(String action) {
		try {
			String jcrPath = "";
			if (action != null && action.equalsIgnoreCase("index")) {
				jcrPath = "home";
			} else if (action != null
					&& action.equalsIgnoreCase("pageNotFoundAction")) {
				jcrPath = "error404";
			} else if (action != null
					&& action.equalsIgnoreCase("serverErrorAction")) {
				jcrPath = "error500";
			}

			TagContainerItem tagItem = pageService.getTagLoader().retrieve(
					"Coremetrics" + jcrPath);
			if (tagItem == null) {
				if (!jcrPath.contains("error404")
						&& !jcrPath.contains("error500")) {
					log.warn("No Coremetrics Tag Item was found for the path: "
							+ "Coremetrics" + jcrPath);
				}
				tagItem = new TagContainerItem();
			}
			PageData pageData = (PageData) getServletRequest().getAttribute(
					ViewConstants.PAGE_DATA);
			if (pageData == null) {
				Metadata metaData = new Metadata();
				ContentItem ci = new ContentItem();
				ci.setMetadata(metaData);
				pageData = new PageData(ci);
			}

			getServletRequest().setAttribute(ViewConstants.PAGE_DATA, pageData);
			getServletRequest().setAttribute("faceBookPageName", "index");
		} catch (ContentLoaderException e) {
			log.error("An unexpected error occurred while retrieving a coremetric tag from the JCR repository. "
					+ e.toString());
		}
	}

	/**
	 * Resolve dart tagging.
	 * 
	 * @param action
	 *            the action
	 */
	protected void resolveDartTagging(String action) {
		try {
			String jcrPath = "";
			if (action != null && action.equalsIgnoreCase("index")) {
				jcrPath = "home";
			} else if (action != null
					&& action.equalsIgnoreCase("pageNotFoundAction")) {
				jcrPath = "error404";
			} else if (action != null
					&& action.equalsIgnoreCase("serverErrorAction")) {
				jcrPath = "error500";
			}

			TagContainerItem tagItem = pageService.getTagLoader().retrieve(
					"Dart" + jcrPath + "enterprise:Page");

			if (tagItem != null) {
				log.debug("TAG ITEM PAGE DART SOURCE: " + tagItem.getDartSrc()
						+ " | DART TYPE: " + tagItem.getDartType());
			} else {
				if (!jcrPath.contains("error404")
						&& !jcrPath.contains("error500")) {
					log.warn("No Dart Tag Item was found for the path: "
							+ "Dart" + jcrPath + "enterprise:Page");
				}
				tagItem = new TagContainerItem();
			}

			PageData pageData = (PageData) getServletRequest().getAttribute(
					ViewConstants.PAGE_DATA);
			if (pageData == null) {
				Metadata metaData = new Metadata();
				ContentItem ci = new ContentItem();
				ci.setMetadata(metaData);
				pageData = new PageData(ci);
			}

			getServletRequest().setAttribute(ViewConstants.PAGE_DATA, pageData);
		} catch (ContentLoaderException e) {
			log.error("An unexpected error occurred while retrieving a dart tag from the JCR repository. "
					+ e.toString());
		}
	}

	/* Spring Setters */
	/**
	 * Sets the page service.
	 * 
	 * @param pageService
	 *            the new page service
	 */
	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}

}