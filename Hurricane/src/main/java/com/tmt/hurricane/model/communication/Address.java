package com.tmt.hurricane.model.communication;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.tmt.hurricane.user.model.User;

/**
 * the address for a person or company
 * 
 * @DBRef
 * User created_by;						the creator
 * LocalDateTime created_at;			the created date
 *
 * @DBRef
 * User updated_by;						the creator
 * LocalDateTime updated_at;			the update
 *
 * @DBRef
 * User deleted_by;						the user who has this deleted
 * LocalDateTime deleted_at;			the date when the user has this deleted
 * 
 * String street						the street
 * String postCode						the post code
 * String city							the city
 * String country						the country
 *
 */
public class Address {
	
    @DBRef
    private User created_by;					// the creator
    private LocalDateTime created_at;			// the created date

    @DBRef
    private User updated_by;					// the creator
    private LocalDateTime updated_at;			// the update

    @DBRef
    private User deleted_by;					// the user who has this deleted
    private LocalDateTime deleted_at;			// the date when the user has this deleted

	private String street;
	private String postCode;
	private String city;
	private String country;
	
	public Address(
			String street, 
			String postCode, 
			String city, 
			String country) {
		super();
		
		this.street = street;
		this.postCode = postCode;
		this.city = city;
		this.country = country;
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

	public void setCreated_at(LocalDateTime created_at) {
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, country, postCode, street);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(postCode, other.postCode) && Objects.equals(street, other.street);
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", postCode=" + postCode + ", city=" + city + ", country=" + country + "]";
	}
	
	
}
