package com.amex.srt.content.loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amex.srt.content.ContentItem;
import com.amex.srt.content.ContentLoadReport;
import com.amex.srt.content.ContentManager;
import com.amex.srt.content.Metadata;
import com.amex.srt.content.repo.ContentRepoAccessDao;
import com.amex.srt.content.repo.RepoFileData;

public class BaseContentItemLoader implements ContentItemTypeLoader {
	/**
	 * Logger for this class
	 */
	private static final Log log = LogFactory.getLog(BaseContentItemLoader.class);

	@Override
	public RepoFileData processContentItem(String originalContentAsXmlString, ContentItem contentItem, String filePath,
			File file, ContentManager contentManager, ContentLoadReport contentLoadReport) {

		Map<String, Object> props = new HashMap<String, Object>();

		String localeString = filePath.substring("/xml/".length());
		localeString = localeString.substring(0, localeString.indexOf('/'));

		props.put("amex:Locale", localeString);
		props.put("amex:Type", contentItem.getContentType().name());
		props.put("amex:CMS_ID", contentItem.getCmsId());

		Metadata metadata = contentItem.getMetadata();

		if (metadata != null) {
			setProperty(props, "amex:PublishedDate", metadata.getPublishedDate());
			setProperty(props, "amex:GeoTaxonomy", getGeoTaxonomyValuesList(metadata.getGeoTaxonomy()));
		}

		RepoFileData repoFileData = null;

		try {

			repoFileData = new RepoFileData(file, ContentRepoAccessDao.REPO_AMEX_CONTENT_ITEM_TYPE, props);

			repoFileData.setOriginalContentAsXmlString(originalContentAsXmlString);
		}
		catch (FileNotFoundException fileNotFoundException) {
			log.error(fileNotFoundException);
		}

		return repoFileData;
	}

	protected List<String> getValuesList(String multiValuePropertyAsString) {

		List<String> result = null;

		if (multiValuePropertyAsString != null) {

			result = new ArrayList<String>();

			String[] elements = multiValuePropertyAsString.split(",");

			for (int idx = 0; idx < elements.length; ++idx) {
				result.add(elements[idx]);
			}
		}

		return result;
	}

	protected List<String> getGeoTaxonomyValuesList(String multiValuePropertyAsString) {
		
		List<String> results = null;

		if (StringUtils.isNotEmpty(multiValuePropertyAsString)) {
		
			results = new ArrayList<String>();
			String[] elements = multiValuePropertyAsString.split(",");

			for (int i = 0; i < elements.length; i++) {
				
				String cleanElement = null;
				
				if (StringUtils.isNotEmpty(elements[i])) {
			
					cleanElement = elements[i].trim();
					
					// Get region and country token
					String regionAndCountryToken = getRegionAndCountryToken(cleanElement);

					if (StringUtils.isNotEmpty(regionAndCountryToken)) {
						
						results.add(regionAndCountryToken);
						// Get region token
						String regionToken = getRegionToken(regionAndCountryToken);

						if (StringUtils.isNotEmpty(regionToken)) {
							results.add(regionToken);
						}
					}										
				}

				results.add(cleanElement);
			}
		}

		return results;
	}

	private String getRegionAndCountryToken(String geoTaxonomyString) {
		
		if (StringUtils.isEmpty(geoTaxonomyString)) {
			return null;
		}

		String[] taxonomyTokens = geoTaxonomyString.split("/");
		String regionAndCountryToken = null;

		try {
			regionAndCountryToken = taxonomyTokens[0] + "/" + taxonomyTokens[1];
		}
		catch (ArrayIndexOutOfBoundsException e) {
			log.error("Unable to assemble regionAndCountry token!", e);
		}
		catch (Exception e) {
			log.error("Unable to assemble regionAndCountry token!", e);
		}

		return regionAndCountryToken;
	}

	private String getRegionToken(String regionAndCountryToken) {
		
		if (StringUtils.isEmpty(regionAndCountryToken)) {
			return null;
		}

		String[] taxonomyTokens = regionAndCountryToken.split("/");
		String regionToken = null;

		try {
			regionToken = taxonomyTokens[0];
		}
		catch (ArrayIndexOutOfBoundsException e) {
			log.error("Unable to assemble regionAndCountry token!", e);
		}
		catch (Exception e) {
			log.error("Unable to assemble regionAndCountry token!", e);
		}

		return regionToken;
	}

	protected void setProperty(Map<String, Object> props, String propName, Object propValue) {

		if (propValue != null) {
			props.put(propName, propValue);
		}
	}
}
