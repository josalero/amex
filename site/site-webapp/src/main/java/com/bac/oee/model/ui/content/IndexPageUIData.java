package com.bac.oee.model.ui.content;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class IndexPageUIData.
 */
public class IndexPageUIData extends PageUIData {

	/** The intro animation. */
	private List<IntroAnimationUIData> introAnimationList = new ArrayList<IntroAnimationUIData>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bac.oee.model.ui.content.PageUIData#getIntroAnimationList()
	 */
	public List<IntroAnimationUIData> getIntroAnimationList() {
		return introAnimationList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bac.oee.model.ui.content.PageUIData#setIntroAnimationList(java.util
	 * .List)
	 */
	public void setIntroAnimationList(
			List<IntroAnimationUIData> introAnimationList) {
		this.introAnimationList = introAnimationList;
	}

}
