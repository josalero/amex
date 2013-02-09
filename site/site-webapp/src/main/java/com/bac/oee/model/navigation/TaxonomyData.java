/**
 * 
 */
package com.bac.oee.model.navigation;

import java.util.Map;

import com.bac.oee.content.GeoTaxonomy;

// TODO: Auto-generated Javadoc
/**
 * The Class TaxonomyData.
 * 
 * @author Jose Aleman
 */
public class TaxonomyData {

	/** The geo taxonomy. */
	private GeoTaxonomy geoTaxonomy;

	/** The topic to ada friendly name map. */
	private Map<String, String> topicToAdaFriendlyNameMap;

	/** The program to ada friendly name map. */
	private Map<String, String> programToAdaFriendlyNameMap;

	/**
	 * Instantiates a new taxonomy data.
	 */
	public TaxonomyData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the geo taxonomy.
	 * 
	 * @return the geo taxonomy
	 */
	public GeoTaxonomy getGeoTaxonomy() {
		return geoTaxonomy;
	}

	/**
	 * Sets the geo taxonomy.
	 * 
	 * @param geoTaxonomy
	 *            the new geo taxonomy
	 */
	public void setGeoTaxonomy(GeoTaxonomy geoTaxonomy) {
		this.geoTaxonomy = geoTaxonomy;
	}

	/**
	 * Gets the topic to ada friendly name map.
	 * 
	 * @return the topic to ada friendly name map
	 */
	public Map<String, String> getTopicToAdaFriendlyNameMap() {
		return topicToAdaFriendlyNameMap;
	}

	/**
	 * Sets the topic to ada friendly name map.
	 * 
	 * @param topicToAdaFriendlyNameMap
	 *            the topic to ada friendly name map
	 */
	public void setTopicToAdaFriendlyNameMap(
			Map<String, String> topicToAdaFriendlyNameMap) {
		this.topicToAdaFriendlyNameMap = topicToAdaFriendlyNameMap;
	}

	/**
	 * Gets the program to ada friendly name map.
	 * 
	 * @return the program to ada friendly name map
	 */
	public Map<String, String> getProgramToAdaFriendlyNameMap() {
		return programToAdaFriendlyNameMap;
	}

	/**
	 * Sets the program to ada friendly name map.
	 * 
	 * @param programToAdaFriendlyNameMap
	 *            the program to ada friendly name map
	 */
	public void setProgramToAdaFriendlyNameMap(
			Map<String, String> programToAdaFriendlyNameMap) {
		this.programToAdaFriendlyNameMap = programToAdaFriendlyNameMap;
	}

}
