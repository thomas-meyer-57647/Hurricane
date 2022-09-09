package com.tmt.hurricane.model.global;
import java.time.LocalDateTime;
import java.util.Objects;

/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2020 Thomas Meyer. License see license.txt
 * @version		0.0.1
 --------------------------------------------------------------------------------*/
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tmt.hurricane.user.model.User;

/**
 * this is the collection for technical reason
 * 
 *  long id										the id of the reason
 *  
 *  @DBRef
 *  User created_by;							the creator
 *  LocalDateTime created_at;					the created date
 *
 *  @DBRef
 *  User updated_by;							the creator
 *  LocalDateTime updated_at;					the update
 *
 *  @DBRef
 *  User deleted_by;							the user who has this deleted
 *  LocalDateTime deleted_at;					the date when the user has this deleted
 *  
 *  String reason								the description of a technical reason
 */
@Document(collection="reasons")
public class Reason {

 	@Transient
 	public static final String SEQUENCE_NAME = "reason_sequence";
 	
 	@Id
    private long id;							// the id of the reason
 	
    @DBRef
    private User created_by;					// the creator
    private LocalDateTime created_at;			// the created date

    @DBRef
    private User updated_by;					// the creator
    private LocalDateTime updated_at;			// the update

    @DBRef
    private User deleted_by;					// the user who has this deleted
    private LocalDateTime deleted_at;			// the date when the user has this deleted
 	
 	private EReasonType type;					// the type of reason
	private String reason;						// the description of a technical reason

	public Reason(
			long id, 
			User created_by, 
			LocalDateTime created_at, 
			User updated_by, 
			LocalDateTime updated_at,
			User deleted_by,
			LocalDateTime deleted_at, 
			
			EReasonType type, String reason) {
		super();
		
		this.id = id;
		this.created_by = created_by;
		this.created_at = created_at;
		this.updated_by = updated_by;
		this.updated_at = updated_at;
		this.deleted_by = deleted_by;
		this.deleted_at = deleted_at;
		this.type = type;
		this.reason = reason;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getCreated_by() {
		return created_by;
	}

	public void setCreated_by(User created_by) {
		this.created_by = created_by;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public User getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(User updated_by) {
		this.updated_by = updated_by;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public User getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(User deleted_by) {
		this.deleted_by = deleted_by;
	}

	public LocalDateTime getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(LocalDateTime deleted_at) {
		this.deleted_at = deleted_at;
	}

	public EReasonType getType() {
		return type;
	}

	public void setType(EReasonType type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public int hashCode() {
		return Objects.hash(created_at, created_by, deleted_at, deleted_by, id, reason, type, updated_at, updated_by);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reason other = (Reason) obj;
		return Objects.equals(created_at, other.created_at) && Objects.equals(created_by, other.created_by)
				&& Objects.equals(deleted_at, other.deleted_at) && Objects.equals(deleted_by, other.deleted_by)
				&& id == other.id && Objects.equals(reason, other.reason) && type == other.type
				&& Objects.equals(updated_at, other.updated_at) && Objects.equals(updated_by, other.updated_by);
	}

	@Override
	public String toString() {
		return "Reason [id=" + id + ", created_by=" + created_by + ", created_at=" + created_at + ", updated_by="
				+ updated_by + ", updated_at=" + updated_at + ", deleted_by=" + deleted_by + ", deleted_at="
				+ deleted_at + ", type=" + type + ", reason=" + reason + "]";
	}
	
}
