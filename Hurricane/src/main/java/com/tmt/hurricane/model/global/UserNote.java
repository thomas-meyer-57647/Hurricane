package com.tmt.hurricane.model.global;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2020 Thomas Meyer. License see license.txt
 * @version		0.0.1
 --------------------------------------------------------------------------------*/

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.tmt.hurricane.user.model.User;

/**
 * this class describes a note of a user for an operation
 *
 * LocalDateTime created_at				the date when this note was created
 *	
 *	@DBRef
 *	User user							the user who wrote this note
 *	
 *	String note;						the content of the note
 */
public class UserNote {
	
	private LocalDateTime created_at;			// the date when this note was created
	
	@DBRef
	private User user;							// the user who wrote this note
	
	private String note;						// the content of the note

	public UserNote(
			LocalDateTime created_at, 
			User user, 
			String note) {
		super();
		
		this.created_at = created_at;
		this.user = user;
		this.note = note;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public int hashCode() {
		return Objects.hash(created_at, note, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserNote other = (UserNote) obj;
		return Objects.equals(created_at, other.created_at) && Objects.equals(note, other.note)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "UserNote [created_at=" + created_at + ", user=" + user + ", note=" + note + "]";
	}
	
}
