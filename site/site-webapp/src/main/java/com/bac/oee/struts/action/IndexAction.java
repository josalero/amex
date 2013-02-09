/*
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bac.oee.struts.action;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectWriter;

import com.bac.oee.content.ContentBase;
import com.bac.oee.content.ContentItem;
import com.bac.oee.content.Indexpage;
import com.bac.oee.content.Page;
import com.bac.oee.struts.ViewConstants;
import com.opensymphony.xwork2.conversion.annotations.Conversion;

// TODO: Auto-generated Javadoc
/**
 * IndexAction is the action/controller class for the homepage.
 * 
 * @author christopher.tai@starcomworldwide.com
 */
@SuppressWarnings({ "serial", "unused" })
@Conversion()
public class IndexAction extends AbstractBaseAction {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(IndexAction.class);

	/** The news feed filtering options. */
	private List<String> newsFeedFilteringOptions;

	/** The survey tracking pixel map. */
	@Resource
	private Map<String, String> surveyTrackingPixelMap;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.struts.action.AbstractBaseAction#execute()
	 */
	public String execute() {

		super.execute();

		log.info("##### IndexAction: Entry ... requestURI = "
				+ getServletRequest().getRequestURI() + " #####");

		try {

			Locale localeObject = getServletRequest().getLocale();
			String language = localeObject.getLanguage();
			String country = localeObject.getCountry();

			if (language.equals("en") && StringUtils.isEmpty(country)) {
				country = "US";
			}

			ContentItem contentItem = (ContentItem) getServletRequest()
					.getAttribute(ViewConstants.PAGE_CONTENT_ITEM);

			boolean hasSpotlightTile = false;
			String spotlightTileId = null;

			if (contentItem != null) {

				ContentBase contentBase = contentItem.getContent();

				if (contentBase instanceof Page) {

					Page page = (Page) contentBase;

					/*
					 * if (page instanceof Homepage) { Homepage homepage =
					 * (Homepage) page; SpotlightTile spotlightTile =
					 * homepage.getSpotlightTile(); hasSpotlightTile =
					 * spotlightTile.getEnabled(); spotlightTileId =
					 * spotlightTile.getTileId(); }
					 */
				}

			}

			String locale = language + "_" + country;

			getNewsFeedFilteringData();
			HttpSession session = getServletRequest().getSession();
			String brand = getServletRequest().getParameter("brand");

			if (brand == null) {
				brand = (String) session.getAttribute("brand");

				if (brand == null) {
					brand = "bank-of-america";
				}
			}

			getServletRequest().setAttribute(
					ViewConstants.PAGE_CANONICAL_URL,
					getServletRequest().getScheme() + "://"
							+ getServletRequest().getServerName() + "/");

			return SUCCESS;
		}

		catch (Exception e) {
			log.error("Could not carry out the Struts IndexAction execution!",
					e);
			return SERVER_ERROR_ACTION;
		}
	}

	/**
	 * To json string.
	 * 
	 * @param object
	 *            the object
	 * @return the string
	 */
	private String toJSONString(Object object) {

		StringWriter stringWriter = new StringWriter();
		ObjectWriter objectWriter = jsonObjectMapper.writer();

		String jsonString = "null";

		try {

			objectWriter.writeValue(stringWriter, object);
			jsonString = stringWriter.toString();
			stringWriter.close();
		} catch (Exception exception) {
			log.error(exception);
		}

		return jsonString;
	}

	/**
	 * Gets the news feed filtering data.
	 * 
	 * @return the news feed filtering data
	 */
	private void getNewsFeedFilteringData() {
		this.newsFeedFilteringOptions = new ArrayList<String>();
		this.newsFeedFilteringOptions.add("Date");
	}

	/**
	 * Gets the news feed filtering options.
	 * 
	 * @return the news feed filtering options
	 */
	public List<String> getNewsFeedFilteringOptions() {
		return this.newsFeedFilteringOptions;
	}

	/**
	 * Sets the news feed filtering options.
	 * 
	 * @param newsFeedFilteringOptions
	 *            the new news feed filtering options
	 */
	public void setNewsFeedFilteringOptions(
			List<String> newsFeedFilteringOptions) {
		this.newsFeedFilteringOptions = newsFeedFilteringOptions;
	}
}
