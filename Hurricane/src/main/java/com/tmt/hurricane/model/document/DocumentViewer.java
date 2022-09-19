package com.tmt.hurricane.model.document;
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

import com.tmt.hurricane.user.model.User;

/**
 * This class describes the people who have already seen this document and when
 * 
 * 	LocalDateTime	view_date;			the date of the view
 *	
 *	@DBRef
 *	User			user;				the person who watched it
 */
public class DocumentViewer {
	
	private LocalDateTime	view_date;			// the date of the view
	
	@DBRef
	private User			user;				// the person who watched it

	public DocumentViewer(
			LocalDateTime view_date, 
			User user) {
		super();
		
		this.view_date = view_date;
		this.user = user;
	}

	public LocalDateTime getViewDate() {
		return view_date;
	}

	public void setViewDate(LocalDateTime view_date) {
		this.view_date = view_date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, view_date);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentViewer other = (DocumentViewer) obj;
		return Objects.equals(user, other.user) && Objects.equals(view_date, other.view_date);
	}

	@Override
	public String toString() {
		return "DocumentViewer [view_date=" + view_date + ", user=" + user + "]";
	}
}
