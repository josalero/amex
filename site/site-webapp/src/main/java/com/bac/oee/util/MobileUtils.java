package com.bac.oee.util;

import javax.servlet.http.HttpServletRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class MobileUtils.
 * 
 * @author Jose Aleman
 */
public class MobileUtils {

	/** The Constant MOBILE_DEVICES. */
	private final static String[] MOBILE_DEVICES = { "midp", "j2me", "avantg",
			"docomo", "novarra", "palmos", "palmsource", "240x320", "opwv",
			"chtml", "pda", "windows ce", "mmp/", "blackberry", "mib/",
			"symbian", "wireless", "nokia", "hand", "mobi", "phone", "cdm",
			"up.b", "audio", "SIE-", "SEC-", "samsung", "HTC", "mot-", "mitsu",
			"sagem", "sony", "alcatel", "lg", "erics", "vx", "NEC", "philips",
			"mmm", "xx", "panasonic", "sharp", "wap", "sch", "rover", "pocket",
			"benq", "java", "pt", "pg", "vox", "amoi", "bird", "compal", "kg",
			"voda", "sany", "kdd", "dbt", "sendo", "sgh", "gradi", "jb",
			"moto", "webos" };

	/**
	 * Detect mobile browser.
	 * 
	 * @param request
	 *            the request
	 * @return true, if successful
	 */
	public static boolean detectMobileBrowser(HttpServletRequest request) {

		if (request.getHeader("X-WAP-PROFILE") != null) {
			return true;
		} else if (request.getHeader("user-agent") != null) {
			for (int i = 0; i < MOBILE_DEVICES.length; i++) {
				if ((request.getHeader("user-agent").toLowerCase())
						.indexOf(MOBILE_DEVICES[i]) != -1) {
					return true;
				}
			}
		} else if (request.getHeader("ACCEPT") != null
				&& ((request.getHeader("ACCEPT")).toLowerCase()).indexOf("wap") != -1) {
			return true;
		}
		return false;
	}
}