/**
 * 
 */
package com.bac.oee.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bac.oee.content.ContentItem;
import com.bac.oee.content.ContentManager;
import com.bac.oee.content.HrefLink;
import com.bac.oee.content.Page;
import com.bac.oee.content.TagContainerItem;
import com.bac.oee.content.TagLoader;
import com.bac.oee.content.TargetEnum;
import com.bac.oee.model.PageData;
import com.bac.oee.model.navigation.PageNavigationData;
import com.bac.oee.model.navigation.PageNavigationItem;
import com.bac.oee.util.PageUtils;

// TODO: Auto-generated Javadoc
/**
 * This class is meant to define all shared services for template pages.
 * 
 * @author Jose Aleman
 */
@Service
public class PageService {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(PageService.class);

	/** The content manager. */
	@Autowired
	private ContentManager contentManager;

	/** The tag loader. */
	@Autowired
	@Qualifier("coremetricsTagLoaderManager")
	protected TagLoader tagLoader;

	/**
	 * Gets the tag loader.
	 * 
	 * @return the tag loader
	 */
	public TagLoader getTagLoader() {
		return tagLoader;
	}

	/**
	 * Sets the tag loader.
	 * 
	 * @param tagLoader
	 *            the new tag loader
	 */
	public void setTagLoader(TagLoader tagLoader) {
		this.tagLoader = tagLoader;
	}

	/**
	 * Gets the child content items.
	 * 
	 * @param contentItem
	 *            the content item
	 * @return the child content items
	 */
	public List<ContentItem> getChildContentItems(ContentItem contentItem) {
		return contentManager.retrieveReferencedPages(contentItem);
	}

	/**
	 * Retrieve tile.
	 * 
	 * @param cmsId
	 *            the cms id
	 * @param locale
	 *            the locale
	 * @return the content item
	 */
	public ContentItem retrieveTile(String cmsId, String locale) {
		return contentManager.retrieveContentItemByCmsId(cmsId);
	}

	/**
	 * Gets the right nav caption.
	 * 
	 * @param text
	 *            the text
	 * @return the right nav caption
	 */
	public String getRightNavCaption(String text) {
		char[] delimiters = { ' ', '-' };
		String rightNavCaption = text.toLowerCase();
		rightNavCaption = WordUtils.capitalize(rightNavCaption, delimiters);
		return rightNavCaption;
	}

	/**
	 * Gets the page path.
	 * 
	 * @param pageJCRPath
	 *            the page jcr path
	 * @param locale
	 *            the locale
	 * @return the page path
	 */
	public String getPagePath(String pageJCRPath, String locale) {
		String pagePath = pageJCRPath.replace("/xml/" + locale + "/", "");
		pagePath = pagePath.replace(".xml", "");

		String[] elements = pagePath.split("/");

		pagePath = elements[0];

		if (elements.length > 1) {
			pagePath += "/";
			pagePath += elements[elements.length - 1];
		}

		return pagePath;
	}

	/**
	 * Gets the page link.
	 * 
	 * @param pageContentItem
	 *            the page content item
	 * @param locale
	 *            the locale
	 * @return the page link
	 */
	public String getPageLink(ContentItem pageContentItem, String locale) {
		return getPageLink(pageContentItem.getJcrPath(), locale);
	}

	/**
	 * Gets the page link.
	 * 
	 * @param pageJCRPath
	 *            the page jcr path
	 * @param locale
	 *            the locale
	 * @return the page link
	 */
	public String getPageLink(String pageJCRPath, String locale) {
		String pagePath = pageJCRPath.replace("/xml/" + locale + "/", "");
		pagePath = pagePath.replace(".xml", "");

		String[] elements = pagePath.split("/");

		pagePath = elements[0];

		if (elements.length > 1) {
			pagePath += "/";
			pagePath += elements[elements.length - 1];
		}

		String urlLocale = locale.replace('_', '-').toLowerCase();
		String pageLink = "/" + urlLocale + "/" + pagePath + ".html";
		return pageLink;
	}

}
