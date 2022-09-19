package com.tmt.hurricane.currency.repository;
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

import com.tmt.hurricane.currency.model.Currency;
import com.tmt.hurricane.user.model.User;

/**
 * the repository for the currency
 */
@Repository
public interface CurrencyRepository extends MongoRepository<Currency, Long> {
	/**
	 * find currency by name
	 * 
	 * @param String							the name to find
	 * @reutrn Optional<Currency>				the found currency or null
	 */
	Optional<Currency> findByName(String name);
	
	/**
	 * find courrency by country code
	 * 
	 * @param long							t	the id of the country
	 * @reutrn Optional<Currency>				the found country or null
	 */
	Optional<Currency> findByCountry(long country_id);
}
