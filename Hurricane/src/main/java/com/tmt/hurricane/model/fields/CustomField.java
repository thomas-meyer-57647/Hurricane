package com.tmt.hurricane.model.fields;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2020 Thomas Meyer. License see license.txt
 * @version		0.0.1
 --------------------------------------------------------------------------------*/

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tmt.hurricane.model.user.User;

/**
 * a selfdefinied field
 *
 * 	TEXT,					Text
 *	NUMBER,					an Integer or Float Number
 *	BOOLEAN,				a boolean
 *	RADIOBUTTONS,			a list of Radiobuttons (Multiple)
 *	COMBOBOX,				a combox - single value
 *	LIST					a list with multiple values
 */
@Document(collection="custom_fields")
public class CustomField {
	
	@Transient
 	public static final String SEQUENCE_NAME = "custom_field_sequence";
	
 	@Id
    private long id;								// the id of the user
	
    @DBRef
    private User created_by;						// the creator
    private LocalDateTime created_at;				// the created date

    @DBRef
    private User updated_by;						// the creator
    private LocalDateTime updated_at;				// the update

    @DBRef
    private User deleted_by;						// the user who has this deleted
    private LocalDateTime deleted_at;				// the date when the user has this deleted

    @Indexed(unique = true)
    private String name;							// the name of the field
    
    @Indexed
    private String classname;						// the class name to which this self-defined field belongs

    @DBRef
    private FieldDescription fieldDescription;		// the description of the field

	public CustomField(String name, String classname, FieldDescription fieldDescription) {
		super();
		
		this.name = name;
		this.classname = classname;
		this.fieldDescription = fieldDescription;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getCreatedBy() {
		return created_by;
	}

	public void setCreatedBy(User created_by) {
		this.created_by = created_by;
	}

	public LocalDateTime getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public User getUpdatedBy() {
		return updated_by;
	}

	public void setUpdatedBy(User updated_by) {
		this.updated_by = updated_by;
	}

	public LocalDateTime getUpdatedAt() {
		return updated_at;
	}

	public void setUpdatedAt(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public User getDeletedBy() {
		return deleted_by;
	}

	public void setDeletedBy(User deleted_by) {
		this.deleted_by = deleted_by;
	}

	public LocalDateTime getDeletedAt() {
		return deleted_at;
	}

	public void setDeleted_at(LocalDateTime deleted_at) {
		this.deleted_at = deleted_at;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public FieldDescription getFieldDescription() {
		return fieldDescription;
	}

	public void setFieldDescription(FieldDescription fieldDescription) {
		this.fieldDescription = fieldDescription;
	}

	@Override
	public int hashCode() {
		return Objects.hash(classname, fieldDescription, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomField other = (CustomField) obj;
		return Objects.equals(classname, other.classname) && Objects.equals(fieldDescription, other.fieldDescription)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "CustomField [id=" + id + ", created_by=" + created_by + ", created_at=" + created_at + ", updated_by="
				+ updated_by + ", updated_at=" + updated_at + ", deleted_by=" + deleted_by + ", deleted_at="
				+ deleted_at + ", name=" + name + ", classname=" + classname + ", fieldDescription=" + fieldDescription
				+ "]";
	}    
}
