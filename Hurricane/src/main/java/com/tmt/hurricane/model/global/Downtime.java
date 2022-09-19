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

import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * this is the downtime for a spezified User or for technical
 * 
 * Dervied from <code>TimeSpan</code>
 * 		LocalDateTime from,						start time 
 *		LocalDateTime to,						end time
 *
 * contained here
 *		Reason reason							reason description
 */
public class Downtime extends TimeSpan {

	@DBRef
	private Reason reason;

	public Downtime(
			LocalDateTime from, 
			LocalDateTime to, 
			Reason reason) {
		super(from, to);
		
		this.reason = reason;
	}

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(reason);
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
		Downtime other = (Downtime) obj;
		return Objects.equals(reason, other.reason);
	}

	@Override
	public String toString() {
		return "Downtime [reason=" + reason + "]";
	}

}
