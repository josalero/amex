package com.bac.oee.model.navigation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

import com.bac.oee.content.ContentItem;
import com.bac.oee.content.GeoTaxonomy;
import com.bac.oee.content.Image;
import com.bac.oee.content.Navigation;
import com.bac.oee.content.PrimaryNav;

// TODO: Auto-generated Javadoc
/**
 * The Class NavigationData.
 */
public class NavigationData {

	/** The content item. */
	private ContentItem contentItem;

	/** The navigation. */
	private Navigation navigation;

	/** The navigation item list. */
	private List<NavigationItem> navigationItemList;// Has the pointer to the
													// right tiles and bottom
													// tiles

	/** The taxonomy data. */
	private TaxonomyData taxonomyData;

	/** The twitter accounts. */
	private List<String> twitterAccounts = Arrays.asList("BofA_Careers",
			"BofA_News", "BofA_Community", "BofA_Tips", "MerrillLynch",
			"USTrust");

	/** The locale. */
	private String locale;

	/**
	 * Instantiates a new navigation data.
	 * 
	 * @param contentItem
	 *            the content item
	 */
	public NavigationData(ContentItem contentItem) {
		if (contentItem != null) {
			if (contentItem.getContent() instanceof Navigation) {
				this.navigation = (Navigation) contentItem.getContent();
			}
		}
		// navigation.getFooterNav().ge
	}

	/**
	 * Instantiates a new navigation data.
	 * 
	 * @param navigation
	 *            the navigation
	 */
	public NavigationData(Navigation navigation) {
		this.navigation = navigation;
		if (navigationItemList == null) {
			navigationItemList = new ArrayList<NavigationItem>();
		}
	}

	/**
	 * Adds the.
	 * 
	 * @param navItem
	 *            the nav item
	 */
	public void add(NavigationItem navItem) {
		if (navigationItemList == null) {
			navigationItemList = new ArrayList<NavigationItem>();
		}
		navigationItemList.add(navItem);
	}

	/**
	 * Gets the navigation item list.
	 * 
	 * @return the navigation item list
	 */
	public List<NavigationItem> getNavigationItemList() {
		return navigationItemList;
	}

	/**
	 * Sets the navigation item list.
	 * 
	 * @param navigationItemList
	 *            the new navigation item list
	 */
	public void setNavigationItemList(List<NavigationItem> navigationItemList) {
		this.navigationItemList = navigationItemList;
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

	/**
	 * Gets the geo taxonomy.
	 * 
	 * @return the geo taxonomy
	 */
	public GeoTaxonomy getGeoTaxonomy() {
		return taxonomyData.getGeoTaxonomy();
	}

	/**
	 * Gets the topic to ada friendly name map.
	 * 
	 * @return the topic to ada friendly name map
	 */
	@Transient
	public Map<String, String> getTopicToAdaFriendlyNameMap() {
		return taxonomyData.getTopicToAdaFriendlyNameMap();
	}

	/**
	 * Gets the program to ada friendly name map.
	 * 
	 * @return the program to ada friendly name map
	 */
	@Transient
	public Map<String, String> getProgramToAdaFriendlyNameMap() {
		return taxonomyData.getProgramToAdaFriendlyNameMap();
	}

	/**
	 * Sets the taxonomy data.
	 * 
	 * @param data
	 *            the new taxonomy data
	 */
	public void setTaxonomyData(TaxonomyData data) {
		this.taxonomyData = data;
	}

	/**
	 * Gets the program to ada friendly name list.
	 * 
	 * @return the program to ada friendly name list
	 */
	public List<String> getProgramToAdaFriendlyNameList() {
		List<String> programToAdaFriendlyNameList = new ArrayList<String>(10);

		for (Map.Entry<String, String> entry : getProgramToAdaFriendlyNameMap()
				.entrySet()) {
			programToAdaFriendlyNameList.add(entry.getKey() + "|| "
					+ entry.getValue());
		}
		return programToAdaFriendlyNameList;
	}

	/**
	 * Gets the topic to ada friendly name list.
	 * 
	 * @return the topic to ada friendly name list
	 */
	public List<String> getTopicToAdaFriendlyNameList() {
		List<String> topicToAdaFriendlyNameList = new ArrayList<String>(10);

		for (Map.Entry<String, String> entry : getTopicToAdaFriendlyNameMap()
				.entrySet()) {
			topicToAdaFriendlyNameList.add(entry.getKey() + "|| "
					+ entry.getValue());
		}
		return topicToAdaFriendlyNameList;
	}

	/**
	 * Gets the twitter accounts.
	 * 
	 * @return the twitter accounts
	 */
	public List<String> getTwitterAccounts() {
		return twitterAccounts;
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

}
