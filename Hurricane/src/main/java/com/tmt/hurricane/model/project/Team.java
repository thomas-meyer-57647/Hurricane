package com.tmt.hurricane.model.project;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2020 Thomas Meyer. License see license.txt
 * @version		0.0.1
 --------------------------------------------------------------------------------*/

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.tmt.hurricane.model.user.User;

/**
 * This class describes a team of a project
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
 * String name;															the team name
 * List<TeamMember> teamMembers = new ArrayList<TeamMember>();			the team members of the team
 *
 */
public class Team {
	
    @DBRef
    private User created_by;														// the creator
    private LocalDateTime created_at;												// the created date

    @DBRef
    private User updated_by;														// the creator
    private LocalDateTime updated_at;												// the update

    @DBRef
    private User deleted_by;														// the user who has this deleted
    private LocalDateTime deleted_at;												// the date when the user has this deleted

	private String name;															// the team name
	private List<TeamMember> teamMembers = new ArrayList<TeamMember>();				// the team members of the team

	public Team(
			String name, 
			List<TeamMember> teamMembers) {
		super();
		
		this.name = name;
		this.teamMembers = teamMembers;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TeamMember> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<TeamMember> teamMembers) {
		this.teamMembers = teamMembers;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, teamMembers);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		return Objects.equals(name, other.name) && Objects.equals(teamMembers, other.teamMembers);
	}

	@Override
	public String toString() {
		return "Team [created_by=" + created_by + ", created_at=" + created_at + ", updated_by=" + updated_by
				+ ", updated_at=" + updated_at + ", deleted_by=" + deleted_by + ", deleted_at=" + deleted_at + ", name="
				+ name + ", teamMembers=" + teamMembers + "]";
	}
}
