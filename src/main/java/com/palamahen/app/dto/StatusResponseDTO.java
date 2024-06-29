package com.palamahen.app.dto;

public class StatusResponseDTO {
	
	private Boolean status;
	private String message;
	
	public StatusResponseDTO() {
		
	}
	
	public StatusResponseDTO(Boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
