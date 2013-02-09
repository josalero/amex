package com.bac.oee.model.ui.content.common;

// TODO: Auto-generated Javadoc
/**
 * Schema fragment(s) for this class:
 * 
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://www.bac.com/oee/content" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="tileTypeEnum">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="image-text-tile"/>
 *     &lt;xs:enumeration value="links-tile"/>
 *     &lt;xs:enumeration value="image-tile"/>
 *     &lt;xs:enumeration value="text-tile"/>
 *     &lt;xs:enumeration value="video-tile"/>
 *     &lt;xs:enumeration value="see-it-in-action-tile"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
public enum TileTypeUIEnum {

	/** The imagetexttile. */
	IMAGETEXTTILE("image-text-tile"),
	/** The linkstile. */
	LINKSTILE("links-tile"),
	/** The imagetile. */
	IMAGETILE("image-tile"),
	/** The texttile. */
	TEXTTILE("text-tile"),
	/** The videotile. */
	VIDEOTILE("video-tile"),
	/** The seeitinactiontile. */
	SEEITINACTIONTILE("see-it-in-action-tile");

	/** The value. */
	private final String value;

	/**
	 * Instantiates a new tile type ui enum.
	 * 
	 * @param value
	 *            the value
	 */
	private TileTypeUIEnum(String value) {
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
	 * @return the tile type ui enum
	 */
	public static TileTypeUIEnum convert(String value) {
		for (TileTypeUIEnum inst : values()) {
			if (inst.xmlValue().equals(value)) {
				return inst;
			}
		}
		return null;
	}
}
