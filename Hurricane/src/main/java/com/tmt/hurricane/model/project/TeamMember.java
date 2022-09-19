package com.tmt.hurricane.model.project;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.tmt.hurricane.model.jobs.Job;
import com.tmt.hurricane.user.model.User;

/**
 * this class describes the task of a person within the team
 * 
 * @DBRef
 * private User created_by;										the creator
 * private LocalDateTime created_at;							the created date
 *
 * @DBRef
 * private User user;											the member
 *   
 * private List<Job> job = new ArrayList<Job>();				the jobs within the team
 */
public class TeamMember {
	
    @DBRef
    private User created_by;									// the creator
    private LocalDateTime created_at;							// the created date

    @DBRef
	private User user;											// the member
    
    private List<Job> job = new ArrayList<Job>();				// the jobs within the team

	public TeamMember(
			User user, 
			List<Job> job) {
		super();
		
		this.user = user;
		this.job = job;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Job> getJob() {
		return job;
	}

	public void setJob(List<Job> job) {
		this.job = job;
	}

	@Override
	public int hashCode() {
		return Objects.hash(job, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeamMember other = (TeamMember) obj;
		return Objects.equals(job, other.job) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "TeamMember [created_by=" + created_by + ", created_at=" + created_at + ", user=" + user + ", job=" + job
				+ "]";
	}
}
