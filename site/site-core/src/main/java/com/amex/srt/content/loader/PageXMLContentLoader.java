package com.amex.srt.content.loader;

import java.io.File;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amex.srt.content.ContentItem;
import com.amex.srt.content.ContentLoadReport;
import com.amex.srt.content.ContentManager;
import com.amex.srt.content.repo.ContentRepoAccessDao;
import com.amex.srt.content.repo.NodeRef;
import com.amex.srt.content.repo.RepoFileData;

public class PageXMLContentLoader extends BaseContentItemLoader implements ContentItemTypeLoader {

	/**
	 * Logger for this class
	 */
	private static final Log log = LogFactory.getLog(PageXMLContentLoader.class);

	@Override
	public RepoFileData processContentItem(String originalContentAsXmlString, ContentItem contentItem, String filePath, File file, ContentManager contentManager, ContentLoadReport contentLoadReport) {
		RepoFileData repoFileData = super.processContentItem(originalContentAsXmlString, contentItem, filePath, file, contentManager, contentLoadReport);
		// - amex:Category (string)
		// - amex:PagePath (string)

		// for example: /xml/[LOCALE]/our-story.xml
		// Category: our-story
		// PagePath: our-story

		// for example:
		// /xml/[LOCALE]/our-story/who-we-are/our-company.xml
		// Category: our-story
		// PagePath: our-story/our-company
		String pagePath = null;
		String localeString = filePath.substring("/xml/".length());
		localeString = localeString.substring(0, localeString.indexOf('/'));

		String prefix = "/xml/" + localeString + "/";

		String category = filePath.substring(prefix.length());
		int pos = category.lastIndexOf('.');
		category = category.substring(0, pos);

		StringTokenizer tokenizer = new StringTokenizer(category, "/");
		category = tokenizer.nextToken();
		pagePath = category;
		
		while (tokenizer.hasMoreTokens()) {
			pagePath = tokenizer.nextToken();
		}

		if (!pagePath.equals(category)) {
			pagePath = category + "/" + pagePath;
		}
		
		// Test if the page with the same pagePath exists		
		NodeRef pageNodeRef = contentManager.findPageNodeRef(category, pagePath, localeString);
		
		if (pageNodeRef != null && !pageNodeRef.getPath().equals(filePath)) {			
			throw new IllegalStateException("Page with pagePath = " + pagePath + " already exists");
		}
		
		Map<String, Object> props = repoFileData.getProps();

		props.put("amex:Category", category);
		props.put("amex:PagePath", pagePath);

		pos = filePath.lastIndexOf('/');
		String parentFolderPath = filePath.substring(0, pos);
		
		log.info("   => Parent Folder = " + parentFolderPath);
		props.put("amex:ParentFolder", parentFolderPath);
		props.put("amex:URL", contentItem.getUrl());
		repoFileData.setFileType(ContentRepoAccessDao.REPO_AMEX_PAGE_TYPE);
		
		return repoFileData;
	}
}
