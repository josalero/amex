package com.bac.oee.model.ui.content.tiles;

import com.bac.oee.model.ui.content.common.TargetUIEnum;
import com.bac.oee.model.ui.content.common.TileImageTypeUIEnum;

// TODO: Auto-generated Javadoc
/**
 * The Class ImageUITile.
 */
public class ImageUITile extends UITile {

	/** The image type. */
	private TileImageTypeUIEnum imageType;

	/** The path. */
	private String path;

	/** The alt text. */
	private String altText;

	/** The caption. */
	private String caption;

	/** The link text. */
	private String linkText;

	/** The link url. */
	private String linkUrl;

	/** The screen reader text. */
	private String screenReaderText;

	/** The target. */
	private TargetUIEnum target;

	/**
	 * Gets the image type.
	 *
	 * @return the image type
	 */
	public TileImageTypeUIEnum getImageType() {
		return imageType;
	}

	/**
	 * Sets the image type.
	 *
	 * @param imageType the new image type
	 */
	public void setImageType(TileImageTypeUIEnum imageType) {
		this.imageType = imageType;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Gets the alt text.
	 *
	 * @return the alt text
	 */
	public String getAltText() {
		return altText;
	}

	/**
	 * Sets the alt text.
	 *
	 * @param altText the new alt text
	 */
	public void setAltText(String altText) {
		this.altText = altText;
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

	/**
	 * Gets the link text.
	 *
	 * @return the link text
	 */
	public String getLinkText() {
		return linkText;
	}

	/**
	 * Sets the link text.
	 *
	 * @param linkText the new link text
	 */
	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	/**
	 * Gets the link url.
	 *
	 * @return the link url
	 */
	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * Sets the link url.
	 *
	 * @param linkUrl the new link url
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	/**
	 * Gets the screen reader text.
	 *
	 * @return the screen reader text
	 */
	public String getScreenReaderText() {
		return screenReaderText;
	}

	/**
	 * Sets the screen reader text.
	 *
	 * @param screenReaderText the new screen reader text
	 */
	public void setScreenReaderText(String screenReaderText) {
		this.screenReaderText = screenReaderText;
	}

	/**
	 * Gets the target.
	 *
	 * @return the target
	 */
	public TargetUIEnum getTarget() {
		return target;
	}

	/**
	 * Sets the target.
	 *
	 * @param target the new target
	 */
	public void setTarget(TargetUIEnum target) {
		this.target = target;
	}

}
