package com.bac.oee.assembler;

import com.bac.oee.content.Infobox;
import com.bac.oee.content.Paragraph;
import com.bac.oee.content.Section;
import com.bac.oee.model.ui.content.SectionUIData;

// TODO: Auto-generated Javadoc
/**
 * The Class SectionUIDataAssembler.
 */
public class SectionUIDataAssembler implements
		Assembler<Section, SectionUIData> {

	/**
	 * Instantiates a new section ui data assembler.
	 */
	public SectionUIDataAssembler() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.assembler.Assembler#assemble(java.lang.Object)
	 */
	@Override
	public SectionUIData assemble(Section objectToAssemble) {

		if (objectToAssemble == null) {
			return null;
		}

		SectionUIData sectionUiData = new SectionUIData();

		// assemble paragraph
		if (objectToAssemble.getParagraphList() != null) {
			for (Paragraph paragraph : objectToAssemble.getParagraphList()) {
				sectionUiData.getParagraphList().add(
						new ParagraphUIDataAssembler().assemble(paragraph));
			}
		}
		// assemble infobox
		if (objectToAssemble.getInfoboxList() != null) {
			for (Infobox infobox : objectToAssemble.getInfoboxList()) {
				sectionUiData.getInfoBoxList().add(
						new InfoBoxUIDataAssembler().assemble(infobox));
			}
		}

		return sectionUiData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.assembler.Assembler#disAssemble(java.lang.Object)
	 */
	@Override
	public Section disAssemble(SectionUIData objectToAssemble) {
		return null;
	}

}
