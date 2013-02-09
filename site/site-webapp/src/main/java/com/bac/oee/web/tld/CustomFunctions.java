package com.bac.oee.web.tld;

// TODO: Auto-generated Javadoc
/**
 * *.
 * 
 * @author Hellen Quesada
 */
public class CustomFunctions {

	/**
	 * Instantiates a new custom functions.
	 */
	private CustomFunctions() {
	}

	/**
	 * Replace all.
	 * 
	 * @param text
	 *            the text
	 * @param pattern
	 *            the pattern
	 * @param replacement
	 *            the replacement
	 * @return the string
	 */
	public static String replaceAll(String text, String pattern,
			String replacement) {
		return text.replaceAll(pattern, replacement);
	}

}
