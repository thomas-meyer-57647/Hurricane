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

/**
 * time span for a user or technical
 * 
 * LocalDateTime	from;					start time
 * LocalDateTime	to;						end time
 */
public class TimeSpan {
	
	private LocalDateTime	from;					// from
	private LocalDateTime	to;						// to
		
	public TimeSpan(
			LocalDateTime from, 
			LocalDateTime to) {
		super();
		
		this.from = from;
		this.to = to;
	}

	public LocalDateTime getFrom() {
		return from;
	}

	public void setFrom(LocalDateTime from) {
		this.from = from;
	}

	public LocalDateTime getTo() {
		return to;
	}

	public void setTo(LocalDateTime to) {
		this.to = to;
	}

	@Override
	public int hashCode() {
		return Objects.hash(from, to);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeSpan other = (TimeSpan) obj;
		return Objects.equals(from, other.from) && Objects.equals(to, other.to);
	}

	@Override
	public String toString() {
		return "TimeSpan [from=" + from + ", to=" + to + "]";
	}	
}
