package com.bac.oee.model.ui.content.common;

// TODO: Auto-generated Javadoc
/**
 * The Class HrefLinkUI.
 */
public class HrefLinkUI {

	/** The link text. */
	private String linkText;

	/** The link url. */
	private String linkUrl;

	/** The link target. */
	private TargetUIEnum linkTarget;

	/** The screen reader text. */
	private String screenReaderText;

	/**
	 * Get the 'link-text' element value.
	 * 
	 * @return value
	 */
	public String getLinkText() {
		return linkText;
	}

	/**
	 * Set the 'link-text' element value.
	 * 
	 * @param linkText
	 *            the new link text
	 */
	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	/**
	 * Get the 'link-url' element value.
	 * 
	 * @return value
	 */
	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * Set the 'link-url' element value.
	 * 
	 * @param linkUrl
	 *            the new link url
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	/**
	 * Get the 'link-target' element value.
	 * 
	 * @return value
	 */
	public TargetUIEnum getLinkTarget() {
		return linkTarget;
	}

	/**
	 * Set the 'link-target' element value.
	 * 
	 * @param linkTarget
	 *            the new link target
	 */
	public void setLinkTarget(TargetUIEnum linkTarget) {
		this.linkTarget = linkTarget;
	}

	/**
	 * Get the 'screen-reader-text' element value.
	 * 
	 * @return value
	 */
	public String getScreenReaderText() {
		return screenReaderText;
	}

	/**
	 * Set the 'screen-reader-text' element value.
	 * 
	 * @param screenReaderText
	 *            the new screen reader text
	 */
	public void setScreenReaderText(String screenReaderText) {
		this.screenReaderText = screenReaderText;
	}
}
