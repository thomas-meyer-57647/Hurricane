package com.tmt.hurricane.model.document;
import java.time.LocalDateTime;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2020 Thomas Meyer. License see license.txt
 * @version		0.0.1
 --------------------------------------------------------------------------------*/
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.tmt.hurricane.user.model.User;

/**
 * This class describes a file in which there can be several documents
 * 
 *  @DBRef
 *  User created_by;											the creator
 *  LocalDateTime created_at;									the created date
 *
 *  @DBRef
 *  User updated_by;											the creator
 *  LocalDateTime updated_at;									the update
 *
 *  @DBRef
 *  User deleted_by;											the user who has this deleted
 *  LocalDateTime deleted_at;									the date when the user has this deleted
 *
 * List<User> allowedViewers = new ArrayList<User>();			a list of allowed viewers
 * List<Document> documents = new ArrayList<Document>();		the list of documents
 * LocalDateTime released = null;								Document released?
 * LocalDateTime resubmission_date;								Resubmission date
 */
public class Documentary {
	
    @DBRef
    private User created_by;					// the creator
    private LocalDateTime created_at;			// the created date

    @DBRef
    private User updated_by;					// the creator
    private LocalDateTime updated_at;			// the update

    @DBRef
    private User deleted_by;					// the user who has this deleted
    private LocalDateTime deleted_at;			// the date when the user has this deleted

    private List<User> allowedViewers = new ArrayList<User>();			// a list of allowed viewers
    private List<Document> documents = new ArrayList<Document>();		// the list of documents
    private LocalDateTime released = null;								// Document released?
    private LocalDateTime resubmission_date;							// Resubmission date
    
	public Documentary(
			List<User> allowedViewers, 
			List<Document> documents, 
			LocalDateTime released,
			LocalDateTime resubmission_date) {
		super();
		this.allowedViewers = allowedViewers;
		this.documents = documents;
		this.released = released;
		this.resubmission_date = resubmission_date;
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

	public List<User> getAllowedViewers() {
		return allowedViewers;
	}

	public void setAllowedViewers(List<User> allowedViewers) {
		this.allowedViewers = allowedViewers;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public LocalDateTime getReleased() {
		return released;
	}

	public void setReleased(LocalDateTime released) {
		this.released = released;
	}

	public LocalDateTime getResubmission_date() {
		return resubmission_date;
	}

	public void setResubmission_date(LocalDateTime resubmission_date) {
		this.resubmission_date = resubmission_date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(allowedViewers, documents, released, resubmission_date);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documentary other = (Documentary) obj;
		return Objects.equals(allowedViewers, other.allowedViewers) && Objects.equals(documents, other.documents)
				&& Objects.equals(released, other.released)
				&& Objects.equals(resubmission_date, other.resubmission_date);
	}

	@Override
	public String toString() {
		return "Documentary [created_by=" + created_by + ", created_at=" + created_at + ", updated_by=" + updated_by
				+ ", updated_at=" + updated_at + ", deleted_by=" + deleted_by + ", deleted_at=" + deleted_at
				+ ", allowedViewers=" + allowedViewers + ", documents=" + documents + ", released=" + released
				+ ", resubmission_date=" + resubmission_date + "]";
	}
}
