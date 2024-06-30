package com.palamahen.app.config;

public enum JwtConstant {
	
	AUTHORIZATION("Authorization"),
	SECRET_KEY("Alpha1Beta2Charlie3Delta4");

	public final String label;
	
	private JwtConstant(String label) {
		this.label = label;
	}
}
