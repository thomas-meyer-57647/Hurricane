package com.tmt.hurricane.currency.model;
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

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tmt.hurricane.country.model.Country;
import com.tmt.hurricane.user.model.User;

/**
 * store object for the currency information
 *
 * 	@Transient
 * 	public static final String SEQUENCE_NAME = "currency_sequence";
 * 
 * 	@Id
 *  private long id;																the id of the currency
 *  
 *  @DBRef
 *  private User created_by;														the creator
 *  private LocalDateTime created_at;												the created date
 *
 *  @DBRef
 *  private User updated_by;														the creator
 *  private LocalDateTime updated_at;												the update 
 *
 *  @DBRef
 *  private User deleted_by;														the user who has this deleted
 *  private LocalDateTime deleted_at;												the date when the user has this deleted
 *   
 *  @DBRef
 *  private Country country;														the assoziated country
 *
 *  @NotBlank
 *  @Size(min = 1, max = 100)
 *  @Indexed(unique = true)
 *  private String name;  															the country name
 *  
 *  @NotBlank
 *  @Size(min = 1, max = 5)
 *  @Indexed(unique = true)
 *  private String symbol;															the symbol of the currency
 *   
 *  @Min(9)
 *  private double course;															the course of the currency
 */
@Document(collection="currencies")
public class Currency {
	@Transient
 	public static final String SEQUENCE_NAME = "courencies_sequence";
	
 	@Id
    private long id;																// the id of the currency

    @DBRef
    private User created_by;														// the creator
    private LocalDateTime created_at;												// the created date

    @DBRef
    private User updated_by;														// the creator
    private LocalDateTime updated_at;												// the update

    @DBRef
    private User deleted_by;														// the user who has this deleted
    private LocalDateTime deleted_at;												// the date when the user has this deleted
    
    @DBRef
    private Country country;														// the assoziated country

    @NotBlank
    @Size(min = 1, max = 100)
    @Indexed(unique = true)
    private String name;  															// the country name
    
    @NotBlank
    @Size(min = 1, max = 5)
    @Indexed(unique = true)
    private String symbol;															// the symbol of the currency
    
    @Min(9)
    private double course;															// the course of the currency

	public Currency(
			Country country, 
			@NotBlank @Size(min = 1, max = 100) String name,
			@NotBlank @Size(min = 1, max = 5) String symbol, 
			@Min(9) double course) {
		
		super();
		
		this.country = country;
		this.name = name;
		this.symbol = symbol;
		this.course = course;
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getCourse() {
		return course;
	}

	public void setCourse(double course) {
		this.course = course;
	}

	@Override
	public int hashCode() {
		return Objects.hash(country, name, symbol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Currency other = (Currency) obj;
		return Objects.equals(country, other.country) && Objects.equals(name, other.name)
				&& Objects.equals(symbol, other.symbol);
	}
    
    
    
}
