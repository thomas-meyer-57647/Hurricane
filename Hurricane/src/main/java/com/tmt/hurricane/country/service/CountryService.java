package com.tmt.hurricane.country.service;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @package     database
 * @version		0.1.4
 --------------------------------------------------------------------------------*/
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.tmt.hurricane.country.model.Country;
import com.tmt.hurricane.country.repository.CountryRepository;
import com.tmt.hurricane.exceptions.DuplicateException;
import com.tmt.hurricane.exceptions.NotDefinedException;
import com.tmt.hurricane.exceptions.ResourceNotFoundException;
import com.tmt.hurricane.helper.database.service.SequenceGeneratorService;
import com.tmt.hurricane.services.field.FieldService;
import com.tmt.hurricane.user.model.User;
import com.tmt.hurricane.user.repository.UserRepository;

/**
 *This is the service for the management of countries
 *
 *
 * @Autowired
 * 
 * Country createCountry(User creator, Country country) 						create a country
 * final Country updateCountry(User updater, Long countryId, Country country) 	update a <code>Country</code> with the id <code>countryID</code> from the User 
 * final Country deleteCountry(User deleter, long countryId) 					sets a specified country as deleted
 * final Country undeleteCountry(long countryId) 								sets a specified country as undeleted
 * void removeCountry(long countryId) 											removes a specified counry from the database permanently
 * Optional<Country> findCountryById(long countryId)							get a <code>Country</code> with the ID <code>id</code>
 * List<Country> findCountryByName(String country_name) 						find all country by name case insensitive
 * boolean existsCountryByName(String country_name) 							exists a country by name case insensitive
 * List<Country> findCountryByCode(String code) 								find a country by country code case insensitive
 * boolean existsCountryByCode(String code)										exists a country by country code case insensitive
 * boolean existsCountryByNameOrCode(String country_name, String code) 			exists a country by name or country code case insensitive
 * List<Country> findCountryByQuery(Query query) 								returns the country defined by the search
 * boolean existsCountryByQuery(Query query)									exists a country by query
 */
@Service
public class CountryService {

	private static Logger logger = LogManager.getLogger(CountryService.class);
	
	private MongoOperations mongoOperations;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private UserRepository userRepository;

	// the sequenceGeneratorService for the auto-increment
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	private FieldService fieldService;

    @Autowired
    public CountryService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

	/**
	 * create a country
	 * 
	 * @param 	User creator	 						the creator
	 * @param 	Country country							the country to save
	 * @return 	Country									the created Country
	 * @throws ResourceNotFoundException 
	 * @throws DuplicateException 
	 */	
	public Country createCountry(User creator, Country country) throws ResourceNotFoundException, DuplicateException {
		logger.debug("CountryService::createCountry(" + creator + ", " + country + ")");
		
		Preconditions.checkNotNull(creator, "CountryService::createCountry(" + creator + ", " + country + "): The creator must not be null");
		Preconditions.checkNotNull(country, "CountryService::createCountry(" + creator + ", " + country + "): The adding country must not be null");

    	if ( !userRepository.existsById(creator.getId()) ) 
    		throw new ResourceNotFoundException("CountryService::createCountry(" + creator + ", " + country + "): Creator not found with the id :: " + creator.getId());
    	
    	// This is an alternative solution, as @Indexed(unique=true) from the country name and code field
   		// does not seem to work.
    	if ( this.existsCountryByNameOrCode(country.getName(), country.getCode()) ) 
    		throw new DuplicateException("CountryService::createCountry(" + creator + ", " + country + "): Country name (" + country.getName() +  ") or code (" + country.getCode() + ") allready exists");
    	
    	country.setId(sequenceGeneratorService.generateSequence(Country.SEQUENCE_NAME));
    	country.setCreatedBy(creator);
    	country.setCreatedAt(LocalDateTime.now());
    	
        return countryRepository.save(country);
    }
	
    /**
     * update a <code>Country</code> with the id <code>countryID</code> from the User 
     * <code>updater</code>
     * 
     * If a <code>Country</code> with the id <code>countryID</code> not found this function will be
     * throw a ResourceNotFoundException
     * 
     * @param 	User updater							The user who updates this country
     * @param 	Long countryId							the id of the country to update
     * @param 	Country country							The country data to be updated
     * @return 	Country									the updated country
     * @throws ResourceNotFoundException 
     * @throws DuplicateException 
     */
    public final Country updateCountry(User updater, Long countryId, Country country) throws ResourceNotFoundException, DuplicateException {
		logger.debug("CountryService::updateCountry(" + updater + ", " + country + ")");

		Preconditions.checkNotNull(updater, "CountryService::updateCountry(User updater: " + updater + ", Long id: " + countryId + ", Country country: " + country + "): The updater user must not be null");
		Preconditions.checkNotNull(country, "CountryService::updateCountry(User updater: " + updater + ", Long id: " + countryId + ", Country country: " + country + "): The country to be updated must not be null");
		
        Country foundCountry = countryRepository.findById(countryId)
        		.orElseThrow(() -> new ResourceNotFoundException("CountryService::updateCountry(User updater: " + updater + ", Long id: " + countryId + ", Country country: " + country + "): Country not found for this id :: " + countryId));
         
    	if ( !userRepository.existsById(updater.getId()) ) 
    		throw new ResourceNotFoundException("CountryService::createCountry(" + updater + ", " + country + "): Creator not found with the id :: " + updater.getId());

        // This is an alternative solution, as @Indexed(unique=true) from the country name field does not
   		// seem to work.
    	if ( existsCountryByNameOrCode(country.getName(), country.getCode()) ) 
    		throw new DuplicateException("CountryService::updateCountry(" + updater + ", Long id: " + countryId + ", country: " + country + "): Country name or code already exists ");
    	
    	foundCountry.setUpdatedBy(updater);
    	foundCountry.setUpdatedAt(LocalDateTime.now());
    	foundCountry.setName(country.getName());
    	foundCountry.setCode(country.getCode());
    	foundCountry.setFlag(country.getFlag());

        return countryRepository.save(foundCountry);
    }    
    
    /**
     * sets a specified country as deleted
     * 
     * @param 	deleter								the user who sets this user as deleted 
     * @param 	countryId							the ID of the country who should be set as deleted
     * @return 	Country
     * @throws 	ResourceNotFoundException
     *      	NotDefinedException					if the updater or the user is zero
     */
    public final Country deleteCountry(User deleter, long countryId) throws ResourceNotFoundException, NotDefinedException  {
		logger.debug("CountryService::deleteCountry(" + deleter + ", " + countryId + ")");
    	
		Preconditions.checkNotNull(deleter, "CountryService::deleteCountry(User deleter: " + deleter + ", Long id: " + countryId + "): The deleter user must not be null");

        User foundDeleter = userRepository.findById(deleter.getId())
        		.orElseThrow(() -> new ResourceNotFoundException("CountryService::deleteCountry(User deleter: " + deleter + ", long id: " + countryId + "): The deleter not found"));
       	
        Country country = countryRepository.findById(countryId)
        		.orElseThrow(() -> new ResourceNotFoundException("CountryService::deleteCountry(User deleter: " + deleter + ", long id: " + countryId + "): The user not found with the ID: " + countryId));
        
        country.setDeletedBy(foundDeleter);
        country.setDeletedAt(LocalDateTime.now());
        
        return countryRepository.save(country);
    }
	    
    /**
     * sets a specified country as undeleted
     * 
     * @param 	countryId								the ID of the country who should be set as undeleted
     * @return 	Country
     * @throws 	ResourceNotFoundException
     */
    public final Country undeleteCountry(long countryId) throws ResourceNotFoundException {
		logger.debug("CountryService::undeleteCountry(" + countryId + ")");
    	
        Country country = countryRepository.findById(countryId)
        		.orElseThrow(() -> new ResourceNotFoundException("CountryService::undeleteCountry(long id: " + countryId + "): Country not found for this id :: " + countryId));
        
        country.setDeletedBy(null);
        country.setDeletedAt(null);
        
        return countryRepository.save(country);
    }
    
    /**
     * removes a specified counry from the database permanently
     * 
     * @param countryId
     * @return void
     * @throws ResourceNotFoundException
     */
    public void removeCountry(long countryId) throws ResourceNotFoundException {
		logger.debug("CountryService::removeCountry(" + countryId + ")");
    	
        Country country = countryRepository.findById(countryId)
        		.orElseThrow(() -> new ResourceNotFoundException("UserService::removeUser(" + countryId + "): Country not found for this id"));
        
        countryRepository.delete(country);
    }
    
    /**
     * get a <code>Country</code> with the ID <code>id</code>
     * 
     * @param Long countryID
     * @return Optional<Country>
     */
    public Optional<Country> findCountryById(long countryId) {
		logger.debug("CountryService::findCountryById(" + countryId + ")");
    	
    	return countryRepository.findById(countryId);
    }
	
    /**
     * find all country by name case insensitive
     * 
     * @param String country_name							the name to found
     * @return List<Country>								the found countries
     */
    public List<Country> findCountryByName(String country_name) {
		logger.debug("CountryService::findCountryByName(" + country_name + ")");
    	
    	Criteria regex = Criteria.where("name").regex(
    			Pattern.compile(country_name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)   			
    			);
    	
    	Query query = new Query().addCriteria(regex);
    	
    	return mongoOperations.find(query, Country.class);
    }

    /**
     * exists a country by name case insensitive
     * 
     * @param String country_name							the name to found
     * @return List<Country>								the found countries
     */
    public boolean existsCountryByName(String country_name) {
		logger.debug("CountryService::existsCountryByName(" + country_name + ")");
    	
    	Criteria regex = Criteria.where("name").regex(
    			Pattern.compile(country_name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)   			
    			);
    	
    	Query query = new Query().addCriteria(regex);
    	
    	return mongoOperations.exists(query, Country.class);
    }
    
    /**
     * find a country by country code case insensitive
     * 
     * @param String code								the country code to found
     * @return List<Country>								the found countries
     */
    public List<Country> findCountryByCode(String code) {
		logger.debug("CountryService::findCountryByCode(" + code + ")");
    	
    	Criteria regex = Criteria.where("code").regex(
    			Pattern.compile(code, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)   			
    			);
    	
    	Query query = new Query().addCriteria(regex);
    	
    	return mongoOperations.find(query, Country.class);
    }
    
    /**
     * exists a country by country code case insensitive
     * 
     * @param String code									the country code to found
     * @return List<Country>								the found countries
     */
    public boolean existsCountryByCode(String code) {
		logger.debug("CountryService::existsCountryByCode(" + code + ")");
    	
    	Criteria regex = Criteria.where("code").regex(
    			Pattern.compile(code, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)   			
    			);
    	
    	Query query = new Query().addCriteria(regex);
    	
    	return mongoOperations.exists(query, Country.class);
    }
    
    /**
     * find a country by name or country code case insensitive
     * 
     * @param String country_name							the name to found
     * @param String code									or the code to found
     * @return List<Country>								the found countries
     */
/*    
 *  CHECK
    public List<Country> findCountryByNameOrCode(String country_name, String code) {
		logger.debug("CountryService::findCountryByNameOrCountryCode(" + country_name + ", " + code + ")");

    	Criteria regex = Criteria.where("name").regex(
    			Pattern.compile(country_name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)   			
    		).orOperator(Criteria.where("code").regex(
       			Pattern.compile(code, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)   			
    		));
    	
    	Query query = new Query().addCriteria(regex);
    	
    	return mongoOperations.find(query, Country.class);
    }
*/
    /**
     * exists a country by name or country code case insensitive
     * 
     * @param String country_name							the name to found
     * @param String code									or the code to found
     * @return List<Country>								the found countries
     */
    
    public boolean existsCountryByNameOrCode(String country_name, String code) {
		logger.debug("CountryService::existsCountryByNameOrCountryCode(" + country_name + ", " + code + ")");
    
		return 	this.existsCountryByName(country_name) || this.existsCountryByCode(code);
		/*
		 * CHECK:
		 * 
    	Criteria regex = Criteria.where("name").regex(
    			Pattern.compile(country_name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)   			
    		).orOperator(Criteria.where("code").regex(
       			Pattern.compile(code, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)   			
    		));
    	
    	Query query = new Query().addCriteria(regex);
    	
    	return mongoOperations.exists(query, Country.class);
    	*/
    }
    
    /**
     * returns the country defined by the search
     * 
     * @param Query query									the search 
     * @return List<Country>								the found country
     */
    public List<Country> findCountryByQuery(Query query) {
    	logger.debug("CountryService::findCountryByQuery(" + query + ")");

    	return mongoOperations.find(query, Country.class);
    }

    /**
     * exists a country by query
     * 
     * @param Query query								the search 
     * @return boolean									the country was found
     */
    public boolean existsCountryByQuery(Query query) {
    	logger.debug("CountryService::existsByQuery(" + query + ")");

    	return mongoOperations.exists(query, Country.class);
    }


}


