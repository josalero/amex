package com.bac.oee.model.ui.content;

import com.bac.oee.content.ImageTile;
import com.bac.oee.model.ui.content.tiles.ImageUITile;

// TODO: Auto-generated Javadoc
/**
 * The Class AnchorUIData.
 */
public class AnchorUIData {

	/**
	 * Instantiates a new anchor ui data.
	 */
	public AnchorUIData() {

	}

	/** The text. */
	private String text;

	/** The image. */
	private ImageUITile image;

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

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public ImageUITile getImage() {
		return image;
	}

	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public void setImage(ImageUITile image) {
		this.image = image;
	}

}
