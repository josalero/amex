/**
 * 
 */
package com.bac.oee.model.level;

// TODO: Auto-generated Javadoc
/**
 * The Enum PageTaxonomyEnum.
 * 
 * @author mmontero
 */
public enum PageTaxonomyEnum {

	/** The page taxonomy geo. */
	PAGE_TAXONOMY_GEO("geo"),

	/** The page taxonomy topic. */
	PAGE_TAXONOMY_TOPIC("topic"),

	/** The page taxonomy program. */
	PAGE_TAXONOMY_PROGRAM("program"),

	/** The page taxonomy none. */
	PAGE_TAXONOMY_NONE("none"),

	/** The page taxonomy session value. */
	PAGE_TAXONOMY_SESSION_VALUE("tagTaxonomySessionValue");

	/** The value. */
	private final String value;

	/**
	 * Instantiates a new page taxonomy enum.
	 * 
	 * @param value
	 *            the value
	 */
	private PageTaxonomyEnum(String value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

}
