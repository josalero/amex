package com.bac.oee.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class PageUtils.
 */
public class PageUtils {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(PageUtils.class);

	/**
	 * Gets the page path.
	 * 
	 * @param pageJCRPath
	 *            the page jcr path
	 * @param locale
	 *            the locale
	 * @return the page path
	 */
	public static String getPagePath(String pageJCRPath, String locale) {
		String pagePath = pageJCRPath.replace("/xml/" + locale + "/", "");
		pagePath = pagePath.replace(".xml", "");
		String[] elements = pagePath.split("/");
		pagePath = elements[0];

		if (elements.length > 1) {
			pagePath += "/";
			pagePath += elements[elements.length - 1];
		}

		return pagePath;
	}

	/**
	 * Gets the category.
	 * 
	 * @param pageJcrPath
	 *            the page jcr path
	 * @param locale
	 *            the locale
	 * @return the category
	 */
	public static String getCategory(String pageJcrPath, String locale) {
		String pagePath = pageJcrPath.replace("/xml/" + locale + "/", "");
		pagePath = pagePath.replace(".xml", "");

		String[] elements = pagePath.split("/");
		pagePath = elements[0];

		if (elements.length > 1) {
			pagePath += "/";
			pagePath += elements[3];
		}

		return pagePath;
	}

	/**
	 * Gets the sub category.
	 * 
	 * @param pageJcrPath
	 *            the page jcr path
	 * @param locale
	 *            the locale
	 * @return the sub category
	 */
	public static String getSubCategory(String pageJcrPath, String locale) {
		String pagePath = pageJcrPath.replace("/xml/" + locale + "/", "");
		pagePath = pagePath.replace(".xml", "");
		String[] elements = pagePath.split("/");
		if (elements.length > 1) {
			pagePath = elements[1];
		} else {
			pagePath = StringUtils.EMPTY;
		}

		return pagePath;
	}

	/**
	 * Gets the display name.
	 * 
	 * @param name
	 *            the name
	 * @return the display name
	 */
	public static String getDisplayName(final String name) {
		StringBuilder friendlyNameBuilder = new StringBuilder();
		String friendlyName = name;
		String state = StringUtils.EMPTY;

		// Special care for states
		if (name.contains(",")) {
			state = StringUtils.substringAfter(name, ",");
			friendlyName = name.replaceAll(state, "");
		}
		String friendlyNameTokens[] = friendlyName.split(" ");

		for (String token : friendlyNameTokens) {
			friendlyNameBuilder.append(StringUtils.capitalize(token
					.toLowerCase()));
			friendlyNameBuilder.append(" ");
		}

		friendlyNameBuilder.append(state);

		return friendlyNameBuilder.toString();

	}

	/**
	 * Gets the site category.
	 * 
	 * @param pageJcrPath
	 *            the page jcr path
	 * @param locale
	 *            the locale
	 * @return the site category
	 */
	public static String getSiteCategory(String pageJcrPath, String locale) {
		String pagePath = pageJcrPath.replace("/xml/" + locale + "/", "");
		pagePath = pagePath.replace(".xml", "");

		String[] elements = pagePath.split("/");
		pagePath = elements[0];

		return pagePath;
	}

	/**
	 * Gets the site sub category.
	 * 
	 * @param pageJcrPath
	 *            the page jcr path
	 * @param locale
	 *            the locale
	 * @return the site sub category
	 */
	public static String getSiteSubCategory(String pageJcrPath, String locale) {
		String pagePath = pageJcrPath.replace("/xml/" + locale + "/", "");
		pagePath = pagePath.replace(".xml", "");

		String[] elements = pagePath.split("/");
		if (elements.length > 1) {
			pagePath = elements[elements.length - 1];
		} else {
			pagePath = null;
		}

		return pagePath;
	}

	/**
	 * Split url.
	 * 
	 * @param url
	 *            the url
	 * @return the list
	 */
	public static List<String> splitUrl(String url) {
		if (StringUtils.isEmpty(url)) {
			return null;
		}

		List<String> urlTokens = new ArrayList<String>();
		String urlPrefix = null;

		if (url.contains("http://")) {
			urlPrefix = url.substring(0, 7);
			url = url.substring(7);
		} else if (url.contains("https://")) {
			urlPrefix = url.substring(0, 8);
			url = url.substring(8);
		}

		if (StringUtils.isNotEmpty(urlPrefix)) {
			urlTokens.add(urlPrefix);
		}

		String[] urlPieces = url.split("/");

		for (int i = 0; i < urlPieces.length; i++) {
			urlTokens.add(urlPieces[i]);
		}

		return urlTokens;
	}

	/**
	 * Gets the relative url.
	 * 
	 * @param url
	 *            the url
	 * @return the relative url
	 */
	public static String getRelativeUrl(String url) {
		if (StringUtils.isEmpty(url)) {
			return null;
		}

		if (url.contains("http://")) {
			url = url.substring(7);
		} else if (url.contains("https://")) {
			url = url.substring(8);
		}

		if (url.contains("en-")) {
			url = url.substring(5);
		}

		return url;
	}
}
