package com.tmt.hurricane.country.repository;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @package     database
 * @version		0.1.4
 --------------------------------------------------------------------------------*/

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tmt.hurricane.country.model.Country;
import com.tmt.hurricane.user.model.User;

/**
 * the repository for the country
 */
@Repository
public interface CountryRepository extends MongoRepository<Country, Long> {
	/**
	 * find country by name
	 * 
	 * @param String							the name to find
	 * @reutrn Optional<Country>				the found country or null
	 */
	Optional<Country> findByName(String name);
	
	/**
	 * find country by country code
	 * 
	 * @param String							the code to find
	 * @reutrn Optional<Country>				the found country or null
	 */
	Optional<Country> findByCode(String code);
	
}

