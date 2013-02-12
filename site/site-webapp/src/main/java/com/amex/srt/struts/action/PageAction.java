package com.amex.srt.struts.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// TODO: Auto-generated Javadoc
/**
 * PageAction encapsulates core logic and functionality that occurs across all
 * subpages.
 * 
 * @author christopher.tai@starcomworldwide.com
 */
@SuppressWarnings("serial")
public class PageAction extends AbstractBaseAction {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(PageAction.class);


	/** The survey tracking pixel map. */
	@Resource
	private Map<String, String> surveyTrackingPixelMap;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.struts.action.AbstractBaseAction#execute()
	 */
	@Override
	public String execute() {


		//String status = SERVER_ERROR_ACTION;

	
		return SUCCESS;

	}

}
