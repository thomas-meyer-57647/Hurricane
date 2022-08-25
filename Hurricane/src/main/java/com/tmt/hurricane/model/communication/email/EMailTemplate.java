package com.tmt.hurricane.model.communication.email;
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
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Language;

import com.tmt.hurricane.model.user.User;


/**
 * This class describes a template for an EMail
 * 
 * @Id
 * long id;							the id of the email template
 *
 * @DBRef
 * User created_by;					the creator
 * LocalDateTime created_at;		the created date
 *
 * @DBRef
 * User updated_by;					the creator
 * LocalDateTime updated_at;		the update
 *
 * @DBRef
 * User deleted_by;					the user who has this deleted
 * LocalDateTime deleted_at;		the date when the user has this deleted
 *
 * @Language
 * String languageCode;				an ISO 639 2-letter language code
 *   
 * EEmailUsing using;				Specifies when this email template should be used 
 *
 * String template;					the email template
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

    @Language
    private String languageCode;				// an ISO 639 2-letter language code
    
    private EEmailUsing using;					// Specifies when this email template should be used 

    private String template;					// the email template

	public EMailTemplate(
			String languageCode, 
			EEmailUsing using, 
			String template) {
		super();
		this.languageCode = languageCode;
		this.using = using;
		this.template = template;
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

	public void setUpdated_at(LocalDateTime updated_at) {
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

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public EEmailUsing getUsing() {
		return using;
	}

	public void setUsing(EEmailUsing using) {
		this.using = using;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	@Override
	public int hashCode() {
		return Objects.hash(created_at, created_by, deleted_at, deleted_by, id, languageCode, template, updated_at,
				updated_by, using);
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
		return Objects.equals(created_at, other.created_at) && Objects.equals(created_by, other.created_by)
				&& Objects.equals(deleted_at, other.deleted_at) && Objects.equals(deleted_by, other.deleted_by)
				&& id == other.id && Objects.equals(languageCode, other.languageCode)
				&& Objects.equals(template, other.template) && Objects.equals(updated_at, other.updated_at)
				&& Objects.equals(updated_by, other.updated_by) && using == other.using;
	}

	@Override
	public String toString() {
		return "EMailTemplate [id=" + id + ", created_by=" + created_by + ", created_at=" + created_at + ", updated_by="
				+ updated_by + ", updated_at=" + updated_at + ", deleted_by=" + deleted_by + ", deleted_at="
				+ deleted_at + ", languageCode=" + languageCode + ", using=" + using + ", template=" + template + "]";
	}

    
}
