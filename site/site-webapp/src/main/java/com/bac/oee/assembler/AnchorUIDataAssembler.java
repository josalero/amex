package com.bac.oee.assembler;

import com.bac.oee.content.Anchor;
import com.bac.oee.content.Section;
import com.bac.oee.model.ui.content.AnchorUIData;
import com.bac.oee.model.ui.content.SectionUIData;

// TODO: Auto-generated Javadoc
/**
 * The Class AnchorUIDataAssembler.
 */
public class AnchorUIDataAssembler implements Assembler<Anchor, AnchorUIData> {

	/**
	 * Instantiates a new anchor ui data assembler.
	 */
	public AnchorUIDataAssembler() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.assembler.Assembler#assemble(java.lang.Object)
	 */
	@Override
	public AnchorUIData assemble(Anchor objectToAssemble) {

		if (objectToAssemble == null) {
			return null;
		}

		AnchorUIData anchorUIData = new AnchorUIData();
		anchorUIData.setImage(new UITileAssembler()
				.assembleImageUITile(objectToAssemble.getImage()));
		anchorUIData.setText(objectToAssemble.getText());
		return anchorUIData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.assembler.Assembler#disAssemble(java.lang.Object)
	 */
	@Override
	public Anchor disAssemble(AnchorUIData objectToAssemble) {
		return null;
	}

}
