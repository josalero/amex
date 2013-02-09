package com.bac.oee.web.model;

import com.bac.oee.content.Image;
import com.bac.oee.content.Navigation;
import com.bac.oee.content.PrimaryNav;

// TODO: Auto-generated Javadoc
/**
 * The Class NavigationUIModel.
 */
public class NavigationUIModel {

	/** The navigation. */
	private Navigation navigation;

	/** The highlights. */
	private String highlights;

	/**
	 * Instantiates a new navigation ui model.
	 * 
	 * @param navigation
	 *            the navigation
	 */
	public NavigationUIModel(Navigation navigation) {
		super();
		this.navigation = navigation;
	}

	/**
	 * Gets the logo.
	 * 
	 * @return the logo
	 */
	public Image getLogo() {
		return navigation.getLogo();
	}

	/**
	 * Gets the primary nav.
	 * 
	 * @return the primary nav
	 */
	public PrimaryNav getPrimaryNav() {
		return navigation.getPrimaryNav();
	}
}
