package com.bac.oee.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bac.oee.struts.ViewConstants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

// TODO: Auto-generated Javadoc
/**
 * The Class TranscriptAction.
 * 
 * @author Jose Aleman
 */
@SuppressWarnings("serial")
public class TranscriptAction extends ActionSupport {

	/**
	 * Instantiates a new transcript action.
	 */
	public TranscriptAction() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() {
		// TODO Auto-generated method stub

		ActionContext actionContext = ActionContext.getContext();

		HttpServletRequest httpServletRequest = (HttpServletRequest) actionContext
				.get(ServletActionContext.HTTP_REQUEST);

		String videoRefOrId = httpServletRequest.getParameter("videoId")
				.toString();

		actionContext.put(ViewConstants.VIDEO_TRANSCRIPT, videoRefOrId);
		return SUCCESS;
	}

}
