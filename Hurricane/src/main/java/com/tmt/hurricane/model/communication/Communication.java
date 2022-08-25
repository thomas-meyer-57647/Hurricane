package com.tmt.hurricane.model.communication;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.tmt.hurricane.model.global.AvailableTime;
import com.tmt.hurricane.model.user.User;

/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2020 Thomas Meyer. License see license.txt
 * @package		usermanagement
 * @version		0.0.1
 --------------------------------------------------------------------------------*/


/**
 * the communication for a person or a company
 *
 * @DBRef
 * User created_by;									the creator
 * LocalDateTime created_at;						the created date
 *
 * @DBRef
 * User updated_by;									the creator
 * LocalDateTime updated_at;						the update
 *
 * @DBRef
 * User deleted_by;									the user who has this deleted
 * LocalDateTime deleted_at;						the date when the user has this deleted

 * ECommunicationTyp	type						the communication typ: EMAIL, TELEPHON, ADDRESS, etc.
 * ECommunicationLocation location					the location of the EMail or Telephon: BUSINESS, PRIVAT
 * AvailableTime fromTime							the communication time (from)
 * LocalDateTime toTime								the communication time (to)
 * String communication								the address or number for the type
 * Address adress									rhe adress
 * String addionalNote								a additionl note  
 */
public class Communication {
	
    @DBRef
    private User created_by;					// the creator
    private LocalDateTime created_at;			// the created date

    @DBRef
    private User updated_by;					// the creator
    private LocalDateTime updated_at;			// the update

    @DBRef
    private User deleted_by;					// the user who has this deleted
    private LocalDateTime deleted_at;			// the date when the user has this deleted

	private ECommunicationType		tpe;
	private ECommunicationLocation	location;
	private AvailableTime			time;
	private String					commication;
	private	Address					address;
	private String					additonalNote;
	
	public Communication(
			ECommunicationType tpe, 
			ECommunicationLocation location, 
			AvailableTime time,
			String commication, 
			Address address, 
			String additonalNote) {
		super();
		
		this.tpe = tpe;
		this.location = location;
		this.time = time;
		this.commication = commication;
		this.address = address;
		this.additonalNote = additonalNote;
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

	public ECommunicationType getTpe() {
		return tpe;
	}

	public void setTpe(ECommunicationType tpe) {
		this.tpe = tpe;
	}

	public ECommunicationLocation getLocation() {
		return location;
	}

	public void setLocation(ECommunicationLocation location) {
		this.location = location;
	}

	public AvailableTime getTime() {
		return time;
	}

	public void setTime(AvailableTime time) {
		this.time = time;
	}

	public String getCommication() {
		return commication;
	}

	public void setCommication(String commication) {
		this.commication = commication;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getAdditonalNote() {
		return additonalNote;
	}

	public void setAdditonalNote(String additonalNote) {
		this.additonalNote = additonalNote;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, commication, location, time, tpe);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Communication other = (Communication) obj;
		return Objects.equals(address, other.address) && Objects.equals(commication, other.commication)
				&& location == other.location && Objects.equals(time, other.time) && tpe == other.tpe;
	}

	@Override
	public String toString() {
		return "Communication [created_by=" + created_by + ", created_at=" + created_at + ", updated_by=" + updated_by
				+ ", updated_at=" + updated_at + ", deleted_by=" + deleted_by + ", deleted_at=" + deleted_at + ", tpe="
				+ tpe + ", location=" + location + ", time=" + time + ", commication=" + commication + ", address="
				+ address + ", additonalNote=" + additonalNote + "]";
	}
	
	
}
