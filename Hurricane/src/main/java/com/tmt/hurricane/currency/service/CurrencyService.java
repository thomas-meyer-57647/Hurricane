package com.tmt.hurricane.currency.service;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @package     database
 * @version		0.1.4
 --------------------------------------------------------------------------------*/

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.tmt.hurricane.country.model.Country;
import com.tmt.hurricane.currency.model.Currency;
import com.tmt.hurricane.currency.repository.CurrencyRepository;
import com.tmt.hurricane.exceptions.DuplicateException;
import com.tmt.hurricane.exceptions.ResourceNotFoundException;
import com.tmt.hurricane.helper.database.service.SequenceGeneratorService;
import com.tmt.hurricane.services.field.FieldService;
import com.tmt.hurricane.user.model.User;
import com.tmt.hurricane.user.repository.UserRepository;

/**
 *This is the service for the management of countries
 */
@Service
public class CurrencyService {

	private static Logger logger = LogManager.getLogger(CurrencyService.class);
	
	private MongoOperations mongoOperations;
	
	@Autowired
	private CurrencyRepository currencyRepository;
	
	@Autowired
	private UserRepository userRepository;

	// the sequenceGeneratorService for the auto-increment
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	private FieldService fieldService;

    @Autowired
    public CurrencyService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
    
	/**
	 * create a currency
	 * 
	 * @param 	User creator	 						the creator
	 * @param 	Currency currency							the currency to save
	 * @return 	Currency									the created Currency
	 * @throws ResourceNotFoundException 
	 * @throws DuplicateException 
	 */	
	public Currency createCurrency(User creator, Currency currency) throws ResourceNotFoundException, DuplicateException {
		logger.debug("CurrencyService::createCurrency(User(" + creator + ", " + currency + ")");
		
		Preconditions.checkNotNull(creator, "CurrencyService::createCurrency(" + creator + ", " + currency + "): The creator must not be null");
		Preconditions.checkNotNull(currency, "CurrencyService::createCurrency(" + creator + ", " + currency + "): The adding currency must not be null");

    	if ( !userRepository.existsById(creator.getId()) ) 
    		throw new ResourceNotFoundException("CurrencyService::createCurrency(" + creator + ", " + currency + "): Creator not found with the id :: " + creator.getId());
    	
    	// This is an alternative solution, as @Indexed(unique=true) from the currency name and code field
   		// does not seem to work.
    	if ( this.existsCurrencyByName(currency.getName()) ) 
    		throw new DuplicateException("CurrencyService::createCurrency(" + creator + ", " + currency + "): Currency name (" + currency.getName() +  ") allready exists");
    	
    	if ( this.existsCurrencyByCountry(currency.getCountry().getId()) ) 
    		throw new DuplicateException("CurrencyService::createCurrency(" + creator + ", " + currency + "): Currency name (" + currency.getCountry() +  ") allready exists");
    	
    	currency.setId(sequenceGeneratorService.generateSequence(Currency.SEQUENCE_NAME));
    	currency.setCreatedBy(creator);
    	currency.setCreatedAt(LocalDateTime.now());
    	
        return currencyRepository.save(currency);
    }
	
    /**
     * removes a specified currency from the database permanently
     * 
     * @param countryId
     * @return void
     * @throws ResourceNotFoundException
     */
    public void removeCurrency(long countryId) throws ResourceNotFoundException {
		logger.debug("CurrencyService::removeCurrency(" + countryId + ")");
    	
        Currency currency = currencyRepository.findById(countryId)
        		.orElseThrow(() -> new ResourceNotFoundException("CurrencyService::removeCurrency(" + countryId + "): Currency not found for this id"));
        
        currencyRepository.delete(currency);
    }
	
    /**
     * get a <code>Currency</code> with the ID <code>id</code>
     * 
     * @param Long ccurrencyID
     * @return Optional<Currency>
     */
    public Optional<Currency> findCurrencyById(long currency_id) {
		logger.debug("CountryService::findCurrencyById(" + currency_id + ")");
    	return currencyRepository.findById(currency_id);
    }
    
    /**
     * find all currency by name case insensitive
     * 
     * @param String currency_name							the name to found
     * @return List<Currency>								the found currency
     */
    public List<Currency> findCurrencyByName(String currency_name) {
		logger.debug("CurrencyService::findCurrencyByName(" + currency_name + ")");
    	
    	Criteria regex = Criteria.where("name").regex(
    			Pattern.compile(currency_name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)   			
    			);
    	
    	Query query = new Query().addCriteria(regex);
    	
    	return mongoOperations.find(query, Currency.class);
    }

    /**
     * exists a currency name case insensitive
     * 
     * @param String currency_name							the name to found
     * @return List<Currency>								the found countries
     */
    public boolean existsCurrencyByName(String currency_name) {
		logger.debug("CurrencyService::existsCurrencyByName(" + currency_name + ")");
    	
		if ( currencyRepository.count() == 0L ) 
			return false;
		
    	Criteria regex = Criteria.where("name").regex(
    			Pattern.compile(currency_name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)   			
    			);
    	
    	Query query = new Query().addCriteria(regex);
    	
    	return mongoOperations.exists(query, Currency.class);
    }
    
    /**
     * find a currency by country id
     * 
     * @param long country_id								the country to found
     * @return Optional<Currency>							the found currency
     */
    public Optional<Currency> findCurrencyByCountryC(long country_id) {
		logger.debug("CurrencyService::findCurrencyByCountry(" + country_id + ")");
    	
		return currencyRepository.findByCountry(country_id);
    }
    
    /**
     * exists a currency by currency id
     * 
     * @param long country_id								the country id to found
     * @return Currency										the found currency
     */
    public boolean existsCurrencyByCountry(long country_id) {
		logger.debug("CurrencyService::existsCurrencyByCountry(" + country_id + ")");
    	
		return currencyRepository.existsById(country_id);
    }
    

}
