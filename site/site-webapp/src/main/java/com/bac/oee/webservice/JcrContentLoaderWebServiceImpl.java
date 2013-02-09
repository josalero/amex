package com.bac.oee.webservice;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.bac.oee.content.ContentLoader;

// TODO: Auto-generated Javadoc
/**
 * JcrContentLoaderWebServiceImpl will retrieve deployed files from a
 * destination directory, load it into JCR, and purge the cache.
 * 
 * @author christopher.tai@starcomworldwide.com
 */
@WebService(endpointInterface = "com.bac.oee.webservice.ContentLoaderWebService")
public class JcrContentLoaderWebServiceImpl implements ContentLoaderWebService {

	/** Logger for this class. */
	private static final Logger log = Logger
			.getLogger(JcrContentLoaderWebServiceImpl.class);

	/** The content loader. */
	private ContentLoader contentLoader;

	/** The loading is in progress. */
	private static boolean loadingIsInProgress = false;

	/**
	 * Sets the content loader.
	 * 
	 * @param contentLoader
	 *            the new content loader
	 */
	public void setContentLoader(ContentLoader contentLoader) {
		this.contentLoader = contentLoader;
	}

	/**
	 * Sets the in progress.
	 */
	private static synchronized void setInProgress() {
		loadingIsInProgress = true;
	}

	/**
	 * Checks if is in progress.
	 * 
	 * @return true, if is in progress
	 */
	private static synchronized boolean isInProgress() {
		return loadingIsInProgress;
	}

	/**
	 * Sets the done.
	 */
	private static synchronized void setDone() {
		loadingIsInProgress = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.webservice.ContentLoaderWebService#refreshContent()
	 */
	public void refreshContent() {

		if (isInProgress()) {
			return;
		}

		setInProgress();

		try {

			contentLoader.refreshContent();
		} catch (Exception exception) {

			log.error("Unable to refresh content", exception);
		} finally {
			setDone();
		}
	}
}
