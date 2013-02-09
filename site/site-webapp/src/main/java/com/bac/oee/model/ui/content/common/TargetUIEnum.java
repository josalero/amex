package com.bac.oee.model.ui.content.common;

// TODO: Auto-generated Javadoc
/**
 * The Enum TargetUIEnum.
 */
public enum TargetUIEnum {

	/** The  top. */
	_TOP("_top"), /** The  self. */
 _SELF("_self"), /** The  blank. */
 _BLANK("_blank"), /** The  new. */
 _NEW("_new");

	/** The value. */
	private final String value;

	/**
	 * Instantiates a new target ui enum.
	 * 
	 * @param value
	 *            the value
	 */
	private TargetUIEnum(String value) {
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
	 * @return the target ui enum
	 */
	public static TargetUIEnum convert(String value) {
		for (TargetUIEnum inst : values()) {
			if (inst.xmlValue().equals(value)) {
				return inst;
			}
		}
		return null;
	}
}
