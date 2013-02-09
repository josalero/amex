package com.bac.oee;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// TODO: Auto-generated Javadoc
/**
 * AppConstants class strictly for storing data to the web application context.
 * 
 * @author christopher.tai@starcomworldwide.com
 */

public class AppConstants {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(AppConstants.class);

	/** The Constant USER_KEY. */
	public static final String USER_KEY = "com.flag.oee.AppConstants.USER_KEY";

	/** The Constant JCR_CONTENT_LOAD_CRON_EXPRESSION. */
	public static final String JCR_CONTENT_LOAD_CRON_EXPRESSION = "*/5 * * * * MON-FRI";

	// Site View Resolver Constants
	/** The Constant JCR_CONTEXT_PATH. */
	public static final String JCR_CONTEXT_PATH = "/xml/en_US/";

	/** The Constant LOCALIZATION_TOKEN. */
	public static final String LOCALIZATION_TOKEN = "localizationToken";

	/** The Constant SECOND_LEVEL_URI_TOKEN. */
	public static final String SECOND_LEVEL_URI_TOKEN = "uriTokenLevel_2";

	/** The Constant THIRD_LEVEL_URI_TOKEN. */
	public static final String THIRD_LEVEL_URI_TOKEN = "uriTokenLevel_3";

	/** The Constant GENERIC_LEVEL_URI_TOKEN. */
	public static final String GENERIC_LEVEL_URI_TOKEN = "uriTokenLevel_";

	/** The Constant XML_FILE_PATH. */
	public static final String XML_FILE_PATH = "xmlFilePath";

	/** The Constant USER_LOCALE. */
	public static final String USER_LOCALE = "userLocale";

	/** The Constant LOCALE. */
	public static final String LOCALE = "locale";

	/** The Constant ADMIN_CONTEXT. */
	public static final String ADMIN_CONTEXT = "admin";

	// TileService Constants
	/**
	 * The Enum SiteTaxonomyType.
	 */
	public static enum SiteTaxonomyType {

		/** The our story. */
		OUR_STORY(10),

		/** The global impact. */
		GLOBAL_IMPACT(2),

		/** The partnering locally. */
		PARTNERING_LOCALLY(2),

		/** The articles. */
		ARTICLES(2),

		/** The military. */
		MILITARY(0),

		/** The press releases. */
		PRESS_RELEASES(3);

		/** The value. */
		int value;

		/**
		 * Instantiates a new site taxonomy type.
		 * 
		 * @param value
		 *            the value
		 */
		private SiteTaxonomyType(int value) {
			this.value = value;
		}

		/**
		 * Gets the value.
		 * 
		 * @return the value
		 */
		public int getValue() {
			return this.value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return super.toString().replaceAll("_", "-").toLowerCase();
		}
	};

	/** The Constant configMap. */
	private static final Map<String, Properties> configMap = new HashMap<String, Properties>();

	/**
	 * Gets the config map.
	 * 
	 * @return the config map
	 */
	public static Map<String, Properties> getConfigMap() {
		return configMap;
	}

	/**
	 * Gets the config.
	 * 
	 * @param key
	 *            the key
	 * @return the config
	 */
	public static String getConfig(String key) {
		Properties p = getProperties("appProperties");
		return p.getProperty(key);
	}

	/**
	 * Gets the properties.
	 * 
	 * @param key
	 *            the key
	 * @return the properties
	 */
	public static Properties getProperties(String key) {
		return configMap.get(key);
	}

	static {
		configMap.put("appProperties",
				AppConstants.loadProperties("/application.properties"));
	}

	/**
	 * Load properties.
	 * 
	 * @param fileName
	 *            the file name
	 * @return the properties
	 */
	private static Properties loadProperties(String fileName) {
		Properties properties = new Properties();
		InputStream inputStream = null;

		try {
			inputStream = AppConstants.class.getClassLoader()
					.getResourceAsStream(fileName);
			if (inputStream != null) {
				properties.load(inputStream);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

		return properties;
	}
}
