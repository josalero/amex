package com.bac.oee.model.navigation;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class PageNavigationData.
 */
public class PageNavigationData {

	/** The nav items. */
	private List<PageNavigationItem> navItems = new ArrayList<PageNavigationItem>();

	/** The title. */
	private String title;

	/**
	 * Gets the nav items.
	 * 
	 * @return the nav items
	 */
	public List<PageNavigationItem> getNavItems() {
		return navItems;
	}

	/**
	 * Sets the nav items.
	 * 
	 * @param navItems
	 *            the new nav items
	 */
	public void setNavItems(List<PageNavigationItem> navItems) {
		this.navItems = navItems;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
