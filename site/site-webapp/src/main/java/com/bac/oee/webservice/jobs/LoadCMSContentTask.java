package com.bac.oee.webservice.jobs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bac.oee.content.ContentLoader;

// TODO: Auto-generated Javadoc
/**
 * The Class LoadCMSContentTask.
 */
public class LoadCMSContentTask {

	/** Logger for this class. */
	private static final Log log = LogFactory.getLog(LoadCMSContentTask.class);

	/** The content loader. */
	@Autowired
	private ContentLoader contentLoader;

	/**
	 * Load cms content.
	 */
	public void loadCMSContent() {

		try {

			log.info("Loading added, updated, deleted CMS content ...");

			contentLoader.refreshContent();
		} catch (Exception exception) {
			log.error(exception);
		}
	}
}
