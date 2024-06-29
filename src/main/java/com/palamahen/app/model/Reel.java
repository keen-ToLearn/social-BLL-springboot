package com.palamahen.app.model;

public class Reel {
	
	private String reelId;
	private String reelName;
	private String reelDescription;
	private String reelBy;
	private String reelTimestamp;
	
	public Reel() {
		
	}

	public Reel(String reelId, String reelName, String reelDescription, String reelBy, String reelTimestamp) {
		super();
		this.reelId = reelId;
		this.reelName = reelName;
		this.reelDescription = reelDescription;
		this.reelBy = reelBy;
		this.reelTimestamp = reelTimestamp;
	}

	public String getReelId() {
		return reelId;
	}

	public void setReelId(String reelId) {
		this.reelId = reelId;
	}

	public String getReelName() {
		return reelName;
	}

	public void setReelName(String reelName) {
		this.reelName = reelName;
	}

	public String getReelDescription() {
		return reelDescription;
	}

	public void setReelDescription(String reelDescription) {
		this.reelDescription = reelDescription;
	}

	public String getReelBy() {
		return reelBy;
	}

	public void setReelBy(String reelBy) {
		this.reelBy = reelBy;
	}

	public String getReelTimestamp() {
		return reelTimestamp;
	}

	public void setReelTimestamp(String reelTimestamp) {
		this.reelTimestamp = reelTimestamp;
	}
	
}
