package com.bac.oee.model.navigation;

import com.bac.oee.content.LogoContainer;
import com.bac.oee.content.Navitem;
import com.bac.oee.content.PrimaryNav;

// TODO: Auto-generated Javadoc
/**
 * The Class NavigationWrapper.
 */
public class NavigationWrapper {

	/** The primary nav. */
	protected PrimaryNav primaryNav;

	/** The navigation item wrapper. */
	protected NavigationItemWrapper navigationItemWrapper;

	/**
	 * Instantiates a new navigation wrapper.
	 */
	public NavigationWrapper() {

	}

	/**
	 * Instantiates a new navigation wrapper.
	 * 
	 * @param primaryNav
	 *            the primary nav
	 */
	public NavigationWrapper(PrimaryNav primaryNav) {
		this.primaryNav = primaryNav;
	}

	/* Auto Generated Getters / Setters */
	/**
	 * Gets the navigation item wrapper.
	 * 
	 * @return the navigation item wrapper
	 */
	public NavigationItemWrapper getNavigationItemWrapper() {
		return navigationItemWrapper;
	}

	/**
	 * Sets the navigation item wrapper.
	 * 
	 * @param navigationItemWrapper
	 *            the new navigation item wrapper
	 */
	public void setNavigationItemWrapper(
			NavigationItemWrapper navigationItemWrapper) {
		this.navigationItemWrapper = navigationItemWrapper;
	}
}
