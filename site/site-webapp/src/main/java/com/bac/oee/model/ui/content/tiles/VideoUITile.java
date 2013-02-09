package com.bac.oee.model.ui.content.tiles;

import java.util.ArrayList;
import java.util.List;

import com.bac.oee.model.ui.content.common.HrefLinkUI;
import com.bac.oee.model.ui.content.common.VideoSizeUIEnum;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoUITile.
 */
public class VideoUITile extends UITile {

	/** The brightcove id. */
	private String brightcoveId;

	/** The related video id list. */
	private List<String> relatedVideoIdList = new ArrayList<String>();

	/** The related page list. */
	private List<HrefLinkUI> relatedPageList = new ArrayList<HrefLinkUI>();

	/** The image list. */
	private List<ImageUITile> imageList = new ArrayList<ImageUITile>();

	/** The video size. */
	private VideoSizeUIEnum videoSize;
	
	/** The transcript. */
	private String transcript;

	/**
	 * Get the 'brightcove-id' element value.
	 * 
	 * @return value
	 */
	public String getBrightcoveId() {
		return brightcoveId;
	}

	/**
	 * Set the 'brightcove-id' element value.
	 * 
	 * @param brightcoveId
	 *            the new brightcove id
	 */
	public void setBrightcoveId(String brightcoveId) {
		this.brightcoveId = brightcoveId;
	}

	/**
	 * /** Get the 'video-size' element value.
	 * 
	 * @return value
	 */
	public VideoSizeUIEnum getVideoSize() {
		return videoSize;
	}

	/**
	 * Set the 'video-size' element value.
	 * 
	 * @param videoSize
	 *            the new video size
	 */
	public void setVideoSize(VideoSizeUIEnum videoSize) {
		this.videoSize = videoSize;
	}

	/**
	 * Gets the related video id list.
	 * 
	 * @return the related video id list
	 */
	public List<String> getRelatedVideoIdList() {
		return relatedVideoIdList;
	}

	/**
	 * Sets the related video id list.
	 * 
	 * @param relatedVideoIdList
	 *            the new related video id list
	 */
	public void setRelatedVideoIdList(List<String> relatedVideoIdList) {
		this.relatedVideoIdList = relatedVideoIdList;
	}

	/**
	 * Gets the related page list.
	 *
	 * @return the related page list
	 */
	public List<HrefLinkUI> getRelatedPageList() {
		return relatedPageList;
	}

	/**
	 * Sets the related page list.
	 *
	 * @param relatedPageList the new related page list
	 */
	public void setRelatedPageList(List<HrefLinkUI> relatedPageList) {
		this.relatedPageList = relatedPageList;
	}

	/**
	 * Gets the image list.
	 *
	 * @return the image list
	 */
	public List<ImageUITile> getImageList() {
		return imageList;
	}

	/**
	 * Sets the image list.
	 *
	 * @param imageList the new image list
	 */
	public void setImageList(List<ImageUITile> imageList) {
		this.imageList = imageList;
	}

	/**
	 * Gets the transcript.
	 *
	 * @return the transcript
	 */
	public String getTranscript() {
		return transcript;
	}

	/**
	 * Sets the transcript.
	 *
	 * @param transcript the new transcript
	 */
	public void setTranscript(String transcript) {
		this.transcript = transcript;
	}


	/**
	 * Get the 'images' element value.
	 * 
	 * @return value
	 */

}
