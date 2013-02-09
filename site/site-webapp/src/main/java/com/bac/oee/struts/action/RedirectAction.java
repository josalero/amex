/**
 * 
 */
package com.bac.oee.struts.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bac.oee.struts.ViewConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class RedirectAction.
 * 
 * @author Jose Aleman
 */
public class RedirectAction extends AbstractBaseAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(RedirectAction.class);

	/**
	 * Instantiates a new redirect action.
	 */
	public RedirectAction() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.struts.action.AbstractBaseAction#execute()
	 */
	@Override
	public String execute() {
		String url = (String) getServletRequest().getAttribute(
				ViewConstants.REDIRECT_URL);

		log.debug("URL " + url);

		return SUCCESS;
	}

}
