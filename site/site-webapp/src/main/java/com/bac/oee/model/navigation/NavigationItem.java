package com.bac.oee.model.navigation;

import java.util.List;

import com.bac.oee.content.HrefLink;
import com.bac.oee.content.Item;

// TODO: Auto-generated Javadoc
/**
 * The Class NavigationItem.
 */
public class NavigationItem {

	/** The item. */
	private Item item;

	/** The navigation items. */
	private List<NavigationItem> navigationItems;

	/** The has bottom tiles. */
	private boolean hasBottomTiles;

	/**
	 * Instantiates a new navigation item.
	 * 
	 * @param item
	 *            the item
	 */
	public NavigationItem(Item item) {
		this.item = item;
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

	/* Auto Generated Delegation Getters */
	/**
	 * Gets the banner text.
	 * 
	 * @return the banner text
	 */
	public String getBannerText() {
		return item.getBannerText();
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return item.getId();
	}

	/**
	 * Gets the link.
	 * 
	 * @return the link
	 */
	public HrefLink getLink() {
		return item.getLink();
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return item.getName();
	}

	/**
	 * Gets the nav item list.
	 * 
	 * @return the nav item list
	 */
	public List<Item> getNavItemList() {
		return item.getNavItemList();
	}

	/**
	 * Gets the item.
	 * 
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Checks if is checks for bottom tiles.
	 * 
	 * @return true, if is checks for bottom tiles
	 */
	public boolean isHasBottomTiles() {
		return hasBottomTiles;
	}

	/**
	 * Sets the checks for bottom tiles.
	 * 
	 * @param hasBottomTiles
	 *            the new checks for bottom tiles
	 */
	public void setHasBottomTiles(boolean hasBottomTiles) {
		this.hasBottomTiles = hasBottomTiles;
	}

}
