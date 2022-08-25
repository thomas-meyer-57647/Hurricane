package com.tmt.hurricane.model.project;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2020 Thomas Meyer. License see license.txt
 * @version		0.0.1
 --------------------------------------------------------------------------------*/
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tmt.hurricane.model.company.Company;
import com.tmt.hurricane.model.document.Documentary;
import com.tmt.hurricane.model.global.TimeSpan;
import com.tmt.hurricane.model.user.User;

/**
 * this class describe a project
 *
 */
@Document(collection="projects")
public class Project {
	
	@Transient
 	public static final String SEQUENCE_NAME = "project_sequence";
	
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

    private String name;															// the name of the project
 
    @DBRef
    @NotNull
    private Company company;														// the company who make this project
    
    @NotNull
    private String filename;														// the path of this project for documents etc.
    private List<Documentary> documents = new ArrayList<Documentary>();				// the documents in the file for this project
    
    private double budget;															// the budget for this project
    private Currency currency;														// the currency for the budget
    
    private TimeSpan timeSpan;														// the timespan for this project
    private LocalDate overdrawableTo;												// if this project overdrawable by time
    
    private List<Team> teams = new ArrayList<Team>();								// the teams of the project

	public Project(
			String name, 
			@NotNull Company company, 
			@NotNull String filename,
			List<Documentary> documents, 
			double budget, Currency currency, 
			TimeSpan timeSpan, 
			LocalDate overdrawableTo,
			List<Team> teams) {
		super();
		
		this.name = name;
		this.company = company;
		this.filename = filename;
		this.documents = documents;
		this.budget = budget;
		this.currency = currency;
		this.timeSpan = timeSpan;
		this.overdrawableTo = overdrawableTo;
		this.teams = teams;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<Documentary> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Documentary> documents) {
		this.documents = documents;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public TimeSpan getTimeSpan() {
		return timeSpan;
	}

	public void setTimeSpan(TimeSpan timeSpan) {
		this.timeSpan = timeSpan;
	}

	public LocalDate getOverdrawableTo() {
		return overdrawableTo;
	}

	public void setOverdrawableTo(LocalDate overdrawableTo) {
		this.overdrawableTo = overdrawableTo;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	@Override
	public int hashCode() {
		return Objects.hash(budget, company, documents, filename, id, name, overdrawableTo, teams, timeSpan);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		return Double.doubleToLongBits(budget) == Double.doubleToLongBits(other.budget)
				&& Objects.equals(company, other.company) && Objects.equals(documents, other.documents)
				&& Objects.equals(filename, other.filename) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(overdrawableTo, other.overdrawableTo) && Objects.equals(teams, other.teams)
				&& Objects.equals(timeSpan, other.timeSpan);
	}

    
}
