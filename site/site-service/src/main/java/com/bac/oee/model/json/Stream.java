package com.bac.oee.model.json;

import com.google.gson.annotations.SerializedName;

/**
 * Stream is the Java representation of the STREAM.get JSON response.
 * 
 * @author christopher.tai@starcomworldwide.com
 */
public class Stream {	
	@SerializedName("stream_id")
	private String streamId;
	@SerializedName("stream_post_id")
	private String streamPostId;
	@SerializedName("content_name")
	private String contentName;
	@SerializedName("description")
	private String description;
	@SerializedName("thumb")
	private String thumb;
	@SerializedName("caption")
	private String caption;
	@SerializedName("link")
	private String link;
	@SerializedName("user_source_id")
	private String userSourceId;
	@SerializedName("attribution_author")
	private String author;
	@SerializedName("attribution_url")
	private String url;
	@SerializedName("attribution_avatar")
	private String avatar;
	
	private Comments comments;
	
	@SerializedName("timestamp")
	private String timeStamp;
	@SerializedName("date_added")
	private String dateAdded;
	@SerializedName("formatted_date")
	private String formattedDate;
	@SerializedName("hand_picked")
	private String handPicked;
	@SerializedName("user_submitted")
	private String userSubmitted;
	@SerializedName("status")
	private String status;
	@SerializedName("show_retweet")
	private String showRetweet;
	
	/* Auto Generated Getters / Setters */
	public String getStreamId() {
		return streamId;
	}
	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}
	public String getStreamPostId() {
		return streamPostId;
	}
	public void setStreamPostId(String streamPostId) {
		this.streamPostId = streamPostId;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getUserSourceId() {
		return userSourceId;
	}
	public void setUserSourceId(String userSourceId) {
		this.userSourceId = userSourceId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Comments getComments() {
		return comments;
	}
	public void setComments(Comments comments) {
		this.comments = comments;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}
	public String getFormattedDate() {
		return formattedDate;
	}
	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}
	public String getHandPicked() {
		return handPicked;
	}
	public void setHandPicked(String handPicked) {
		this.handPicked = handPicked;
	}
	public String getUserSubmitted() {
		return userSubmitted;
	}
	public void setUserSubmitted(String userSubmitted) {
		this.userSubmitted = userSubmitted;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShowRetweet() {
		return showRetweet;
	}
	public void setShowRetweet(String showRetweet) {
		this.showRetweet = showRetweet;
	}
}
