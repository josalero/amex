package com.bac.oee.model.ui.content;

import java.util.ArrayList;
import java.util.List;

import com.bac.oee.content.Tile;
import com.bac.oee.model.ui.content.tiles.UITile;

// TODO: Auto-generated Javadoc
/**
 * The Class SectionUIData.
 */
public class SectionUIData {

	/**
	 * Instantiates a new section ui data.
	 */
	public SectionUIData() {

	}

	/** The paragraph list. */
	private List<ParagraphUIData> paragraphList = new ArrayList<ParagraphUIData>();

	/** The tile list. */
	private List<UITile> tileList = new ArrayList<UITile>();

	/** The info box list. */
	private List<InfoBoxUIData> infoBoxList = new ArrayList<InfoBoxUIData>();

	/**
	 * Gets the paragraph list.
	 * 
	 * @return the paragraph list
	 */
	public List<ParagraphUIData> getParagraphList() {
		return paragraphList;
	}

	/**
	 * Sets the paragraph list.
	 * 
	 * @param paragraphList
	 *            the new paragraph list
	 */
	public void setParagraphList(List<ParagraphUIData> paragraphList) {
		this.paragraphList = paragraphList;
	}

	/**
	 * Gets the info box list.
	 * 
	 * @return the info box list
	 */
	public List<InfoBoxUIData> getInfoBoxList() {
		return infoBoxList;
	}

	/**
	 * Sets the info box list.
	 * 
	 * @param infoBoxList
	 *            the new info box list
	 */
	public void setInfoBoxList(List<InfoBoxUIData> infoBoxList) {
		this.infoBoxList = infoBoxList;
	}

	/**
	 * Gets the tile list.
	 * 
	 * @return the tile list
	 */
	public List<UITile> getTileList() {
		return tileList;
	}

	/**
	 * Sets the tile list.
	 * 
	 * @param tileList
	 *            the new tile list
	 */
	public void setTileList(List<UITile> tileList) {
		this.tileList = tileList;
	}

}
