package com.tmt.hurricane.model.communication.email;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tmt.hurricane.model.global.LanguageString;
import com.tmt.hurricane.user.model.User;


/**
 * This class describes a template for an EMail
 * 
 * @Id
 * long id;																the id of the email template
 *
 * @DBRef
 * User created_by;														the creator
 * LocalDateTime created_at;											the created date
 *
 * @DBRef
 * User updated_by;														the creator
 * LocalDateTime updated_at;											the update
 *
 * @DBRef
 * User deleted_by;														the user who has this deleted
 * LocalDateTime deleted_at;											the date when the user has this deleted
 *
 * @Indexed
 * String name;															the name of the templace	
 * 
 * List<LanguageString> template = new ArrayList<LanguageString>();		the list of email template
 *   
 * EEmailUsing using;													Specifies when this email template should be used 
 */
@Document(collection="email_template")
public class EMailTemplate {

	@Transient
 	public static final String SEQUENCE_NAME = "emailtemplate_sequence";
	
 	@Id
    private long id;							// the id of the email template

    @DBRef
    private User created_by;					// the creator
    private LocalDateTime created_at;			// the created date

    @DBRef
    private User updated_by;					// the creator
    private LocalDateTime updated_at;			// the update

    @DBRef
    private User deleted_by;					// the user who has this deleted
    private LocalDateTime deleted_at;			// the date when the user has this deleted

    @Indexed(unique = true)
    @NotBlank
    private String name;						// the name of the templace																// name of the company
    
    List<LanguageString> template = new ArrayList<LanguageString>();	// the list of email template
    
    private EEmailUsing using;					// Specifies when this email template should be used 

	public EMailTemplate(
			@NotBlank String name, 
			List<LanguageString> template, 
			EEmailUsing using) {
		super();
		
		this.name = name;
		this.template = template;
		this.using = using;
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

	public void setName(@NotBlank String name) {
		this.name = name;
	}
	
	public List<LanguageString> getTemplate() {
		return template;
	}

	public void setTemplate(List<LanguageString> template) {
		this.template = template;
	}

	public EEmailUsing getUsing() {
		return using;
	}

	public void setUsing(EEmailUsing using) {
		this.using = using;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, template, using);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EMailTemplate other = (EMailTemplate) obj;
		return Objects.equals(name, other.name) && Objects.equals(template, other.template) && using == other.using;
	}

	@Override
	public String toString() {
		return "EMailTemplate [id=" + id + ", created_by=" + created_by + ", created_at=" + created_at + ", updated_by="
				+ updated_by + ", updated_at=" + updated_at + ", deleted_by=" + deleted_by + ", deleted_at="
				+ deleted_at + ", name=" + name + ", template=" + template + ", using=" + using + "]";
	}
}
