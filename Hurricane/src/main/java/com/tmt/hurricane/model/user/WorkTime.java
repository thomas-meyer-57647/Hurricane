package com.tmt.hurricane.model.user;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2020 Thomas Meyer. License see license.txt
 * @version		0.0.1
 --------------------------------------------------------------------------------*/
import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.tmt.hurricane.model.global.AvailableTime;

/**
 * work time for a person according to employment contract
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
 * Derived from <code>AvailableTime</code>
 * LocalDate	from;					
 * LocalDate	to;						
 * byte			days;					
 */
public class WorkTime extends AvailableTime {

	@DBRef
    private User created_by;														// the creator
    private LocalDateTime created_at;												// the created date

    @DBRef
    private User updated_by;														// the creator
    private LocalDateTime updated_at;												// the update

    @DBRef
    private User deleted_by;														// the user who has this deleted
    private LocalDateTime deleted_at;												// the date when the user has this deleted

	public WorkTime(
			LocalDateTime from, 
			LocalDateTime to, 
			byte days) {
		super(from, to, days);
	}

	public User getCreatedBy() {
		return created_by;
	}

	public void setCreatedBy(User created_by) {
		this.created_by = created_by;
	}

	public LocalDateTime getCreated_at() {
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

	@Override
	public String toString() {
		return "WorkTime [created_by=" + created_by + ", created_at=" + created_at + ", updated_by=" + updated_by
				+ ", updated_at=" + updated_at + ", deleted_by=" + deleted_by + ", deleted_at=" + deleted_at + "]";
	}

}
