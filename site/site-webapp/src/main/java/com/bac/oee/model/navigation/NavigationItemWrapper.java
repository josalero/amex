package com.bac.oee.model.navigation;

import java.util.List;

import com.bac.oee.content.Item;
import com.bac.oee.content.Navitem;

// TODO: Auto-generated Javadoc
/**
 * The Class NavigationItemWrapper.
 */
public class NavigationItemWrapper {

	/** The navitem. */
	private Navitem navitem;

	/** The navigation items. */
	private List<NavigationItem> navigationItems;

	/**
	 * Instantiates a new navigation item wrapper.
	 * 
	 * @param navitem
	 *            the navitem
	 */
	public NavigationItemWrapper(Navitem navitem) {
		this.navitem = navitem;
	}

	/* Auto Generated Delegate Getters */
	/**
	 * Gets the nav item list.
	 * 
	 * @return the nav item list
	 */
	public List<Item> getNavItemList() {
		return navitem.getNavItemList();
	}

	/* Auto Generated Getters / Setters */
	/**
	 * Gets the navigation items.
	 * 
	 * @return the navigation items
	 */
	public List<NavigationItem> getNavigationItems() {
		return navigationItems;
	}

	/**
	 * Sets the navigation items.
	 * 
	 * @param navigationItems
	 *            the new navigation items
	 */
	public void setNavigationItems(List<NavigationItem> navigationItems) {
		this.navigationItems = navigationItems;
	}
}
