package com.tmt.hurricane.model.fields;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * the value of a selfdefinied field for a specified record
 *
 *	@Transient
 *	static final String SEQUENCE_NAME = "field_value_sequence";
 *
 *	@Id
 * long id;											the id of the value
 *
 *	@Indexed	
 *	private long refID;								the reference id
 * 	
 *	@DBRef
 *	@Indexed
 *	private CustomField customField;				the associated custom field
 *	
 *	private String values;							the value(s) for this field
 */
@Document(collection="field_value")
public class FieldValue {

	@Transient
 	public static final String SEQUENCE_NAME = "field_value_sequence";

 	@Id
    private long id;								// the id of the value

	@Indexed	
 	private long refID;								// the reference id
 	
	@DBRef
	@Indexed
	private CustomField customField;				// the associated custom field
	
	private String values;							// the value(s) for this field

	public FieldValue(long refID, CustomField customField, String values) {
		super();
		
		this.refID = refID;
		this.customField = customField;
		this.values = values;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRefID() {
		return refID;
	}

	public void setRefID(long refID) {
		this.refID = refID;
	}

	public CustomField getCustomField() {
		return customField;
	}

	public void setCustomField(CustomField customField) {
		this.customField = customField;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customField, refID, values);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FieldValue other = (FieldValue) obj;
		return Objects.equals(customField, other.customField) && refID == other.refID
				&& Objects.equals(values, other.values);
	}

	@Override
	public String toString() {
		return "FieldValue [id=" + id + ", refID=" + refID + ", customField=" + customField + ", values=" + values
				+ "]";
	}
	
}
