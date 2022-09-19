package com.tmt.hurricane.user.model;
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

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tmt.hurricane.model.communication.Communication;
import com.tmt.hurricane.model.company.Company;
import com.tmt.hurricane.model.jobs.Job;
import com.tmt.hurricane.model.user.UserDowntime;
import com.tmt.hurricane.model.user.WorkTime;

/**
 * store object for the user information
 *
 *	@Transient
 *	public static final String SEQUENCE_NAME = "user_sequence";
 *	
 *	@Id
 *  long id;																the id of the user
 *
 *  @DBRef
 *  @Indexed
 *  User created_by;														the creator
 *  LocalDateTime created_at;												the created date
 *
 *  @DBRef
 *  @Indexed
 *  User updated_by;														the creator
 *  LocalDateTime updated_at;												the update
 *
 *  @DBRef
 *  @Indexed
 *  User deleted_by;														the user who has this deleted
 *  LocalDateTime deleted_at;												the date when the user has this deleted
 *
 *  @NotBlank
 *  @Size(min = 1, max = 100)
 *  String firstname;														first name
 *
 *  @NotBlank
 *  @Size(min = 1, max = 100)
 *  String lastname;														last name
 *   
 *  @NotBlank
 *  @Email
 *  @Indexed(unique=true)
 *  String email;															the email of the user
 *
 *  @NotBlank
 *  @Size(min = 6, max = 15)
 *  String password;														the password
 *   
 *  String pictogram;														the pictogram of the user
 *
 *  List<Communication> communications = new ArrayList<Communication>();	the communications
 *
 *  List<WorkTime> workTimes = new ArrayList<WorkTime>();					list of worktime
 *   
 *  List<UserDowntime> downtimes = new ArrayList<UserDowntime>();			list of downtimes of a specified user
 *   
 *  List<Job> jobs = new ArrayList<Job>();									list of jobs
 * 
 *  @DBRef
 *  Company company; 														the name of company
 *
 */
@Document(collection="users")
public class User {
	
	@Transient
 	public static final String SEQUENCE_NAME = "user_sequence";
	
 	@Id
    private long id;																// the id of the user

    @DBRef
    private User created_by;														// the creator
    private LocalDateTime created_at;												// the created date

    @DBRef
    private User updated_by;														// the creator
    private LocalDateTime updated_at;												// the update

    @DBRef
    private User deleted_by;														// the user who has this deleted
    private LocalDateTime deleted_at;												// the date when the user has this deleted

    @NotBlank
    @Size(min = 1, max = 100)
    // @Size(max = 200, message = "{validation.name.size.too_long}")
    private String firstname;														// first name

    @NotBlank
    @Size(min = 1, max = 100)
    private String lastname;														// last name
    
    @NotBlank
    @Email
    @Indexed(unique=true)
    private String email;															// the email of the user

    @NotBlank
    @Size(min = 6, max = 15)
    private String password;														// the password
    
    private boolean blocked;														// if the user blocked?
    private boolean enabled;														// if the user registred
    
    private Binary pictogram;														// the pictogram of the user

    private List<Communication> communications = new ArrayList<Communication>();	// the communications

    private List<WorkTime> workTimes = new ArrayList<WorkTime>();					// list of worktime
    
    private List<UserDowntime> downtimes = new ArrayList<UserDowntime>();			// list of downtimes of a specified user
    
    private List<Job> jobs = new ArrayList<Job>();									// list of jobs

    @DBRef
    private Company company; 														// the name of company

	public User(
			@NotBlank @Size(min = 1, max = 100) String firstname,
			@NotBlank @Size(min = 1, max = 100) String lastname,
			@NotBlank @Email String email,
			@NotBlank @Size(min = 6, max = 15) String password) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Binary getPictogram() {
		return pictogram;
	}

	public void setPictogram(Binary pictogram) {
		this.pictogram = pictogram;
	}

	public List<Communication> getCommunications() {
		return communications;
	}

	public void setCommunications(List<Communication> communications) {
		this.communications = communications;
	}

	public List<WorkTime> getWorkTimes() {
		return workTimes;
	}

	public void setWorkTimes(List<WorkTime> workTimes) {
		this.workTimes = workTimes;
	}

	public List<UserDowntime> getDowntimes() {
		return downtimes;
	}

	public void setDowntimes(List<UserDowntime> downtimes) {
		this.downtimes = downtimes;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, enabled, firstname, lastname, workTimes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && enabled == other.enabled
				&& Objects.equals(firstname, other.firstname) && Objects.equals(lastname, other.lastname)
				&& Objects.equals(workTimes, other.workTimes);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", created_by=" + created_by + ", created_at=" + created_at + ", updated_by="
				+ updated_by + ", updated_at=" + updated_at + ", deleted_by=" + deleted_by + ", deleted_at="
				+ deleted_at + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + ", blocked=" + blocked + ", enabled=" + enabled + ", pictogram="
				+ pictogram + ", communications=" + communications + ", workTimes=" + workTimes + ", downtimes="
				+ downtimes + ", jobs=" + jobs + ", company=" + company + "]";
	}

    
	
}
