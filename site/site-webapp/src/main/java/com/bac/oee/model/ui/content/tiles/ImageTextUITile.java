package com.bac.oee.model.ui.content.tiles;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ImageTextUITile.
 */
public class ImageTextUITile extends UITile {

	/** The text tile. */
	private TextUITile textTile;

	/** The image list. */
	private List<ImageUITile> imageList = new ArrayList<ImageUITile>();

	/**
	 * Gets the text tile.
	 *
	 * @return the text tile
	 */
	public TextUITile getTextTile() {
		return textTile;
	}

	/**
	 * Sets the text tile.
	 *
	 * @param textTile the new text tile
	 */
	public void setTextTile(TextUITile textTile) {
		this.textTile = textTile;
	}

	/**
	 * Gets the image list.
	 *
	 * @return the image list
	 */
	public List<ImageUITile> getImageList() {
		return imageList;
	}

	/**
	 * Sets the image list.
	 *
	 * @param imageList the new image list
	 */
	public void setImageList(List<ImageUITile> imageList) {
		this.imageList = imageList;
	}

}
