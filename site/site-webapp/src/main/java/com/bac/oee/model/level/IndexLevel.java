/**
 * 
 */
package com.bac.oee.model.level;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.bac.oee.assembler.PageUIDataAssembler;
import com.bac.oee.assembler.PanelUIDataAssembler;
import com.bac.oee.assembler.SectionUIDataAssembler;
import com.bac.oee.assembler.UITileAssembler;
import com.bac.oee.content.ContentItem;
import com.bac.oee.content.Indexpage;
import com.bac.oee.content.Page;
import com.bac.oee.content.Panel;
import com.bac.oee.content.Panels;
import com.bac.oee.content.Section;
import com.bac.oee.content.Sections;
import com.bac.oee.content.Tile;
import com.bac.oee.model.PageData;
import com.bac.oee.model.ui.content.IndexPageUIData;
import com.bac.oee.model.ui.content.PageUIData;
import com.bac.oee.model.ui.content.PanelUIData;
import com.bac.oee.model.ui.content.SectionUIData;
import com.bac.oee.model.ui.content.common.PageTypeUIEnum;
import com.bac.oee.model.ui.content.tiles.UITile;

// TODO: Auto-generated Javadoc
/**
 * The Class IndexLevel.
 * 
 * @author Jose Aleman
 */
@Component
public class IndexLevel extends PageBaseLevel {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(IndexLevel.class);

	/**
	 * Instantiates a new index level.
	 */
	public IndexLevel() {

	}

	/**
	 * Instantiates a new index level.
	 * 
	 * @param contentItem
	 *            the content item
	 * @param locale
	 *            the locale
	 */
	public IndexLevel(ContentItem contentItem, String locale) {
		super(contentItem, locale);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.model.level.PageBaseLevel#init(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	public void init(HttpServletRequest servletRequest) {
		super.init(servletRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.model.level.PageBaseLevel#onBeforeRender()
	 */
	@Override
	protected void onBeforeRender() {
		pageData = new PageData(getContentItem());

		PageUIData pageUIData = new PageUIDataAssembler()
				.assemble(getContentItem());

		assemblePages(pageUIData,
				pageService.getChildContentItems(getContentItem()));

		pageData.setIndexPageUIData((IndexPageUIData) pageUIData);

	}

	/**
	 * Assemble pages.
	 * 
	 * @param pageUIData
	 *            the page ui data
	 * @param pageContentItems
	 *            the page content items
	 */
	public void assemblePages(PageUIData pageUIData,
			List<ContentItem> pageContentItems) {

		for (ContentItem pageContentItem : pageContentItems) {

			PageUIData subPageUIData = new PageUIDataAssembler()
					.assemble(pageContentItem);

			subPageUIData.setParentPage(pageUIData);

			Page page = (Page) pageContentItem.getContent();

			assemblePanels(subPageUIData, page.getPanels());

			pageUIData.getPageList().add(subPageUIData);

		}

	}

	/**
	 * Assemble sections.
	 * 
	 * @param pageUIData
	 *            the page ui data
	 * @param panelUIData
	 *            the panel ui data
	 * @param sections
	 *            the sections
	 */
	public void assembleSections(PageUIData pageUIData,
			PanelUIData panelUIData, Sections sections) {

		if( sections.getSectionList() != null ){
			for (Section section : sections.getSectionList()) {
				SectionUIData sectionUIData = new SectionUIDataAssembler()
						.assemble(section);
				assembleTiles(pageUIData, sectionUIData, section);
				panelUIData.getSectionList().add(sectionUIData);
			}
		}
	}

	/**
	 * Assemble tiles.
	 * 
	 * @param pageUIData
	 *            the page ui data
	 * @param sectionUIData
	 *            the section ui data
	 * @param section
	 *            the section
	 */
	public void assembleTiles(PageUIData pageUIData,
			SectionUIData sectionUIData, Section section) {

		if (section.getTiles() != null && section.getTiles().getTileIdList() != null ) {
			for (String tileId : section.getTiles().getTileIdList()) {
				ContentItem contentItem = pageService.retrieveTile(tileId,
						getLocale());
				if (contentItem != null) {
					if (contentItem.getContent() instanceof Tile) {
						UITile uiTile = new UITileAssembler()
								.assemble(contentItem);
						if (uiTile != null) {
							uiTile.setParentPage(pageUIData);
							sectionUIData.getTileList().add(uiTile);
						}
					}
				}
			}
		}
	}

	/**
	 * Assemble panels.
	 * 
	 * @param pageUIData
	 *            the page ui data
	 * @param panels
	 *            the panels
	 */
	public void assemblePanels(PageUIData pageUIData, Panels panels) {

		if( panels.getPanelList() != null ){
			for (Panel panel : panels.getPanelList()) {
				PanelUIData panelUIData = new PanelUIDataAssembler()
						.assemble(panel);
	
				assembleSections(pageUIData, panelUIData, panel.getSections());
				pageUIData.getPanelList().add(panelUIData);
			}
		}
	}

	/**
	 * returns a Well Formatted Date String.
	 * 
	 * @return the published date
	 */
	private String getPublishedDate() {
		SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy");

		String dateString;
		dateString = outputFormat.format(getContentItem().getMetadata()
				.getPublishedDate());

		return dateString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bac.oee.model.level.PageBaseLevel#setFrom(com.bac.oee.model.level
	 * .PageBaseLevel)
	 */
	@Override
	public void setFrom(PageBaseLevel setFromObject) {

		super.setFrom(setFromObject);
	}
}
