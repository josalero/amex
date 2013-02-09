package com.bac.oee.model.ui.content.tiles;

import com.bac.oee.content.ImageTile;

// TODO: Auto-generated Javadoc
/**
 * The Class DocumentUITile.
 */
public class SeeItInActionUITile extends UITile {

	/** The logo. */
	private ImageUITile logo;

	/** The text. */
	private String text;

	/** The name. */
	private String name;

	/** The location. */
	private String location;
	
	/** The location. */
	private String caption;


	/**
	 * Gets the text.
	 * 
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text.
	 * 
	 * @param text
	 *            the new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.model.ui.content.PageUIData#getName()
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.model.ui.content.PageUIData#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the location.
	 * 
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 * 
	 * @param location
	 *            the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets the logo.
	 *
	 * @return the logo
	 */
	public ImageUITile getLogo() {
		return logo;
	}

	/**
	 * Sets the logo.
	 *
	 * @param logo the new logo
	 */
	public void setLogo(ImageUITile logo) {
		this.logo = logo;
	}

	/**
	 * Gets the caption.
	 *
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * Sets the caption.
	 *
	 * @param caption the new caption
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

}
