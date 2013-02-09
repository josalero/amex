package com.bac.oee.model.ui.content.tiles;

import com.bac.oee.model.ui.content.PageUIData;
import com.bac.oee.model.ui.content.common.TileTypeUIEnum;

// TODO: Auto-generated Javadoc
/**
 * The Class UITile.
 */
public class UITile extends PageUIData {

	/** The headline. */
	private String headline;

	/** The primary headline. */
	private String primaryHeadline;

	/** The short headline. */
	private String shortHeadline;

	/** The type. */
	private TileTypeUIEnum type;

	/**
	 * Get the 'headline' element value.
	 * 
	 * @return value
	 */
	public String getHeadline() {
		return headline;
	}

	/**
	 * Set the 'headline' element value.
	 * 
	 * @param headline
	 *            the new headline
	 */
	public void setHeadline(String headline) {
		this.headline = headline;
	}

	/**
	 * Get the 'primary-headline' element value.
	 * 
	 * @return value
	 */
	public String getPrimaryHeadline() {
		return primaryHeadline;
	}

	/**
	 * Set the 'primary-headline' element value.
	 * 
	 * @param primaryHeadline
	 *            the new primary headline
	 */
	public void setPrimaryHeadline(String primaryHeadline) {
		this.primaryHeadline = primaryHeadline;
	}

	/**
	 * Get the 'short-headline' element value.
	 * 
	 * @return value
	 */
	public String getShortHeadline() {
		return shortHeadline;
	}

	/**
	 * Set the 'short-headline' element value.
	 * 
	 * @param shortHeadline
	 *            the new short headline
	 */
	public void setShortHeadline(String shortHeadline) {
		this.shortHeadline = shortHeadline;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public TileTypeUIEnum getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(TileTypeUIEnum type) {
		this.type = type;
	}

}
