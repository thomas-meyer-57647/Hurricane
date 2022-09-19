package com.tmt.hurricane.model.company;
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

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tmt.hurricane.model.communication.Communication;
import com.tmt.hurricane.model.global.ResubmittableNote;
import com.tmt.hurricane.user.model.User;

/**
 * a company description
 *
 *	@Transient
 * 	public static final String SEQUENCE_NAME = "company_sequence";
 *	
 *	@Id
 *  long id;																			the id of the user
 *
 *  @DBRef
 *  User created_by;																	the creator
 *  LocalDateTime created_at;															the created date
 *
 *  @DBRef
 *  User updated_by;																	the creator
 *  LocalDateTime updated_at;															the update
 *
 *  @DBRef
 *  User deleted_by;																	the user who has this deleted
 *  LocalDateTime deleted_at;															the date when the user has this deleted
 *
 *  String name;																		name of the company
 *  List<Communication>	communications = new ArrayList<Communication>();				list of communications
 *
 *  List<ResubmittableNote> resubmittableNote = new ArrayList<ResubmittableNote>();		(resubmittable) Note
 *
 */
@Document(collection="companies")
public class Company {
	
	@Transient
 	public static final String SEQUENCE_NAME = "company_sequence";
	
 	@Id
    private long id;																		// the id of the user

    @DBRef
    private User created_by;																// the creator
    private LocalDateTime created_at;														// the created date

    @DBRef
    private User updated_by;																// the creator
    private LocalDateTime updated_at;														// the update

    @DBRef
    private User deleted_by;																// the user who has this deleted
    private LocalDateTime deleted_at;														// the date when the user has this deleted

    @Indexed(unique = true)
    private String name;																	// name of the company
    private List<Communication>	communications = new ArrayList<Communication>();			// list of communications
 
    private List<ResubmittableNote> resubmittableNote = new ArrayList<ResubmittableNote>();	// (resubmittable) Note

	public Company(
			String name, 
			List<Communication> communications,
			List<ResubmittableNote> resubmittableNote) {
		super();

		this.name = name;
		this.communications = communications;
		this.resubmittableNote = resubmittableNote;
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

	public List<Communication> getCommunications() {
		return communications;
	}

	public void setCommunications(List<Communication> communications) {
		this.communications = communications;
	}

	public List<ResubmittableNote> getResubmittableNote() {
		return resubmittableNote;
	}

	public void setResubmittableNote(List<ResubmittableNote> resubmittableNote) {
		this.resubmittableNote = resubmittableNote;
	}

	@Override
	public int hashCode() {
		return Objects.hash(communications, id, name, resubmittableNote, updated_at, updated_by);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return Objects.equals(communications, other.communications) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(resubmittableNote, other.resubmittableNote)
				&& Objects.equals(updated_at, other.updated_at) && Objects.equals(updated_by, other.updated_by);
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", created_by=" + created_by + ", created_at=" + created_at + ", updated_by="
				+ updated_by + ", updated_at=" + updated_at + ", deleted_by=" + deleted_by + ", deleted_at="
				+ deleted_at + ", name=" + name + ", communications=" + communications + ", resubmittableNote="
				+ resubmittableNote + "]";
	}
    
}
