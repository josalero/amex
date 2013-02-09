package com.bac.oee.model.ui.content.tiles;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class DocumentUITile.
 */
public class DocumentUITile extends UITile {

	/** The document list. */
	private List<DocumentContainerUI> documentList = new ArrayList<DocumentContainerUI>();

	/**
	 * Get the list of 'document' element items.
	 * 
	 * @return list
	 */
	public List<DocumentContainerUI> getDocumentList() {
		return documentList;
	}

	/**
	 * Set the list of 'document' element items.
	 * 
	 * @param list
	 *            the new document list
	 */
	public void setDocumentList(List<DocumentContainerUI> list) {
		documentList = list;
	}
}
