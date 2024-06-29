package com.palamahen.app.utility;

import java.beans.PropertyEditorSupport;

//property editor utility for "firstName" when it comes in request
public class FirstNamePropertyEditor extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(text.trim().toLowerCase());
	}

}
