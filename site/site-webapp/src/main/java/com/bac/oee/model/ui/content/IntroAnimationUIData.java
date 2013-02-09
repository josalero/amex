package com.bac.oee.model.ui.content;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class IntroAnimationUIData.
 */
public class IntroAnimationUIData {

	/**
	 * Instantiates a new intro animation ui data.
	 */
	public IntroAnimationUIData() {

	}

	/** The segment text. */
	private String segmentText;

	/**
	 * Gets the segment list.
	 * 
	 * @return the segment list
	 */
	public List<SegmentUIData> getSegmentList() {
		return segmentList;
	}

	/**
	 * Sets the segment list.
	 * 
	 * @param segmentList
	 *            the new segment list
	 */
	public void setSegmentList(List<SegmentUIData> segmentList) {
		this.segmentList = segmentList;
	}

	/**
	 * Gets the segment text.
	 * 
	 * @return the segment text
	 */
	public String getSegmentText() {
		return segmentText;
	}

	/**
	 * Sets the segment text.
	 * 
	 * @param segmentText
	 *            the new segment text
	 */
	public void setSegmentText(String segmentText) {
		this.segmentText = segmentText;
	}

	/** The segment list. */
	private List<SegmentUIData> segmentList = new ArrayList<SegmentUIData>();

}
