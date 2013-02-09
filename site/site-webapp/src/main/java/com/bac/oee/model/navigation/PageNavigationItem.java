package com.bac.oee.model.navigation;

import com.bac.oee.content.HrefLink;

// TODO: Auto-generated Javadoc
/**
 * The Class PageNavigationItem.
 */
public class PageNavigationItem implements Comparable<PageNavigationItem> {

	/** The link. */
	private HrefLink link;

	/** The current. */
	private boolean current;

	/** The id. */
	private String id;

	/**
	 * Instantiates a new page navigation item.
	 * 
	 * @param link
	 *            the link
	 * @param isCurrent
	 *            the is current
	 */
	public PageNavigationItem(HrefLink link, boolean isCurrent) {
		super();
		this.link = link;
		this.current = isCurrent;
	}

	/**
	 * Gets the link.
	 * 
	 * @return the link
	 */
	public HrefLink getLink() {
		return link;
	}

	/**
	 * Sets the link.
	 * 
	 * @param link
	 *            the new link
	 */
	public void setLink(HrefLink link) {
		this.link = link;
	}

	/**
	 * Gets the current.
	 * 
	 * @return the current
	 */
	public boolean getCurrent() {
		return current;
	}

	/**
	 * Sets the current.
	 * 
	 * @param isCurrent
	 *            the new current
	 */
	public void setCurrent(boolean isCurrent) {
		this.current = isCurrent;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(PageNavigationItem o) {
		// TODO Auto-generated method stub
		return this.id.compareTo(o.getId());
	}
}
