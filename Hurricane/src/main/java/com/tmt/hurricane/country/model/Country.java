package com.tmt.hurricane.country.model;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @package     database
 * @version		0.1.4
 --------------------------------------------------------------------------------*/

import java.time.LocalDateTime;
import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tmt.hurricane.user.model.User;

/**
 * store object for the contry information
 *
 *	@Transient
 *	public static final String SEQUENCE_NAME = "country_sequence";
 *	
 *	@Id
 *  long id;																the id of the user
 *
 *  @DBRef
 *  User created_by;														the creator
 *  LocalDateTime created_at;												the created date
 *
 *  @DBRef
 *  User updated_by;														the creator
 *  LocalDateTime updated_at;												the update
 *
 *  @DBRef
 *  User deleted_by;														the user who has this deleted
 *  LocalDateTime deleted_at;												the date when the user has this deleted
 *
 *  @Min(0) 
 *  long int ordering;														the ordering
 *  
 *  @NotBlank
 *  @Size(min = 1, max = 100)
 *  @Index(unique = true)
 *  String name;  															last name
 *
 *  @NotBlank
 *  @Size(min = 1, max = 2)
 *  String code;															the country code (iso 3166-alpha2)
 *
 *	string Flag;   
 *
 */
@Document(collection="countries")
public class Country {
	@Transient
 	public static final String SEQUENCE_NAME = "countries_sequence";
	
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
    @Indexed(unique = true)
    private String name;  															// the country name

    @NotBlank
    @Size(min = 1, max = 2)
    @Indexed(unique = true)   
    private String code;															// the country code (ISO Code 3166-Alpha2

    @Min(0)
    private int ordering = 0;														// ordering
    
    private Binary flag;															// the flag of the country

	public Country(
			@NotBlank @Size(min = 1, max = 100) String name,
			@NotBlank @Size(min = 1, max = 2) String code, 
			Binary flag) {

		this.name = name;
		this.code = code;
		this.flag = flag;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getOrdering() {
		return ordering;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}

	public Binary getFlag() {
		return flag;
	}

	public void setFlag(Binary flag) {
		this.flag = flag;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return Objects.equals(code, other.code) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", created_by=" + created_by + ", created_at=" + created_at + ", updated_by="
				+ updated_by + ", updated_at=" + updated_at + ", deleted_by=" + deleted_by + ", deleted_at="
				+ deleted_at + ", name=" + name + ", code=" + code + ", ordering=" + ordering + ", flag=" + flag
				+ ", getId()=" + getId() + ", getCreatedBy()=" + getCreatedBy() + ", getCreatedAt()=" + getCreatedAt()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedAt()=" + getUpdatedAt() + ", getDeletedBy()="
				+ getDeletedBy() + ", getDeletedAt()=" + getDeletedAt() + ", getName()=" + getName() + ", getCode()="
				+ getCode() + ", getOrdering()=" + getOrdering() + ", getFlag()=" + getFlag() + ", hashCode()="
				+ hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}
	
    
}
