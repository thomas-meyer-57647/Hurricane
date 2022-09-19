package com.tmt.hurricane.model.user;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.tmt.hurricane.model.communication.Communication;
import com.tmt.hurricane.model.global.Downtime;
import com.tmt.hurricane.model.global.Reason;
import com.tmt.hurricane.user.model.User;

/**
 * this is the downtime for a spezified User
 * 
 * @DBRef
 * User created_by;								the creator
 * LocalDateTime created_at;					the created date
 *
 * @DBRef
 * User updated_by;								the creator
 * LocalDateTime updated_at;					the update
 *
 * @DBRef
 * User deleted_by;								the user who has this deleted
 * LocalDateTime deleted_at;					the date when the user has this deleted
 *
 * Communication communication;					communication e.g. Hosptal
 * String note;									note
 * User substitution;							Substitution
 * boolean continuation_of_pay;					continuation of pay
 * 
 * Derived from <code>Downtime</code>
 * 
 *   Reason reason								reason description
 *
 *		Dervied from <code>TimeSpan</code>
 * 			LocalDateTime from,					start time 
 *			LocalDateTime to,					end time
 *
 */
public class UserDowntime extends Downtime {
	
	@DBRef
    private User created_by;														// the creator
    private LocalDateTime created_at;												// the created date

    @DBRef
    private User updated_by;														// the creator
    private LocalDateTime updated_at;												// the update

    @DBRef
    private User deleted_by;														// the user who has this deleted
    private LocalDateTime deleted_at;												// the date when the user has this deleted

	private Communication communication;		// communication
	private String note;						// note
	private User substitution;					// Substitution
	private boolean continuation_of_pay;		// continuation of pay
	
	public UserDowntime(
			LocalDateTime from, 
			LocalDateTime to, 
			Reason reason, 
			Communication communication, 
			String note,
			User substitution, 
			boolean continuation_of_pay) {
		super(from, to, reason);

		this.communication = communication;
		this.note = note;
		this.substitution = substitution;
		this.continuation_of_pay = continuation_of_pay;
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

	public Communication getCommunication() {
		return communication;
	}

	public void setCommunication(Communication communication) {
		this.communication = communication;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public User getSubstitution() {
		return substitution;
	}

	public void setSubstitution(User substitution) {
		this.substitution = substitution;
	}

	public boolean isContinuation_of_pay() {
		return continuation_of_pay;
	}

	public void setContinuation_of_pay(boolean continuation_of_pay) {
		this.continuation_of_pay = continuation_of_pay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(communication, continuation_of_pay, note, substitution);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDowntime other = (UserDowntime) obj;
		return Objects.equals(communication, other.communication) && continuation_of_pay == other.continuation_of_pay
				&& Objects.equals(note, other.note) && Objects.equals(substitution, other.substitution);
	}

	@Override
	public String toString() {
		return "UserDowntime [created_by=" + created_by + ", created_at=" + created_at + ", updated_by=" + updated_by
				+ ", updated_at=" + updated_at + ", deleted_by=" + deleted_by + ", deleted_at=" + deleted_at
				+ ", communication=" + communication + ", note=" + note + ", substitution=" + substitution
				+ ", continuation_of_pay=" + continuation_of_pay + "]";
	}
}
