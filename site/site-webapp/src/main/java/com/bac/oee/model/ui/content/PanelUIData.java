package com.bac.oee.model.ui.content;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class PanelUIData.
 */
public class PanelUIData {

	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new panel ui data.
	 */
	public PanelUIData() {

	}

	/** The section list. */
	private List<SectionUIData> sectionList = new ArrayList<SectionUIData>();

	/**
	 * Gets the section list.
	 * 
	 * @return the section list
	 */
	public List<SectionUIData> getSectionList() {
		return sectionList;
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
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
