package com.bac.oee.factory;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bac.oee.content.ContentItem;
import com.bac.oee.content.ContentManager;
import com.bac.oee.content.Item;
import com.bac.oee.content.Navigation;
import com.bac.oee.content.TagContainerItem;
import com.bac.oee.content.exceptions.ContentLoaderException;
import com.bac.oee.model.navigation.NavigationData;
import com.bac.oee.model.navigation.NavigationItem;
import com.bac.oee.model.navigation.TaxonomyData;
import com.bac.oee.service.PageService;
import com.bac.oee.service.TaxonomyService;

// TODO: Auto-generated Javadoc
/**
 * The factory object that distributes navigation related objects.
 * 
 * @author christopher.tai@starcomworldwide.com
 */
@Component
public class NavigationFactory {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(NavigationFactory.class);

	/** The locale. */
	private String locale;

	/** The content manager. */
	@Autowired
	protected ContentManager contentManager;

	/** The taxonomy service. */
	@Autowired
	protected TaxonomyService taxonomyService;

	/** The page service. */
	@Autowired
	protected PageService pageService;

	/**
	 * Sets the content manager.
	 * 
	 * @param contentManager
	 *            the new content manager
	 */
	public void setContentManager(ContentManager contentManager) {
		this.contentManager = contentManager;
	}

	/**
	 * Gets the navigation data.
	 * 
	 * @param contentItem
	 *            the content item
	 * @return the navigation data
	 */
	public NavigationData getNavigationData(ContentItem contentItem) {
		if (contentItem == null || contentItem.getContent() == null) {
			return null;
		}

		Navigation navigation = (Navigation) contentItem.getContent();
		return getNavigationData(navigation);
	}

	/**
	 * Gets the navigation data.
	 * 
	 * @param navigation
	 *            the navigation
	 * @return the navigation data
	 */
	public NavigationData getNavigationData(Navigation navigation) {
		if (navigation == null) {
			return null;
		}

		// populate utility nav
		NavigationData navigationData = new NavigationData(navigation);

		/*
		 * UtilityNav utilityNav = navigationData.getUtilityNav();
		 * List<NavigationItem> utilityNavigationItems = null;
		 * 
		 * if (utilityNav.getNavItems() != null &&
		 * utilityNav.getNavItems().getNavItemList() != null) {
		 * utilityNavigationItems = new ArrayList<NavigationItem>();
		 * 
		 * for (Item item : utilityNav.getNavItems().getNavItemList()) {
		 * NavigationItem navigationItem = populateTiles(item);
		 * utilityNavigationItems.add(navigationItem); } }
		 * 
		 * NavigationItemWrapper utilityNavigationItemWrapper = new
		 * NavigationItemWrapper( utilityNav.getNavItems());
		 * utilityNavigationItemWrapper
		 * .setNavigationItems(utilityNavigationItems);
		 * 
		 * UtilityNavigationData utilityNavigationData = new
		 * UtilityNavigationData( utilityNav); utilityNavigationData
		 * .setNavigationItemWrapper(utilityNavigationItemWrapper);
		 * 
		 * // populate primary nav PrimaryNav primaryNav =
		 * navigationData.getPrimaryNav(); List<NavigationItem>
		 * primaryNavigationItems = null;
		 * 
		 * if (primaryNav.getNavItems() != null &&
		 * primaryNav.getNavItems().getNavItemList() != null) {
		 * primaryNavigationItems = new ArrayList<NavigationItem>();
		 * 
		 * for (Item item : primaryNav.getNavItems().getNavItemList()) {
		 * NavigationItem navigationItem = populateTiles(item);
		 * primaryNavigationItems.add(navigationItem); } }
		 * 
		 * NavigationItemWrapper primaryNavigationItemWrapper = new
		 * NavigationItemWrapper( primaryNav.getNavItems());
		 * primaryNavigationItemWrapper
		 * .setNavigationItems(primaryNavigationItems);
		 * 
		 * PrimaryNavigationData primaryNavigationData = new
		 * PrimaryNavigationData( primaryNav); primaryNavigationData
		 * .setNavigationItemWrapper(primaryNavigationItemWrapper);
		 * 
		 * // populate footer nav FooterNav footerNav =
		 * navigationData.getFooterNav(); List<NavigationItem>
		 * footerNavigationItems = null;
		 * 
		 * if (footerNav.getNavItems() != null &&
		 * footerNav.getNavItems().getNavItemList() != null) {
		 * footerNavigationItems = new ArrayList<NavigationItem>();
		 * 
		 * for (Item item : footerNav.getNavItems().getNavItemList()) {
		 * NavigationItem navigationItem = populateTiles(item);
		 * footerNavigationItems.add(navigationItem); } }
		 * 
		 * NavigationItemWrapper footerNavigationItemWrapper = new
		 * NavigationItemWrapper( footerNav.getNavItems());
		 * footerNavigationItemWrapper
		 * .setNavigationItems(footerNavigationItems);
		 * 
		 * FooterNavigationData footerNavigationData = new FooterNavigationData(
		 * footerNav); footerNavigationData
		 * .setNavigationItemWrapper(footerNavigationItemWrapper);
		 * 
		 * NavigationWrapper navigationWrapper = new NavigationWrapper();
		 */

		return navigationData;
	}

	/**
	 * Populate tiles.
	 * 
	 * @param item
	 *            the item
	 * @return the navigation item
	 */
	private NavigationItem populateTiles(Item item) {

		if (item == null) {
			return null;
		}

		NavigationItem navigationItem = new NavigationItem(item);

		try {

		} catch (Exception ex) {
			log.error("An unexpected error occurred while populating the tiles. "
					+ ex.toString());
		}

		return navigationItem;
	}

	/* Auto Generated Getters / Setters */
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
	 * Populate navigation data.
	 * 
	 * @param navData
	 *            the nav data
	 * @param navigation
	 *            the navigation
	 */
	public void populateNavigationData(NavigationData navData,
			Navigation navigation) {
		TaxonomyData taxonomyData = new TaxonomyData();
		populateTaxonomyData(taxonomyData);

		// navData.setStateDartTagData(retrieveMarketDartTagData("pl-state-market"));
		// navData.setMetroDartTagData(retrieveMarketDartTagData("pl-metro-market"));

		navData.setTaxonomyData(taxonomyData);
		navData.setNavigationItemList(getNavigationItemList(navigation));

	}

	/**
	 * Gets the navigation item list.
	 * 
	 * @param navigation
	 *            the navigation
	 * @return the navigation item list
	 */
	public List<NavigationItem> getNavigationItemList(Navigation navigation) {
		List<NavigationItem> navItems = new ArrayList<NavigationItem>();

		if (navigation != null && navigation.getPrimaryNav() != null
				&& navigation.getPrimaryNav().getNavItems() != null) {
			List<Item> itemList = navigation.getPrimaryNav().getNavItems()
					.getNavItemList();
			if (itemList != null && itemList.size() > 0) {
				for (Item item : itemList) {
					if (item != null) {
						/*
						 * if (item.getRightSideTiles() != null) {
						 * NavigationItem navItem = populateTiles(item);
						 * navItems.add(navItem); } else { NavigationItem
						 * navItem = new NavigationItem(item);
						 * navItems.add(navItem); }
						 */
					}
				}
			}
		}

		return navItems;
	}

	/**
	 * Populate taxonomy data.
	 * 
	 * @param taxonomyData
	 *            the taxonomy data
	 */
	private void populateTaxonomyData(TaxonomyData taxonomyData) {
		// TODO Auto-generated method stub

		taxonomyData.setGeoTaxonomy(taxonomyService.getGeoTaxonomy());
		taxonomyData.setProgramToAdaFriendlyNameMap(taxonomyService
				.getProgramToAdaFriendlyNameMap());
		taxonomyData.setTopicToAdaFriendlyNameMap(taxonomyService
				.getTopicToAdaFriendlyNameMap());

	}

}
