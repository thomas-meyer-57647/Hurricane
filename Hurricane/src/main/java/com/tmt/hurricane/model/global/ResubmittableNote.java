package com.tmt.hurricane.model.global;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/
import java.time.LocalDateTime;
import java.util.Objects;

import com.tmt.hurricane.user.model.User;

/**
 * this is a resubmittable note
 * 
 * LocalDateTime	created_at;				the Date of the creation
 * User				editor;					the User who has edited this note
 * LocalDateTime  	resubmittable;			resubmittable time
 * String			description;			the description of the note
 */
public class ResubmittableNote {
	
	private LocalDateTime	created_at;				// the Date of the creation
	private User			editor;					// the User who has edited this note
	private LocalDateTime  	resubmittable;			// resubmittable time
	private String			description;			// the description of the note
	
	public ResubmittableNote(
			LocalDateTime created_at, 
			User editor, 
			LocalDateTime resubmittable, 
			String description) {
		super();
		
		this.created_at = created_at;
		this.editor = editor;
		this.resubmittable = resubmittable;
		this.description = description;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public User getEditor() {
		return editor;
	}

	public void setEditor(User editor) {
		this.editor = editor;
	}

	public LocalDateTime getResubmittable() {
		return resubmittable;
	}

	public void setResubmittable(LocalDateTime resubmittable) {
		this.resubmittable = resubmittable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(created_at, description, editor, resubmittable);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResubmittableNote other = (ResubmittableNote) obj;
		return Objects.equals(created_at, other.created_at) && Objects.equals(description, other.description)
				&& Objects.equals(editor, other.editor) && Objects.equals(resubmittable, other.resubmittable);
	}

	@Override
	public String toString() {
		return "ResubmittableNote [created_at=" + created_at + ", editor=" + editor + ", resubmittable=" + resubmittable
				+ ", description=" + description + "]";
	}
		
}
