package com.tmt.hurricane.currency.service;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;

import com.mongodb.client.MongoClients;
import com.tmt.hurricane.country.model.Country;
import com.tmt.hurricane.country.repository.CountryRepository;
import com.tmt.hurricane.country.service.CountryService;
import com.tmt.hurricane.currency.model.Currency;
import com.tmt.hurricane.exceptions.DuplicateException;
import com.tmt.hurricane.exceptions.NotDefinedException;
import com.tmt.hurricane.exceptions.ResourceNotFoundException;
import com.tmt.hurricane.user.model.User;
import com.tmt.hurricane.user.service.UserService;

/*
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
*/

/**
 * test function for the currency service
 */
@SpringBootTest 
// @TestPropertySource(properties = {"logging.level.org.springframework.data.mongodb.core.MongoTemplate=INFO"}, value = "/embedded.properties")
class CurrencyServiceTest {
	
//	private static final String CONNECTION_STRING = "mongodb://%s:%d";
	
//	private MongodExecutable mongodExecutable;
    private MongoTemplate mongoTemplate;
    
	@Autowired 
	CurrencyService currencyService;
	
	@Autowired 
	CountryRepository countryRepository;
	
	@Autowired 
	UserService userService;

	@Autowired 
	private CountryService countryService;
/*
	@AfterEach
    void clean() {
        mongodExecutable.stop();
    }

    @BeforeEach
    void setup() throws Exception {
        String ip = "localhost";
        int port = 27017;

        ImmutableMongodConfig mongodbConfig = MongodConfig.builder()
          .version(Version.Main.PRODUCTION)
          .net(new Net(ip, port, Network.localhostIsIPv6()))
          .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodbConfig);
        mongodExecutable.start();
        mongoTemplate = new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, ip, port)), "hurricane");
    }	
*/    
	/**
	 * test create currency with minimalst valid data
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */
	@Test
	void testCreateCurrencyWithMinimalistValidData() throws ResourceNotFoundException, DuplicateException {
		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User creator = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundUser = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundUser.isPresent(), "testCreateCurrencyWithMinimalistValidData(): Creator (" + createdCreator + ") not stored");

		assertTrue(true);
		
		// create assoziated currency country
		String name = "Germany";
		String country_code = "DE";
		
		Country country = new Country(
				name,
				country_code, 
				null);
		
		Country createdCountry = countryService.createCountry(creator, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testCreateCurrencyWithMinimalistValidData(): Country (" + createdCountry + ") not stored");

		// create currency
		String currency_name = "Euro";
		String currency_symbol = "EUR";
		double currency_course = 1;
		
		Currency currency = new Currency(
				createdCountry, 
				currency_name,
				currency_symbol, 
				currency_course); 
			
		// test
		Currency createdCurrency = currencyService.createCurrency(creator, currency);
		Optional<Currency> foundCurrency = currencyService.findCurrencyById(createdCurrency.getId());
				
		assertTrue( foundCurrency.isPresent(), "testCreateCurrencyWithMinimalistValidData(): Currency (" + createdCurrency + ") not stored");

		assertNotNull(foundCurrency.get().getCreatedBy(), "testCreateCurrencyWithMinimalistValidData(): Country (" + foundCurrency.get() + ") created by not null");
		assertNotNull(foundCurrency.get().getCreatedAt(), "testCreateCurrencyWithMinimalistValidData(): Country (" + foundCurrency.get() + ") created date not set");
		assertNull(foundCurrency.get().getUpdatedBy(), "testCreateCurrencyWithMinimalistValidData(): Country (" + foundCurrency.get() + ") updater was set");
		assertNull(foundCurrency.get().getUpdatedAt(), "testCreateCurrencyWithMinimalistValidData(): Country (" + foundCurrency.get() + ") updater date was set");
		assertNull(foundCurrency.get().getDeletedBy(), "testCreateCurrencyWithMinimalistValidData(): Country (" + foundCurrency.get() + ") deleter was set");
		assertNull(foundCurrency.get().getDeletedAt(), "testCreateCurrencyWithMinimalistValidData(): Country (" + foundCurrency.get() + ") deleter date was set");
		assertEquals(foundCountry.get().getId(), foundCurrency.get().getCountry().getId(), "testCreateCurrencyWithMinimalistValidData(): Country (" + foundCurrency.get() + ") Country not identical with " + country);
		assertEquals(currency_name, foundCurrency.get().getName(), "testCreateCurrencyWithMinimalistValidData(): Country (" + foundCurrency.get() + ") Name not identical with " + name);
		assertEquals(currency_name, foundCurrency.get().getName(), "testCreateCurrencyWithMinimalistValidData(): Country (" + foundCurrency.get() + ") Name not identical with " + name);
		assertEquals(currency_symbol, foundCurrency.get().getSymbol(), "testCreateCurrencyWithMinimalistValidData(): Country (" + foundCurrency.get() + ") Currency Symbol identical with " + currency_symbol);
		assertEquals(currency_course, foundCurrency.get().getCourse(), "testCreateCurrencyWithMinimalistValidData(): Country (" + foundCurrency.get() + ") Currency Symbol identical with " + currency_course);
		
		// clean up
		currencyService.removeCurrency(foundCurrency.get().getId());
		countryService.removeCountry(foundCountry.get().getId());
		userService.removeUser(foundUser.get().getId());
	}
	
	/**
	 * test findCurrencyById 
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testFindCurrencyById() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User creator = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundUser = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundUser.isPresent(), "testFindCurrencyById(): Creator (" + createdCreator + ") not stored");
		
		// create assoziated currency country
		String name = "Germany";
		String country_code = "DE";
		
		Country country = new Country(
				name,
				country_code, 
				null);
		
		Country createdCountry = countryService.createCountry(creator, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testFindCurrencyById(): Country (" + createdCountry + ") not stored");

		// create currency
		String country_name = "Euro";
		String country_symbol = "EUR";
		double country_course = 1;
		
		Currency currency = new Currency(
				createdCountry, 
				country_name,
				country_symbol, 
				country_course); 
			
		Currency createdCurrency = currencyService.createCurrency(creator, currency);
		Optional<Currency> foundCurrency = currencyService.findCurrencyById(createdCurrency.getId());
				
		assertTrue( foundCurrency.isPresent(), "testFindCurrencyById(): Currency (" + createdCurrency + ") not stored");

		// test
		assertEquals(foundCurrency.get().getId(), currencyService.findCurrencyById(foundCurrency.get().getId()).get().getId(), "testFindCurrencyById(): Currency (" + createdCurrency + ") not identical ID");

		// clean up
		currencyService.removeCurrency(createdCountry.getId());
		countryService.removeCountry(createdCountry.getId());
		userService.removeUser(creator.getId());
	}

	/**
	 * test find currency mpt by ID 
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
/*	
	@Test
	void testFindCurrencyNotById() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User creator = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundUser = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundUser.isPresent(), "testFindCurrencyNotById(): Creator (" + createdCreator + ") not stored");
		
		// create assoziated currency country
		String name = "Germany";
		String country_code = "DE";
		
		Country country = new Country(
				name,
				country_code, 
				null);
		
		Country createdCountry = countryService.createCountry(creator, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testFindCurrencyNotById(): Country (" + createdCountry + ") not stored");

		// create currency
		String country_name = "Euro";
		String country_symbol = "EUR";
		double country_course = 1;
		
		Currency currency = new Currency(
				createdCountry, 
				country_name,
				country_symbol, 
				country_course); 
			
		Currency createdCurrency = currencyService.createCurrency(creator, currency);
		Optional<Currency> foundCurrency = currencyService.findCurrencyById(createdCurrency.getId());
				
		assertTrue( foundCurrency.isPresent(), "testFindCurrencyNotById(): Currency (" + createdCurrency + ") not stored");

		// test
		assertFalse( currencyService.findCurrencyById(-1).isPresent(), "testFindCountryNotById(): Currency (" + createdCurrency + ") not identical ID");

		// clean up
		currencyService.removeCurrency(createdCountry.getId());
		countryService.removeCountry(createdCountry.getId());
		userService.removeUser(creator.getId());
	}
*/	
	/**
	 * test valid removed currency
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
/*	
	@Test
	void testValidRemoveCurrency() throws ResourceNotFoundException, NotDefinedException, DuplicateException {

		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User creator = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundUser = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundUser.isPresent(), "testValidRemoveCurrency(): Creator (" + createdCreator + ") not stored");
		
		// create assoziated currency country
		String name = "Germany";
		String country_code = "DE";
		
		Country country = new Country(
				name,
				country_code, 
				null);
		
		Country createdCountry = countryService.createCountry(creator, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testValidRemoveCurrency(): Country (" + createdCountry + ") not stored");

		// create currency
		String country_name = "Euro";
		String country_symbol = "EUR";
		double country_course = 1;
		
		Currency currency = new Currency(
				createdCountry, 
				country_name,
				country_symbol, 
				country_course); 
			
		Currency createdCurrency = currencyService.createCurrency(creator, currency);
		Optional<Currency> foundCurrency = currencyService.findCurrencyById(createdCurrency.getId());
				
		assertTrue( foundCurrency.isPresent(), "testValidRemoveCurrency(): Currency (" + createdCurrency + ") not stored");
		
		// test
		currencyService.removeCurrency(foundCurrency.get().getId());
		
		Optional<Currency> foundRemovedCurrency = currencyService.findCurrencyById(foundCountry.get().getId());
		
		assertFalse( foundRemovedCurrency.isPresent(), "testValidRemoveCurrency(): Currency was not removed");
		
		// clean up
		countryService.removeCountry(createdCountry.getId());
		userService.removeUser(creator.getId());
	}
*/
	/**
	 * test removed currency with invalid index should throw ResourceNotFoundException
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
/*	
	@Test
	void testRemovedCurrencyWithInvalidIndexShouldThrowResourceNotFoundException() throws ResourceNotFoundException, NotDefinedException, DuplicateException {

		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User creator = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundUser = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundUser.isPresent(), "testRemovedCurrencyWithInvalidIndexShouldThrowResourceNotFoundException(): Creator (" + createdCreator + ") not stored");
		
		// create assoziated currency country
		String name = "Germany";
		String country_code = "DE";
		
		Country country = new Country(
				name,
				country_code, 
				null);
		
		Country createdCountry = countryService.createCountry(creator, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testRemovedCurrencyWithInvalidIndexShouldThrowResourceNotFoundException(): Country (" + createdCountry + ") not stored");

		// create currency
		String country_name = "Euro";
		String country_symbol = "EUR";
		double country_course = 1;
		
		Currency currency = new Currency(
				createdCountry, 
				country_name,
				country_symbol, 
				country_course); 
			
		Currency createdCurrency = currencyService.createCurrency(creator, currency);
		Optional<Currency> foundCurrency = currencyService.findCurrencyById(createdCurrency.getId());
				
		assertTrue( foundCurrency.isPresent(), "testRemovedCurrencyWithInvalidIndexShouldThrowResourceNotFoundException(): Currency (" + createdCurrency + ") not stored");
		
		// test
		assertThrows(ResourceNotFoundException.class, () -> countryService.removeCountry(-1), "testRemovedCurrencyWithInvalidIndexShouldThrowResourceNotFoundException(): A ResourceNotFoundException was not thrown when id is not exists");
		
		// clean up
		currencyService.removeCurrency(createdCurrency.getId());
		countryService.removeCountry(createdCountry.getId());
		userService.removeUser(creator.getId());
	}
*/	

}
