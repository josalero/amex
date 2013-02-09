package com.bac.oee.assembler;

import com.bac.oee.content.Infobox;
import com.bac.oee.model.ui.content.AnchorUIData;
import com.bac.oee.model.ui.content.InfoBoxUIData;
import com.bac.oee.model.ui.content.common.TileTypeUIEnum;

// TODO: Auto-generated Javadoc
/**
 * The Class InfoBoxUIDataAssembler.
 */
public class InfoBoxUIDataAssembler implements
		Assembler<Infobox, InfoBoxUIData> {

	/**
	 * Instantiates a new info box ui data assembler.
	 */
	public InfoBoxUIDataAssembler() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.assembler.Assembler#assemble(java.lang.Object)
	 */
	@Override
	public InfoBoxUIData assemble(Infobox objectToAssemble) {

		if (objectToAssemble == null) {
			return null;
		}

		InfoBoxUIData infoboxUIData = new InfoBoxUIData();

		if (objectToAssemble.getAnchor() != null) {
			infoboxUIData.setAnchor(new AnchorUIDataAssembler()
					.assemble(objectToAssemble.getAnchor()));
		}

		if (objectToAssemble.getInfoText() != null) {
			infoboxUIData.setInfoText(objectToAssemble.getInfoText());
		}

		return infoboxUIData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.assembler.Assembler#disAssemble(java.lang.Object)
	 */
	@Override
	public Infobox disAssemble(InfoBoxUIData objectToAssemble) {
		// TODO Auto-generated method stub
		return null;
	}

}
