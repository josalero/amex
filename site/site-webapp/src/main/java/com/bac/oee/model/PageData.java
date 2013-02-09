package com.bac.oee.model;

import com.bac.oee.content.ContentItem;
import com.bac.oee.content.Indexpage;
import com.bac.oee.model.navigation.NavigationData;
import com.bac.oee.model.ui.content.IndexPageUIData;

// TODO: Auto-generated Javadoc
/**
 * The Class PageData.
 */
public class PageData {

	/** The content item. */
	protected ContentItem contentItem;

	/** The navigation data. */
	protected NavigationData navigationData;

	/** The index page ui data. */
	protected IndexPageUIData indexPageUIData;

	/** The index page. */
	protected Indexpage indexPage;

	/** The page title. */
	private String pageTitle;

	/**
	 * Gets the navigation data.
	 * 
	 * @return the navigation data
	 */
	public NavigationData getNavigationData() {
		return navigationData;
	}

	/**
	 * Sets the navigation data.
	 * 
	 * @param navigationData
	 *            the new navigation data
	 */
	public void setNavigationData(NavigationData navigationData) {
		this.navigationData = navigationData;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return contentItem.getId();
	}

	/**
	 * Gets the cms id.
	 * 
	 * @return the cms id
	 */
	public String getCmsId() {
		return contentItem.getCmsId();
	}

	/**
	 * Gets the template.
	 * 
	 * @return the template
	 */
	public String getTemplate() {
		return contentItem.getTemplate();
	}

	/**
	 * Gets the html title.
	 * 
	 * @return the html title
	 */
	public String getHtmlTitle() {
		return contentItem.getMetadata().getHtmltitle();
	}

	/**
	 * Gets the meta keywords.
	 * 
	 * @return the meta keywords
	 */
	public String getMetaKeywords() {
		return contentItem.getMetadata().getMetakeywords();
	}

	/**
	 * Gets the meta description.
	 * 
	 * @return the meta description
	 */
	public String getMetaDescription() {
		return contentItem.getMetadata().getMetadescription();
	}

	/**
	 * Gets the canonical url.
	 * 
	 * @return the canonical url
	 */
	public String getCanonicalUrl() {
		return contentItem.getMetadata().getCanonicalUrl();
	}

	/**
	 * Gets the content type.
	 * 
	 * @return the content type
	 */
	public String getContentType() {
		// return contentItem.toString();
		return "text/html";
	}

	/* Constructors */
	/**
	 * Instantiates a new page data.
	 * 
	 * @param contentItem
	 *            the content item
	 */
	public PageData(ContentItem contentItem) {
		this.contentItem = contentItem;
	}

	/**
	 * Gets the content item.
	 * 
	 * @return the content item
	 */
	public ContentItem getContentItem() {
		return this.contentItem;
	}

	/**
	 * Gets the page path.
	 * 
	 * @return the page path
	 */
	public String getPagePath() {
		return contentItem.getPagePath();
	}

	/**
	 * Gets the jcr path.
	 * 
	 * @return the jcr path
	 */
	public String getJcrPath() {
		return contentItem.getJcrPath();
	}

	/**
	 * Gets the page title.
	 * 
	 * @return the page title
	 */
	public String getPageTitle() {

		if (pageTitle != null && !pageTitle.isEmpty()) {

			return pageTitle;
		}

		return getHtmlTitle();
	}

	/**
	 * Sets the page title.
	 * 
	 * @param pageTitle
	 *            the new page title
	 */
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	/**
	 * Gets the index page.
	 * 
	 * @return the index page
	 */
	public Indexpage getIndexPage() {
		return indexPage;
	}

	/**
	 * Gets the index page ui data.
	 * 
	 * @return the index page ui data
	 */
	public IndexPageUIData getIndexPageUIData() {
		return indexPageUIData;
	}

	/**
	 * Sets the index page ui data.
	 * 
	 * @param indexPageUIData
	 *            the new index page ui data
	 */
	public void setIndexPageUIData(IndexPageUIData indexPageUIData) {
		this.indexPageUIData = indexPageUIData;
	}

}
