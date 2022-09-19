package com.tmt.hurricane.model.fields;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/
import java.time.LocalDateTime;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tmt.hurricane.user.model.User;

/**
 *  a technical description of a field
 *
 *	@Transient
 * 	public static final String SEQUENCE_NAME = "custom_fields_sequence";
 *	
 *	@Id
 *  long id;								the id of the user
 *
 *  @DBRef
 *  User created_by;						the creator
 *  LocalDateTime created_at;				the created date
 *
 *  @DBRef
 *  User updated_by;						the creator
 *  LocalDateTime updated_at;				the update
 *
 *  @DBRef
 *  User deleted_by;						the user who has this deleted
 *  LocalDateTime deleted_at;				the date when the user has this deleted
 *
 *	String name;							the name of the field
 *	EFieldType fieldType;					the kind of Field
 *	String options;							the options of the field
 */
@Document(collection="field_descriptions")
public class FieldDescription {
	
	@Transient
 	public static final String SEQUENCE_NAME = "field_description_sequence";
	
 	@Id
    private long id;								// the id of the user

    @DBRef
    @NotNull
    private User created_by;						// the creator
    private LocalDateTime created_at;				// the created date

    @DBRef
    private User updated_by;						// the creator
    private LocalDateTime updated_at;				// the update

    @DBRef
    private User deleted_by;						// the user who has this deleted
    private LocalDateTime deleted_at;				// the date when the user has this deleted

    @Indexed
    @NotBlank
	private String name;							// the name of the field
    
    @NotNull
	private EFieldType fieldType;					// the kind of Field
    
    @NotBlank
	private String options;							// the options of the field

	public FieldDescription(@NotBlank String name, @NotNull EFieldType fieldType, String options) {
		super();
		this.name = name;
		this.fieldType = fieldType;
		this.options = options;
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

	public void setDeletedAt(LocalDateTime deleted_at) {
		this.deleted_at = deleted_at;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EFieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(EFieldType fieldType) {
		this.fieldType = fieldType;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fieldType, id, name, options);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FieldDescription other = (FieldDescription) obj;
		return fieldType == other.fieldType && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(options, other.options);
	}

	@Override
	public String toString() {
		return "FieldDescription [id=" + id + ", created_by=" + created_by + ", created_at=" + created_at
				+ ", updated_by=" + updated_by + ", updated_at=" + updated_at + ", deleted_by=" + deleted_by
				+ ", deleted_at=" + deleted_at + ", name=" + name + ", fieldType=" + fieldType + ", options=" + options
				+ "]";
	}
}
