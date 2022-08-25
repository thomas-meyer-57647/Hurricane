package com.tmt.hurricane.model.global;
import java.time.LocalDateTime;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2020 Thomas Meyer. License see license.txt
 * @package		usermanagement
 * @version		0.0.1
 --------------------------------------------------------------------------------*/
import java.time.LocalTime;
import java.util.Objects;

/**
 * the location for a comminication
 *
 *	LocalDateTime	from
 *	LocalDateTime	to
 *	Set<WeekDay>	days
 */
public class AvailableTime extends TimeSpan {
	
	private byte		days;

	public AvailableTime(
			LocalDateTime from, 
			LocalDateTime to, 
			byte days) {
		super(from, to);
		
		this.days = days;
	}

	public byte getDays() {
		return days;
	}

	public void setDays(byte days) {
		this.days = days;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(days);
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
		AvailableTime other = (AvailableTime) obj;
		return days == other.days;
	}

	@Override
	public String toString() {
		return "AvailableTime [days=" + days + "]";
	}
	
}
