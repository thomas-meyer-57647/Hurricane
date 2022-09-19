package com.tmt.hurricane.model.global;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @package		usermanagement
 * @version		0.1.4
 --------------------------------------------------------------------------------*/

import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.Language;

/**
 * a language specefic string
 */
public class LanguageString {
	
    @Language
    private String languageCode;				// an ISO 639 2-letter language code

    private String text;						// the text content

	public LanguageString(String languageCode, String text) {
		super();
		
		this.languageCode = languageCode;
		this.text = text;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int hashCode() {
		return Objects.hash(languageCode, text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LanguageString other = (LanguageString) obj;
		return Objects.equals(languageCode, other.languageCode) && Objects.equals(text, other.text);
	}

	@Override
	public String toString() {
		return "LanguageString [languageCode=" + languageCode + ", text=" + text + "]";
	}
}
