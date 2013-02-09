package com.bac.oee.assembler;

import com.bac.oee.content.Paragraph;
import com.bac.oee.model.ui.content.ParagraphUIData;

// TODO: Auto-generated Javadoc
/**
 * The Class ParagraphUIDataAssembler.
 */
public class ParagraphUIDataAssembler implements
		Assembler<Paragraph, ParagraphUIData> {

	/**
	 * Instantiates a new paragraph ui data assembler.
	 */
	public ParagraphUIDataAssembler() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.assembler.Assembler#assemble(java.lang.Object)
	 */
	@Override
	public ParagraphUIData assemble(Paragraph objectToAssemble) {
		if (objectToAssemble == null) {
			return null;
		}

		ParagraphUIData paragraphUIData = new ParagraphUIData();
		paragraphUIData.setContent(objectToAssemble.getContent());
		paragraphUIData.setHeading(objectToAssemble.getHeading());

		return paragraphUIData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.assembler.Assembler#disAssemble(java.lang.Object)
	 */
	@Override
	public Paragraph disAssemble(ParagraphUIData objectToAssemble) {
		// TODO Auto-generated method stub
		return null;
	}

}
