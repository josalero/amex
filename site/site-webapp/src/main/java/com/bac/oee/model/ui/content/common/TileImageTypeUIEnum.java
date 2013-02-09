package com.bac.oee.model.ui.content.common;

// TODO: Auto-generated Javadoc
/**
 * The Enum TileImageTypeUIEnum.
 */
public enum TileImageTypeUIEnum {

	/** The IMAGETYP e87 x87. */
	IMAGETYPE87X87("image-type-87x87"),
	/** The IMAGETYP e111 x167. */
	IMAGETYPE111X167("image-type-111x167"),
	/** The IMAGETYP e127 x87. */
	IMAGETYPE127X87("image-type-127x87"),
	/** The IMAGETYP e139 x139. */
	IMAGETYPE139X139("image-type-139x139"),
	/** The IMAGETYP e155 x73. */
	IMAGETYPE155X73("image-type-155x73"),
	/** The IMAGETYP e195 x. */
	IMAGETYPE195X("image-type-195x"),
	/** The IMAGETYP e242 x167. */
	IMAGETYPE242X167("image-type-242x167"),
	/** The IMAGETYP e266 x126. */
	IMAGETYPE266X126("image-type-266x126"),
	/** The IMAGETYP e440 x248. */
	IMAGETYPE440X248("image-type-440x248"),
	/** The IMAGETYP e480 x353. */
	IMAGETYPE480X353("image-type-480x353"),
	/** The IMAGETYP e878 x494. */
	IMAGETYPE878X494("image-type-878x494"),
	/** The IMAGETYP e180 x90. */
	IMAGETYPE180X90("image-type-180x90");

	/** The value. */
	private final String value;

	/**
	 * Instantiates a new tile image type ui enum.
	 * 
	 * @param value
	 *            the value
	 */
	private TileImageTypeUIEnum(String value) {
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
	 * @return the tile image type ui enum
	 */
	public static TileImageTypeUIEnum convert(String value) {
		for (TileImageTypeUIEnum inst : values()) {
			if (inst.xmlValue().equals(value)) {
				return inst;
			}
		}
		return null;
	}
}
