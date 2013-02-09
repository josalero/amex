package com.bac.oee.assembler;

import com.bac.oee.content.Panel;
import com.bac.oee.model.ui.content.PanelUIData;

// TODO: Auto-generated Javadoc
/**
 * The Class PanelUIDataAssembler.
 */
public class PanelUIDataAssembler implements Assembler<Panel, PanelUIData> {

	/**
	 * Instantiates a new panel ui data assembler.
	 */
	public PanelUIDataAssembler() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.assembler.Assembler#assemble(java.lang.Object)
	 */
	@Override
	public PanelUIData assemble(Panel objectToAssemble) {
		if (objectToAssemble == null) {
			return null;
		} else {
			PanelUIData panelUIData = new PanelUIData();
			panelUIData.setName(objectToAssemble.getPanelName());
			return panelUIData;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.assembler.Assembler#disAssemble(java.lang.Object)
	 */
	@Override
	public Panel disAssemble(PanelUIData objectToAssemble) {
		// TODO Auto-generated method stub
		return null;
	}

}
