package com.bac.oee.model.ui.content.common;

// TODO: Auto-generated Javadoc
/**
 * The Enum PageTypeEnum.
 */
public enum PageTypeUIEnum {

	/** The index. */
	INDEX("index"), /** The page. */
	PAGE("page");

	/** The value. */
	private final String value;

	/**
	 * Instantiates a new page type enum.
	 * 
	 * @param value
	 *            the value
	 */
	private PageTypeUIEnum(String value) {
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
	 * @return the page type enum
	 */
	public static PageTypeUIEnum convert(String value) {
		for (PageTypeUIEnum inst : values()) {
			if (inst.xmlValue().equals(value)) {
				return inst;
			}
		}
		return null;
	}

}
