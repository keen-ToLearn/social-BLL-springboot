package com.palamahen.app.model;

public class Story {
	
	private String storyId;
	private String storyTitle;
	private String storyMediaURL;
	private String storyBy;
	private String storyTimestamp;
	
	public Story() {
		
	}

	public Story(String storyId, String storyTitle, String storyMediaURL, String storyBy, String storyTimestamp) {
		super();
		this.storyId = storyId;
		this.storyTitle = storyTitle;
		this.storyMediaURL = storyMediaURL;
		this.storyBy = storyBy;
		this.storyTimestamp = storyTimestamp;
	}

	public String getStoryId() {
		return storyId;
	}

	public void setStoryId(String storyId) {
		this.storyId = storyId;
	}

	public String getStoryTitle() {
		return storyTitle;
	}

	public void setStoryTitle(String storyTitle) {
		this.storyTitle = storyTitle;
	}

	public String getStoryMediaURL() {
		return storyMediaURL;
	}

	public void setStoryMediaURL(String storyMediaURL) {
		this.storyMediaURL = storyMediaURL;
	}

	public String getStoryBy() {
		return storyBy;
	}

	public void setStoryBy(String storyBy) {
		this.storyBy = storyBy;
	}

	public String getStoryTimestamp() {
		return storyTimestamp;
	}

	public void setStoryTimestamp(String storyTimestamp) {
		this.storyTimestamp = storyTimestamp;
	}
	
}
