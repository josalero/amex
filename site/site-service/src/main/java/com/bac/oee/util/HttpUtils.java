package com.bac.oee.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * HttpUtils exposes common HTTP related utility methods and functionality.
 * 
 * @author christopher.tai@starcomworldwide.com
 */
public class HttpUtils {
	private static final Log log = LogFactory.getLog(HttpUtils.class);

	public static String toQuerystring(Map<?, ?> params) {
		StringBuffer stringBuffer = new StringBuffer();

		if (params != null && params.size() > 0) {
			stringBuffer.append("?");

			for (Iterator<?> iterator = params.keySet().iterator(); iterator
					.hasNext();) {
				Object key = iterator.next();

				try {
					stringBuffer.append(URLEncoder.encode(key.toString(),
							"UTF-8"));
					stringBuffer.append("=");
					stringBuffer.append(URLEncoder.encode(params.get(key)
							.toString(), "UTF-8"));

					if (iterator.hasNext()) {
						stringBuffer.append("&");
					}
				} catch (UnsupportedEncodingException e) {
					log.error(
							"Unable to encode the querystring value to UTF-8!",
							e);
				}
			}
		}

		return stringBuffer.toString();
	}

	public static Map<String, String> getQueryStringMap(String queryString) {
		if (StringUtils.isEmpty(queryString)) {
			return null;
		}

		String[] keyValuePairs = queryString.split("&");

		if (ArrayUtils.isEmpty(keyValuePairs)) {
			return null;
		}

		Map<String, String> map = new LinkedHashMap<String, String>();

		for (int i = 0; i < keyValuePairs.length; i++) {
			String keyValuePair = keyValuePairs[i];
			String[] mapEntry = keyValuePair.split("=");

			if (!ArrayUtils.isEmpty(mapEntry)) {
				map.put(mapEntry[0], mapEntry[1]);
			}
		}

		return map;
	}

	public static String getQueryStringValue(
			Map<String, String> queryStringMap, String key) {
		String value = null;

		for (Map.Entry<String, String> entry : queryStringMap.entrySet()) {
			if (entry.getKey().equals(key)) {
				value = entry.getValue();
				break;
			}
		}

		return value;
	}

	/**
	 * Makes an HTTP request out to the endpoint and returns a response back.
	 * 
	 * @param endpoint
	 * @return
	 * @throws Exception
	 */
	public static String makeHttpRequest(String endpoint) throws Exception {
		if (StringUtils.isEmpty(endpoint)) {
			return null;
		}

		String result = null;

		try {
			// open the connection
			URL url = new URL(endpoint);
			URLConnection urlConnection = url.openConnection();

			// get the response
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(urlConnection.getInputStream()));
			StringBuilder stringBuilder = new StringBuilder();
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}

			bufferedReader.close();
			result = stringBuilder.toString();
		} catch (Exception e) {
			log.error("Unable to create an HTTP request out to the URL: "
					+ endpoint + "!", e);
			throw new Exception(
					"Unable to create an HTTP request out to the URL: "
							+ endpoint + "!", e);
		}

		return result;
	}
	
	public static InputStream getStream(String endpoint) throws Exception {
		if (StringUtils.isEmpty(endpoint)) {
			return null;
		}
		
		try {
			// open the connection
			URL url = new URL(endpoint);
			URLConnection urlConnection = url.openConnection();
			return urlConnection.getInputStream();
		} catch (Exception e) {
			log.error("Unable to create an HTTP request out to the URL: "
					+ endpoint + "!", e);
			throw new Exception(
					"Unable to create an HTTP request out to the URL: "
							+ endpoint + "!", e);
		}
	}
}
