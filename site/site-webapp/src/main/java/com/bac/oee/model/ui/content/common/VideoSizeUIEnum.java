package com.bac.oee.model.ui.content.common;

// TODO: Auto-generated Javadoc
/**
 * The Enum VideoSizeUIEnum.
 */
public enum VideoSizeUIEnum {

	/** The VIDEOSIZ e478 x268. */
	VIDEOSIZE478X268("video-size-478x268"),
	/** The VIDEOSIZ e512 x288. */
	VIDEOSIZE512X288("video-size-512x288"),
	/** The VIDEOSIZ e640 x360. */
	VIDEOSIZE640X360("video-size-640x360"),
	/** The VIDEOSIZ e720 x480. */
	VIDEOSIZE720X480("video-size-720x480"),
	/** The VIDEOSIZ e878 x494. */
	VIDEOSIZE878X494("video-size-878x494");

	/** The value. */
	private final String value;

	/**
	 * Instantiates a new video size ui enum.
	 * 
	 * @param value
	 *            the value
	 */
	private VideoSizeUIEnum(String value) {
		this.value = value;
	}

	/**
	 * Xml value.
	 * 
	 * @return the string
	 */
	public String xmlValue() {
		return value;
	}

	/**
	 * Convert.
	 * 
	 * @param value
	 *            the value
	 * @return the video size ui enum
	 */
	public static VideoSizeUIEnum convert(String value) {
		for (VideoSizeUIEnum inst : values()) {
			if (inst.xmlValue().equals(value)) {
				return inst;
			}
		}
		return null;
	}
}
