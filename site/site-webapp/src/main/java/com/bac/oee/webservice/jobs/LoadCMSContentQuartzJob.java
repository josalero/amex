package com.bac.oee.webservice.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

// TODO: Auto-generated Javadoc
/**
 * The Class LoadCMSContentQuartzJob.
 */
public class LoadCMSContentQuartzJob extends QuartzJobBean {

	/** The load cms content task. */
	private LoadCMSContentTask loadCMSContentTask;

	/**
	 * Sets the load cms content task.
	 * 
	 * @param loadCMSContentTask
	 *            the new load cms content task
	 */
	public void setLoadCMSContentTask(LoadCMSContentTask loadCMSContentTask) {
		this.loadCMSContentTask = loadCMSContentTask;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org
	 * .quartz.JobExecutionContext)
	 */
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {

		loadCMSContentTask.loadCMSContent();
	}
}