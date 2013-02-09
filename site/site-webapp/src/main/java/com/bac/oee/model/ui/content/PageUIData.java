package com.bac.oee.model.ui.content;

import java.util.ArrayList;
import java.util.List;

import com.bac.oee.model.ui.content.common.PageTypeUIEnum;

// TODO: Auto-generated Javadoc
/**
 * The Class PageUIData.
 */
public class PageUIData extends MasterContentUIData {

	/** The name. */
	private String name;

	/** The parent name. */
	private String parentName;

	/** The page type. */
	private PageTypeUIEnum pageType;

	/** The parent page. */
	private PageUIData parentPage;

	/**
	 * Instantiates a new page ui data.
	 */
	public PageUIData() {

	}

	/** The intro animation. */
	private List<IntroAnimationUIData> introAnimationList = new ArrayList<IntroAnimationUIData>();

	/** The page list. */
	private List<PageUIData> pageList = new ArrayList<PageUIData>();

	/** The panel list. */
	private List<PanelUIData> panelList = new ArrayList<PanelUIData>();

	/**
	 * Gets the page list.
	 * 
	 * @return the page list
	 */
	public List<PageUIData> getPageList() {
		return pageList;
	}

	/**
	 * Gets the panel list.
	 * 
	 * @return the panel list
	 */
	public List<PanelUIData> getPanelList() {
		return panelList;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the page type.
	 * 
	 * @return the page type
	 */
	public PageTypeUIEnum getPageType() {
		return pageType;
	}

	/**
	 * Sets the page type.
	 * 
	 * @param pageType
	 *            the new page type
	 */
	public void setPageType(PageTypeUIEnum pageType) {
		this.pageType = pageType;
	}

	/**
	 * Gets the parent name.
	 * 
	 * @return the parent name
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * Sets the parent name.
	 * 
	 * @param parentName
	 *            the new parent name
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 * Gets the parent page.
	 * 
	 * @return the parent page
	 */
	public PageUIData getParentPage() {
		return parentPage;
	}

	/**
	 * Sets the parent page.
	 * 
	 * @param parentPage
	 *            the new parent page
	 */
	public void setParentPage(PageUIData parentPage) {
		this.parentPage = parentPage;
	}

	/**
	 * Gets the intro animation list.
	 * 
	 * @return the intro animation list
	 */
	public List<IntroAnimationUIData> getIntroAnimationList() {
		return introAnimationList;
	}

	/**
	 * Sets the intro animation list.
	 * 
	 * @param introAnimationList
	 *            the new intro animation list
	 */
	public void setIntroAnimationList(
			List<IntroAnimationUIData> introAnimationList) {
		this.introAnimationList = introAnimationList;
	}
}
