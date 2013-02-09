package com.bac.oee.model.json;

import com.google.gson.annotations.SerializedName;

/**
 * Comment is the Java representation of the STREAM.get JSON response.
 * 
 * @author christopher.tai@starcomworldwide.com
 */
public class Comments {
	@SerializedName("total_results")
	private String totalResults;

	/* Auto Generated Getters / Setters */
	public String getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}
}
