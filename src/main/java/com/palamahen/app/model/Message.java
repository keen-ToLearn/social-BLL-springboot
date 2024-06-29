package com.palamahen.app.model;

public class Message {
	
	private String msgId;
	private String msgText;
	private String msgTimestamp;
	
	public Message() {
		
	}

	public Message(String msgId, String msgText, String msgTimestamp) {
		super();
		this.msgId = msgId;
		this.msgText = msgText;
		this.msgTimestamp = msgTimestamp;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgText() {
		return msgText;
	}

	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}

	public String getMsgTimestamp() {
		return msgTimestamp;
	}

	public void setMsgTimestamp(String msgTimestamp) {
		this.msgTimestamp = msgTimestamp;
	}
	
}
