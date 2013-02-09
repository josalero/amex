/**
 * 
 */
package com.bac.oee.struts.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerErrorAction.
 * 
 * @author jose aleman
 */
@SuppressWarnings("serial")
public class ServerErrorAction extends AbstractBaseAction {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(ServerErrorAction.class);

	/**
	 * Instantiates a new server error action.
	 */
	public ServerErrorAction() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.struts.action.AbstractBaseAction#execute()
	 */
	@Override
	public String execute() {
		String result = super.execute();
		getServletResponse().setStatus(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		if (getServletRequest().getSession().getServletContext()
				.getAttribute("exception") != null) {
			log.error(">>>>>>>>>>>>>RUNTIME ERROR:<<<<<<<<<<<<<<<<<<<<<<<<< ",
					(Exception) getServletRequest().getSession()
							.getServletContext().getAttribute("exception"));
		}

		return result;
	}

}
