package com.bac.oee.web.model;

// TODO: Auto-generated Javadoc
/**
 * Delegation class for City. For now, this will be just a regular bean. Once we
 * inflate the database, I'll convert it into a true delegate.
 * 
 * @author christopher.tai@starcomworldwide.com
 */
public class CityUIModel {
	/*
	 * private City city;
	 * 
	 * public CityUIModel() { super(); }
	 * 
	 * public CityUIModel(City city) { super(); this.city = city; }
	 */

	/** The id. */
	private Long id;

	/** The name. */
	private String name;

	/* Auto Generated Getters / Setters */
	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
