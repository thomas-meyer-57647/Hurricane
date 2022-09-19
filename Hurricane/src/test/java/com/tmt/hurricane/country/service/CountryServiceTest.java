package com.tmt.hurricane.country.service;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.bson.types.Binary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tmt.hurricane.country.model.Country;
import com.tmt.hurricane.country.repository.CountryRepository;
import com.tmt.hurricane.exceptions.DuplicateException;
import com.tmt.hurricane.exceptions.NotDefinedException;
import com.tmt.hurricane.exceptions.ResourceNotFoundException;
import com.tmt.hurricane.user.model.User;
import com.tmt.hurricane.user.service.UserService;

/**
 * test function for the country service
 * 
 * void testCreateCountryWithMinimalistValidData() 											test create Crountry with minimalst valid data
 * void testCreateCountryWithNullCreatorShouldThrowNotDefinedException()    				test create Country with null creator should throw NotDefinedException
 * void testCreateCountryWithNotExistsCreatorShouldThrowResourceNotFound() 					test create Country with not exists create should throw RessourceNotFound  
 * void testCreateCountryWithNullCountryShouldThrowResourceNotFound() 						test create Country with null country should throw NotDefinedException  
 * void testCreateCountryWithDuplicatedNameShouldThrowResourceNotFound() 					test create Country with duplicated name should throw DuplicatetException  
 * void testCreateCountryWithDuplicatedCountryCodeShouldThrowResourceNotFound() 			test create Country with duplicated name should throw DuplicatetException  
 * void testUpdateCountryWithValidData() 													test update Country with valid data
 * void testUpdateCountryWithNullUpdaterShouldThrowNotDefinedException() 					test update Country with null Updater should throw NotDefinedException
 * void testUpdateCountryWithNotExistsUserShouldThrowResourceNotFoundException() 			test update Country with not exists user should throw ResourceNotFoundException
 * void testUpdateCountryWithInvalidIDShouldThrowResourceNotFoundException() 				test update Country with invalid id should throw ResourceNotFoundException
 * void testUpdateCountryWithNullUpdateCountryShouldThrowResourceNotFoundException()		test update Country with null update country throw NotDefinedException
 * void testValidDeleteCountry() 															test valid delete country
 * void testDeleteUserWithNullDeleterShouldThrowNotDefinedException() 						test Delete country with null deleter should throw NotDefinedException
 * void testDeleteCountryWithNotExistsDeleterShouldThrowResourceNotFoundException() 		test Delete country with not exists deleter should throw ResourceNotFoundException
 * void testDeleteUserWithNotExistsUserIDShouldThrowResourceNotFoundException() 			test Delete country with not exists country id should throw ResourceNotFoundException
 * void testValidUndeleteCountry() 															test valid undelete country
 * void testUndeleteCountryWithInvalidIndexShouldThrowResourceNotFound() 					test undelete country with invalid Index Should Throw ResourceNotFound
 * void testValidRemoveCountry() 															test valid removed vounty
 * void testRemovedCountryWithInvalidIndexShouldThrowResourceNotFoundException() 			test removed country with invalid index should throw ResourceNotFoundException
 * void testFindCountryNotById() 															test find country mpt by ID 
 * void testFindCountryByName() 															test findCountryByName 
 * void existCountryByName() 																test existsCountryByName 
 * void testFindCountryByNotDefinedName() 													test exists country by not defined 
 * void testFindCountryByCode() 															test findCountryByCountryCode 
 * void testFindCountryByNotDefinedCode() 													test find country by not defined country code 
 * void testExistsCountryByCode() 															test existsCountryByCountryCode 
 * void testExistsCountryByNotDefinedCode() 												test exists country by not defined country code 
 * void testExistsCountryByNameOrCountryCode() 												test existsCountryByNameOrCountryCode 
 * void testExistsCountryByNotDefinedNameOrCode() 											test existsCountryByNotDefinedNameOrCountryCode 
 */
@SpringBootTest
class CountryServiceTest {
	
	@Autowired 
	CountryService countryService;
	
	@Autowired 
	CountryRepository countryRepository;
	
	@Autowired 
	UserService userService;

	/**
	 * test create Crountry with minimalst valid data
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */
	@Test
	void testCreateCountryWithMinimalistValidData() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
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
		
		assertTrue( foundUser.isPresent(), "testCreateCountryWithMinimalistValidData(): Creator (" + createdCreator + ") not stored");
		
		// create country
		String name = "Germany";
		String country_code = "DE";
		
		Country country = new Country(
				name,
				country_code, 
				null);
		
		Country createdCountry = countryService.createCountry(creator, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testCreateCountryWithMinimalistValidData(): Country (" + createdCountry + ") not stored");

		// test
		assertNotNull(foundCountry.get().getCreatedBy(), "testCreateCountryWithMinimalistValidData(): Country (" + foundCountry.get() + ") created by not null");
		assertNotNull(foundCountry.get().getCreatedAt(), "testCreateCountryWithMinimalistValidData(): Country (" + foundCountry.get() + ") created date not set");
		assertNull(foundCountry.get().getUpdatedBy(), "testCreateCountryWithMinimalistValidData(): Country (" + foundCountry.get() + ") updater was set");
		assertNull(foundCountry.get().getUpdatedAt(), "testCreateCountryWithMinimalistValidData(): Country (" + foundCountry.get() + ") updater date was set");
		assertNull(foundCountry.get().getDeletedBy(), "testCreateCountryWithMinimalistValidData(): Country (" + foundCountry.get() + ") deleter was set");
		assertNull(foundCountry.get().getDeletedAt(), "testCreateCountryWithMinimalistValidData(): Country (" + foundCountry.get() + ") deleter date was set");
		assertEquals(name, foundCountry.get().getName(), "testCreateCountryWithMinimalistValidData(): Country (" + foundCountry.get() + ") Name not identical with " + name);
		assertEquals(country_code, foundCountry.get().getCode(), "testCreateCountryWithMinimalistValidData(): Country (" + foundCountry.get() + ") Country Code not identical with " + country_code);
		assertNull(foundCountry.get().getFlag(), "testCreateCountryWithMinimalistValidData(): Country (" + foundCountry.get() + ") Flag is not null");
		
		// clean up
		countryService.removeCountry(foundCountry.get().getId());
		userService.removeUser(foundUser.get().getId());
	}

	/**
	 * test create Country with null creator should throw NotDefinedException  
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */
	@Test
	void testCreateCountryWithNullCreatorShouldThrowNotDefinedException() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create country
		String name = "Germany";
		String country_code = "DE";
		
		Country country = new Country(
				name,
				country_code, 
				null);
		
		// test
		assertThrows(NullPointerException.class, () -> countryService.createCountry(null, country), "testCreateCountryWithNullCreatorShouldThrowNotDefinedException(): Null Create User with didn't throw NotDefinedException");
	}
	
	/**
	 * test create Country with not exists create should throw RessourceNotFound  
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 */
	@Test
	void testCreateCountryWithNotExistsCreatorShouldThrowResourceNotFound() throws ResourceNotFoundException, NotDefinedException {
		// not exists creator
		String notExistsCreatorFirstname = "fake user firstname";
		String notExistsCreatorLastname = "fake user lastname";
		String notExistsCreatorEmail = "fake@email.com";
		String notExistsCreatorPassword = "fake password";
		
		User notExistsCreator = new User(
				notExistsCreatorFirstname,				// password 
				notExistsCreatorLastname, 				// lastname
				notExistsCreatorEmail, 					// email
				notExistsCreatorPassword				// password
				);
		notExistsCreator.setId(-1);
				
		// create country
		String name = "Germany";
		String country_code = "DE";
		
		Country country = new Country(
				name,
				country_code, 
				null);		
		
		// test
		assertThrows(ResourceNotFoundException.class, () -> countryService.createCountry(notExistsCreator, country), "testCreateCountryWithNotExistsCreatorShouldThrowResourceNotFound(): Not exists Creator didn't throw ResourceNotFoundException");
	}
	
	/**
	 * test create Country with null country should throw NotDefinedException  
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testCreateCountryWithNullCountryShouldThrowResourceNotFound() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		
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
		
		assertTrue( foundUser.isPresent(), "testCreateCountryWithNullCountryShouldThrowResourceNotFound(): Creator (" + createdCreator + ") not stored");

		// test
		assertThrows(NullPointerException.class, () -> countryService.createCountry(creator, null), "testCreateCountryWithNullCountryShouldThrowResourceNotFound(): Not exists Creator didn't throw NotDefinedException");
		
		// clean up
		userService.removeUser(foundUser.get().getId());	
	}

	/**
	 * test create Country with duplicated name should throw DuplicatetException  
	 * 
	 * @throws DuplicateException 
	 */	
	@Test
	void testCreateCountryWithDuplicatedNameShouldThrowResourceNotFound() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
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
		
		assertTrue( foundUser.isPresent(), "testCreateCountryWithDuplicatedNameShouldThrowResourceNotFound(): Creator (" + createdCreator + ") not stored");

		// create country 1
		String name1 = "Germany";
		String country_code1 = "DE";
		
		Country country1 = new Country(
				name1,
				country_code1, 
				null);
		
		Country createdCountry1 = countryService.createCountry(creator, country1);
		Optional<Country> foundCountry1 = countryRepository.findById(createdCountry1.getId());
		
		assertTrue( foundCountry1.isPresent(), "testCreateCountryWithDuplicatedNameShouldThrowResourceNotFound(): Country (" + createdCountry1 + ") not stored");
		
		// create country 2
		String name2 = "Germany";
		String country_code2 = "FR";
		
		Country country2 = new Country(
				name2,
				country_code2, 
				null);
	
		// test
		assertThrows(DuplicateException.class, () -> countryService.createCountry(creator, country2), "testCreateCountryWithDuplicatedNameShouldThrowResourceNotFound(): Duplicate country name didn't throw DuplicateException");
		
		// clean up
		countryService.removeCountry(foundCountry1.get().getId());	
		userService.removeUser(foundUser.get().getId());	
	}

	/**
	 * test create Country with duplicated name should throw DuplicatetException  
	 * 
	 * @throws DuplicateException 
	 */	
	@Test
	void testCreateCountryWithDuplicatedCountryCodeShouldThrowResourceNotFound() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
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
		
		assertTrue( foundUser.isPresent(), "testCreateCountryWithDuplicatedCountryCodeShouldThrowResourceNotFound(): Creator (" + createdCreator + ") not stored");

		// create country 1
		String name1 = "Germany";
		String country_code1 = "DE";
		
		Country country1 = new Country(
				name1,
				country_code1, 
				null);
		
		Country createdCountry1 = countryService.createCountry(creator, country1);
		Optional<Country> foundCountry1 = countryRepository.findById(createdCountry1.getId());
		
		assertTrue( foundCountry1.isPresent(), "testCreateCountryWithDuplicatedCountryCodeShouldThrowResourceNotFound(): Country (" + createdCountry1 + ") not stored");
		
		// create country 2
		String name2 = "France";
		String country_code2 = "de";
		
		Country country2 = new Country(
				name2,
				country_code2, 
				null);
	
		// test
		assertThrows(DuplicateException.class, () -> countryService.createCountry(creator, country2), "testCreateCountryWithDuplicatedCountryCodeShouldThrowResourceNotFound(): Douplicate Country Code didn't throw DuplicateException");
		
		// clean up
		countryService.removeCountry(foundCountry1.get().getId());	
		userService.removeUser(foundUser.get().getId());	
	}

	/**
	 * test update Country with valid data
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */
	@Test
	void testUpdateCountryWithValidData() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create updater
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User updater = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdUpdater = userService.createUser(null, updater);
		Optional<User> foundUpdater = userService.findUserById(createdUpdater.getId());
		
		assertTrue( foundUpdater.isPresent(), "testUpdateCountryWithValidData(): Updater (" + updater + ") not stored");

		// create country
		String country_name = "Germany";
		String country_code = "de";
		Binary country_flag = null;
		
		Country country = new Country(
				country_name,					 
				country_code, 					
				country_flag 					
		);

		Country createdCountry = countryService.createCountry(updater, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testUpdateCountryWithValidData(): Country (" + country + ") not stored");
		
		// update country
		String updated_country_name = "France";
		String updated_country_code = "fr";
		Binary updated_country_flag = null;
		
		Country updatedCountryData = new Country(
				updated_country_name,			 
				updated_country_code, 			
				updated_country_flag
		);
		
		Country updatedCountry = countryService.updateCountry(updater, foundCountry.get().getId(), updatedCountryData);
		Optional<Country> foundUpdatedCountry = countryService.findCountryById(updatedCountry.getId());
		
		assertTrue( foundUpdatedCountry.isPresent(), "testUpdateCountryWithValidData(): Updated Country (" + updatedCountry + ") not stored");
		assertEquals(createdCountry.getId(), foundUpdatedCountry.get().getId(), "testUpdateCountryWithValidData(): Updated Country (" + foundUpdatedCountry.get() + ") ID not identical with " + createdCountry.getId());
		assertEquals(createdUpdater.getId(), foundUpdatedCountry.get().getCreatedBy().getId(), "testUpdateCountryWithValidData(): Updated Country (" + foundUpdatedCountry.get() + ") creator was not identical with: " + createdUpdater.getId());
		assertEquals(foundCountry.get().getCreatedAt(), foundUpdatedCountry.get().getCreatedAt(), "testUpdateCountryWithValidData(): Updated Country (" + foundUpdatedCountry.get()  + ") creator date was not identical with: " + foundCountry.get().getCreatedAt() );
		assertEquals(foundUpdater.get().getId(), foundUpdatedCountry.get().getUpdatedBy().getId(), "testUpdateCountryWithValidData(): Updated Country (" + foundUpdatedCountry.get() + ") updater was not set to " + foundUpdater.get().getId());
		assertNotNull(foundUpdatedCountry.get().getUpdatedAt(), "testUpdateCountryWithValidData(): Updated Country (" + foundUpdatedCountry.get() + ") updater date was not set");
		assertNull(foundUpdatedCountry.get().getDeletedBy(), "testUpdateCountryWithValidData(): updated Country (" + foundUpdatedCountry.get() + ") deleter was set");
		assertNull(foundUpdatedCountry.get().getDeletedAt(), "testUpdateCountryWithValidData(): Updated Country (" + foundUpdatedCountry.get() + ") deleter date was set");
		assertEquals(updated_country_name, foundUpdatedCountry.get().getName(), "testUpdateCountryWithValidData(): Updated Country (" + foundUpdatedCountry.get() + ") Name was set to " + updated_country_name);
		assertEquals(updated_country_code, foundUpdatedCountry.get().getCode(), "testUpdateCountryWithValidData(): Updated Country (" + foundUpdatedCountry.get() + ") Country Code not set to " + updated_country_code);
		assertEquals(updated_country_flag, foundUpdatedCountry.get().getFlag(), "testUpdateCountryWithValidData(): Updated Country (" + foundUpdatedCountry.get() + ") Flag not set to " + updated_country_flag);
		
		// clean up
		countryService.removeCountry(foundUpdatedCountry.get().getId());
		userService.removeUser(foundUpdater.get().getId());
	}

	/**
	 * test update Country with null Updater should throw NotDefinedException
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */
	@Test
	void testUpdateCountryWithNullUpdaterShouldThrowNotDefinedException() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create user
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User updater = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdUpdater = userService.createUser(null, updater);
		Optional<User> foundUpdater = userService.findUserById(createdUpdater.getId());
		
		assertTrue( foundUpdater.isPresent(), "testUpdateCountryWithNullUpdaterShouldThrowNotDefinedException(): Updater (" + createdUpdater + ") not stored");

		// create country
		String country_name = "Germany";
		String country_code = "de";
		Binary country_flag = null;
		
		Country country = new Country(
				country_name,					 
				country_code, 					
				country_flag 					
		);

		Country createdCountry = countryService.createCountry(updater, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testUpdateCountryWithNullUpdaterShouldThrowNotDefinedException(): Country (" + country + ") not stored");
		
		// update country
		String updated_country_name = "France";
		String updated_country_code = "fr";
		Binary updated_country_flag = null;
		
		Country updatedCountryData = new Country(
				updated_country_name,			 
				updated_country_code, 			
				updated_country_flag
		);
		
		// test
		assertThrows(NullPointerException.class, () -> countryService.updateCountry(null, createdCountry.getId(), updatedCountryData), "testUpdateCountryWithNullUpdaterShouldThrowNotDefinedException(): NotDefinedException was not throw if updater ist null");
		
		// clean up
		countryService.removeCountry(foundCountry.get().getId());
		userService.removeUser(foundUpdater.get().getId());
	}

	/**
	 * test update Country with not exists user should throw ResourceNotFoundException
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */
	@Test
	void testUpdateCountryWithNotExistsUserShouldThrowResourceNotFoundException() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create user
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User updater = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdUpdater = userService.createUser(null, updater);
		Optional<User> foundUpdater = userService.findUserById(createdUpdater.getId());
		
		assertTrue( foundUpdater.isPresent(), "testUpdateCountryWithNotExistsUserShouldThrowResourceNotFoundException(): Updater (" + createdUpdater + ") not stored");

		// create country
		String country_name = "Germany";
		String country_code = "de";
		Binary country_flag = null;
		
		Country country = new Country(
				country_name,					 
				country_code, 					
				country_flag 					
		);

		Country createdCountry = countryService.createCountry(updater, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testUpdateCountryWithNotExistsUserShouldThrowResourceNotFoundException(): Country (" + country + ") not stored");
		
		// update country
		String updated_country_name = "France";
		String updated_country_code = "fr";
		Binary updated_country_flag = null;
		
		Country updatedCountryData = new Country(
				updated_country_name,			 
				updated_country_code, 			
				updated_country_flag
		);
		
		// test
		long oldID = updater.getId();		// store old id
		updater.setId(-1);					
		
		assertThrows(ResourceNotFoundException.class, () -> countryService.updateCountry(updater, createdCountry.getId(), updatedCountryData), "testUpdateCountryWithNullUpdaterShouldThrowNotDefinedException(): NotDefinedException was not throw if updater not valid");	
		
		// clean up
		countryService.removeCountry(foundCountry.get().getId());
		userService.removeUser(oldID);
	}

	/**
	 * test update Country with invalid id should throw ResourceNotFoundException
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testUpdateCountryWithInvalidIDShouldThrowResourceNotFoundException() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create updater
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User updater = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdUpdater = userService.createUser(null, updater);
		Optional<User> foundUpdater = userService.findUserById(createdUpdater.getId());
		
		assertTrue( foundUpdater.isPresent(), "testUpdateCountryWithInvalidIDShouldThrowResourceNotFoundException(): Updater (" + updater + ") not stored");

		// create country
		String country_name = "Germany";
		String country_code = "de";
		Binary country_flag = null;
		
		Country country = new Country(
				country_name,					 
				country_code, 					
				country_flag 					
		);

		Country createdCountry = countryService.createCountry(updater, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testUpdateCountryWithInvalidIDShouldThrowResourceNotFoundException(): Country (" + country + ") not stored");
		
		// update country
		String updated_country_name = "France";
		String updated_country_code = "fr";
		Binary updated_country_flag = null;
		
		Country updatedCountryData = new Country(
				updated_country_name,			 
				updated_country_code, 			
				updated_country_flag
		);
		
		Country updatedCountry = countryService.updateCountry(updater, foundCountry.get().getId(), updatedCountryData);
		Optional<Country> foundUpdatedCountry = countryService.findCountryById(updatedCountry.getId());

		assertThrows(ResourceNotFoundException.class, () -> countryService.updateCountry(updater, -1L, updatedCountryData), "testUpdateCountryWithInvalidIDShouldThrowResourceNotFoundException(): NotDefinedException was not throw if id is invalid");	
		
		// clean up
		countryService.removeCountry(foundUpdatedCountry.get().getId());
		userService.removeUser(foundUpdater.get().getId());
	}
	
	/**
	 * test update Country with null update country throw NotDefinedException
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testUpdateCountryWithNullUpdateCountryShouldThrowResourceNotFoundException() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create updater
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User updater = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdUpdater = userService.createUser(null, updater);
		Optional<User> foundUpdater = userService.findUserById(createdUpdater.getId());
		
		assertTrue( foundUpdater.isPresent(), "testUpdateCountryWithNullUpdateCountryShouldThrowResourceNotFoundException(): Updater (" + updater + ") not stored");

		// create country
		String country_name = "Germany";
		String country_code = "de";
		Binary country_flag = null;
		
		Country country = new Country(
				country_name,					 
				country_code, 					
				country_flag 					
		);

		Country createdCountry = countryService.createCountry(updater, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testUpdateCountryWithNullUpdateCountryShouldThrowResourceNotFoundException(): Country (" + country + ") not stored");
		
		// update country
		String updated_country_name = "France";
		String updated_country_code = "fr";
		Binary updated_country_flag = null;
		
		Country updatedCountryData = new Country(
				updated_country_name,			 
				updated_country_code, 			
				updated_country_flag
		);
		
		Country updatedCountry = countryService.updateCountry(updater, foundCountry.get().getId(), updatedCountryData);
		Optional<Country> foundUpdatedCountry = countryService.findCountryById(updatedCountry.getId());
		
		assertThrows(NullPointerException.class, () -> countryService.updateCountry(updater, createdCountry.getId(), null), "testUpdateCountryWithNullUpdateCountryShouldThrowResourceNotFoundException(): NotDefinedException was the update data not defined");	
		
		// clean up
		countryService.removeCountry(foundUpdatedCountry.get().getId());
		userService.removeUser(foundUpdater.get().getId());
	}

	/**
	 * test valid delete country
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testValidDeleteCountry() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create deleter
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User deleter = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdDeleter = userService.createUser(null, deleter);
		Optional<User> foundDeleter = userService.findUserById(createdDeleter.getId());
		
		assertTrue( foundDeleter.isPresent(), "testValidDeleteCountry(): Deleter (" + deleter + ") not stored");

		// create country
		String country_name = "Germany";
		String country_code = "de";
		Binary country_flag = null;
		
		Country country = new Country(
				country_name,					 
				country_code, 					
				country_flag 					
		);

		Country createdCountry = countryService.createCountry(deleter, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testValidDeleteCountry(): Country (" + country + ") not stored");
		
		// test
		Country deletedCountry = countryService.deleteCountry(deleter, foundCountry.get().getId());
		
		Optional<Country> foundDeletedCountry = countryService.findCountryById(deletedCountry.getId());
		assertTrue( foundDeletedCountry.isPresent(), "testValidDeleteCountry(): Deleted Country (" + deletedCountry + ") not stored");

		assertEquals(foundCountry.get().getCreatedBy(), foundDeletedCountry.get().getCreatedBy(), "testValidDeleteCountry(): Deleted Country (" + foundDeletedCountry.get() + "): creator was changed");
		assertEquals(foundCountry.get().getCreatedAt(), foundDeletedCountry.get().getCreatedAt(), "testValidDeleteCountry(): Deleted Country (" + foundDeletedCountry.get() + "): creator date was changed");
		assertEquals(foundCountry.get().getUpdatedBy(), foundDeletedCountry.get().getUpdatedBy(), "testValidDeleteCountry(): Deleted Country (" + foundDeletedCountry.get() + "): updater was changed");
		assertEquals(foundCountry.get().getUpdatedAt(), foundDeletedCountry.get().getUpdatedAt(), "testValidDeleteCountry(): Deleted Country (" + foundDeletedCountry.get() + "): updater date was changed");
		assertEquals(createdDeleter.getId(), foundDeletedCountry.get().getDeletedBy().getId(), "testValidDeleteCountry(): Deleted Country (" + foundDeletedCountry.get() + "): deleter was not set");
		assertNotNull(foundDeletedCountry.get().getDeletedAt(), "testValidDeleteCountry(): Deleted Country (" + foundDeletedCountry.get() + "): deleter date was not set");
		assertEquals(country_name, foundDeletedCountry.get().getName(), "testValidDeleteCountry(): Deleted Country (" + foundDeletedCountry.get() + "): name was changed");
		assertEquals(country_code, foundDeletedCountry.get().getCode(), "testValidDeleteCountry(): Deleted Country (" + foundDeletedCountry.get() + "): country code was changed");
		assertEquals(country_flag, foundDeletedCountry.get().getFlag(), "testValidDeleteCountry(): Deleted Country (" + foundDeletedCountry.get() + "): flag was changed");

		// clean up
		countryService.removeCountry(foundCountry.get().getId());
		userService.removeUser(foundDeleter.get().getId());
	}

	/**
	 * test Delete country with null deleter should throw NotDefinedException
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */
	@Test
	void testDeleteUserWithNullDeleterShouldThrowNotDefinedException() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User deleter = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdDeleter = userService.createUser(null, deleter);
		Optional<User> foundDeleter = userService.findUserById(createdDeleter.getId());
		
		assertTrue( foundDeleter.isPresent(), "testValidDeleteCountry(): Deleter (" + deleter + ") not stored");
		
		// create country
		String country_name = "Germany";
		String country_code = "de";
		Binary country_flag = null;
		
		Country country = new Country(
				country_name,					 
				country_code, 					
				country_flag 					
		);

		Country createdCountry = countryService.createCountry(deleter, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testDeleteUserWithNullDeleterShouldThrowNotDefinedException(): Country (" + country + ") not stored");

		// test
		assertThrows(NullPointerException.class, () -> countryService.deleteCountry(null, foundCountry.get().getId()), "testDeleteUserWithNullDeleterShouldThrowNotDefinedException(): A NotDefinedException was not thrown when deleter ist null");

		// clean up
		countryService.removeCountry(foundCountry.get().getId());		
		userService.removeUser(foundDeleter.get().getId());		
	}

	/**
	 * test Delete country with not exists deleter should throw ResourceNotFoundException
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */
	@Test
	void testDeleteCountryWithNotExistsDeleterShouldThrowResourceNotFoundException() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create user
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User deleter = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdDeleter = userService.createUser(null, deleter);
		Optional<User> foundDeleter = userService.findUserById(createdDeleter.getId());
		
		assertTrue( foundDeleter.isPresent(), "testDeleteCountryWithNotExistsDeleterShouldThrowResourceNotFoundException(): User (" + deleter + ") not stored");

		// create country
		String country_name = "Germany";
		String country_code = "de";
		Binary country_flag = null;
		
		Country country = new Country(
				country_name,					 
				country_code, 					
				country_flag 					
		);

		Country createdCountry = countryService.createCountry(deleter, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testDeleteCountryWithNotExistsDeleterShouldThrowResourceNotFoundException(): Country (" + country + ") not stored");
		
		// test
		long oldIdx = createdDeleter.getId();
		createdDeleter.setId(-1);
		
		assertThrows(ResourceNotFoundException.class, () -> countryService.deleteCountry(createdDeleter, foundCountry.get().getId()), "testDeleteCountryWithNotExistsDeleterShouldThrowResourceNotFoundException(): A NotDefinedException was not thrown when deleter ist null");
		
		// clean up
		countryService.removeCountry(foundCountry.get().getId());
		userService.removeUser(oldIdx);
	}

	/**
	 * test Delete country with not exists country id should throw ResourceNotFoundException
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */
	@Test
	void testDeleteUserWithNotExistsUserIDShouldThrowResourceNotFoundException() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		
		// create user
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User deleter = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdDeleter = userService.createUser(null, deleter);
		Optional<User> foundDeleter = userService.findUserById(createdDeleter.getId());
		
		assertTrue( foundDeleter.isPresent(), "testDeleteUserWithNotExistsUserIDShouldThrowResourceNotFoundException(): User (" + deleter + ") not stored");

		// create country
		String country_name = "Germany";
		String country_code = "de";
		Binary country_flag = null;
		
		Country country = new Country(
				country_name,					 
				country_code, 					
				country_flag 					
		);

		Country createdCountry = countryService.createCountry(deleter, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testDeleteUserWithNotExistsUserIDShouldThrowResourceNotFoundException(): Country (" + country + ") not stored");

		// test
		long oldIdx = createdCountry.getId();
		createdCountry.setId(-1);
		
		assertThrows(ResourceNotFoundException.class, () -> countryService.deleteCountry(createdDeleter, createdCountry.getId()), "testDeleteUserWithNotExistsUserIDShouldThrowResourceNotFoundException(): A NotDefinedException was not thrown when country is not defined");

		// clean up
		countryService.removeCountry(oldIdx);
		userService.removeUser(foundDeleter.get().getId());
	}

	/**
	 * test valid undelete country
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testValidUndeleteCountry() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create user
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User deleter = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdDeleter = userService.createUser(null, deleter);
		Optional<User> foundUser = userService.findUserById(createdDeleter.getId());
		
		assertTrue( foundUser.isPresent(), "testValidUndeleteCountry(): User (" + createdDeleter + ") not stored");

		// create country
		String country_name = "Germany";
		String country_code = "de";
		Binary country_flag = null;
		
		Country country = new Country(
				country_name,					 
				country_code, 					
				country_flag 					
		);

		Country createdCountry = countryService.createCountry(deleter, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testValidUndeleteCountry(): Country (" + country + ") not stored");
		
		// test
		countryService.deleteCountry(createdDeleter, createdCountry.getId());
		countryService.undeleteCountry(createdCountry.getId());
		
		Optional<Country> foundUndeletedCountry = countryService.findCountryById(createdCountry.getId());
		assertTrue( foundUndeletedCountry.isPresent(), "testValidUndeleteCountry(): Undelete Country (" + foundUndeletedCountry + ") not stored");

		assertEquals(foundCountry.get().getCreatedBy(), foundUndeletedCountry.get().getCreatedBy(), "testValidUndeleteCountry(): Deleted Country (" + foundUndeletedCountry.get() + "): creator was changed");
		assertEquals(foundCountry.get().getCreatedAt(), foundUndeletedCountry.get().getCreatedAt(), "testValidUndeleteCountry(): Deleted Country (" + foundUndeletedCountry.get() + "): creator date was changed");
		assertEquals(foundCountry.get().getUpdatedBy(), foundUndeletedCountry.get().getUpdatedBy(), "testValidUndeleteCountry(): Deleted Country (" + foundUndeletedCountry.get() + "): updater was changed");
		assertEquals(foundCountry.get().getUpdatedAt(), foundUndeletedCountry.get().getUpdatedAt(), "testValidUndeleteCountry(): Deleted Country (" + foundUndeletedCountry.get() + "): updater date was changed");
		assertNull(foundUndeletedCountry.get().getDeletedBy(), "testValidUndeleteCountry(): Deleted Country (" + foundUndeletedCountry.get() + "): deleter was not set to null");
		assertNull(foundUndeletedCountry.get().getDeletedAt(), "testValidUndeleteCountry(): Deleted Country (" + foundUndeletedCountry.get() + "): deleter date was not set to null");
		assertEquals(country_name, foundUndeletedCountry.get().getName(), "testValidUndeleteCountry(): Deleted Country (" + foundUndeletedCountry.get() + "): name was changed");
		assertEquals(country_code, foundUndeletedCountry.get().getCode(), "testValidUndeleteCountry(): Deleted Country (" + foundUndeletedCountry.get() + "): country code was changed");
		assertEquals(country_flag, foundUndeletedCountry.get().getFlag(), "testValidUndeleteCountry(): Deleted Country (" + foundUndeletedCountry.get() + "): flag was changed");

		// clean up
		countryService.removeCountry(foundCountry.get().getId());
		userService.removeUser(foundUser.get().getId());
	}

	/**
	 * test undelete country with invalid Index Should Throw ResourceNotFound
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testUndeleteCountryWithInvalidIndexShouldThrowResourceNotFound() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create deleter
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User deleter = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdDeleter = userService.createUser(null, deleter);
		Optional<User> foundUser = userService.findUserById(createdDeleter.getId());
		
		assertTrue( foundUser.isPresent(), "testUndeleteCountryWithInvalidIndexShouldThrowResourceNotFound(): User (" + createdDeleter + ") not stored");

		// create country
		String country_name = "Germany";
		String country_code = "de";
		Binary country_flag = null;
		
		Country country = new Country(
				country_name,					 
				country_code, 					
				country_flag 					
		);

		Country createdCountry = countryService.createCountry(deleter, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testUndeleteCountryWithInvalidIndexShouldThrowResourceNotFound(): Country (" + country + ") not stored");

		// test
		countryService.deleteCountry(deleter, foundCountry.get().getId());
		assertThrows(ResourceNotFoundException.class, () -> countryService.undeleteCountry(-1), "ResourceNotFoundException(): A ResourceNotFoundException was not thrown when id is not exists");
		
		// clean up
		countryService.removeCountry(foundCountry.get().getId());
		userService.removeUser(foundUser.get().getId());
	}

	/**
	 * test valid removed vounty
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testValidRemoveCountry() throws ResourceNotFoundException, NotDefinedException, DuplicateException {

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
		Optional<User> foundCreator = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundCreator.isPresent(), "testValidRemoveCountry(): User (" + createdCreator + ") not stored");

		// create country
		String country_name = "Germany";
		String country_code = "de";
		Binary country_flag = null;
		
		Country country = new Country(
				country_name,					 
				country_code, 					
				country_flag 					
		);

		Country createdCountry = countryService.createCountry(creator, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testValidRemoveCountry(): Country (" + country + ") not stored");
		
		// test
		countryService.removeCountry(foundCountry.get().getId());
		
		Optional<Country> foundRemovedCountry = countryService.findCountryById(foundCountry.get().getId());
		
		assertFalse( foundRemovedCountry.isPresent(), "testValidRemoveCountry(): Country was not removed");
		
		// clean up
		userService.removeUser(foundCreator.get().getId());		
	}

	/**
	 * test removed country with invalid index should throw ResourceNotFoundException
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testRemovedCountryWithInvalidIndexShouldThrowResourceNotFoundException() throws ResourceNotFoundException, NotDefinedException, DuplicateException {

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
		Optional<User> foundCreator = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundCreator.isPresent(), "testValidRemoveCountry(): User (" + createdCreator + ") not stored");

		// create country
		String country_name = "Germany";
		String country_code = "de";
		Binary country_flag = null;
		
		Country country = new Country(
				country_name,					 
				country_code, 					
				country_flag 					
		);

		Country createdCountry = countryService.createCountry(creator, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testValidRemoveCountry(): Country (" + country + ") not stored");
		
		// test
		assertThrows(ResourceNotFoundException.class, () -> countryService.removeCountry(-1), "testRemovedUserWithInvalidIndexShouldThrowResourceNotFoundException(): A ResourceNotFoundException was not thrown when id is not exists");
		
		// clean up
		countryService.removeCountry(foundCountry.get().getId());
		userService.removeUser(foundCreator.get().getId());
	}

	/**
	 * test findCountryById 
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testFindCountryById() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "EMail@email.com";
		String password = "password";
		
		User creator = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundCreator.isPresent(), "testFindCountryById(): Creator (" + createdCreator + ") not stored");
		
		// create country
		String name = "Germany";
		String country_code = "DE";
		
		Country country = new Country(
				name,
				country_code, 
				null);
		
		Country createdCountry = countryService.createCountry(creator, country);
		Optional<Country> foundCountry = countryRepository.findById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testFindCountryById(): Country (" + createdCountry + ") not stored");

		// test
		assertEquals(foundCountry.get().getId(), countryService.findCountryById(foundCountry.get().getId()).get().getId(), "testFindCountryById(): Country (" + createdCountry + ") not identical ID");

		// clean up
		countryService.removeCountry(createdCountry.getId());
		userService.removeUser(creator.getId());
	}
	
	/**
	 * test find country mpt by ID 
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testFindCountryNotById() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "EMail@email.com";
		String password = "password";
		
		User creator = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundCreator.isPresent(), "testFindCountryById(): Creator (" + createdCreator + ") not stored");
		
		// create country
		String name = "Germany";
		String country_code = "DE";
		
		Country country = new Country(
				name,
				country_code, 
				null);
		
		Country createdCountry = countryService.createCountry(creator, country);
		Optional<Country> foundCountry = countryRepository.findById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testFindCountryById(): Country (" + createdCountry + ") not stored");

		// test
		assertFalse( countryService.findCountryById(-1).isPresent(), "testFindCountryById(): Country (" + createdCountry + ") was found by ID");

		// clean up
		countryService.removeCountry(createdCountry.getId());
		userService.removeUser(creator.getId());
	}
	
	/**
	 * test findCountryByName 
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testFindCountryByName() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "EMail@email.com";
		String password = "password";
		
		User creator = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundCreator.isPresent(), "testFindCountryByName(): Creator (" + createdCreator + ") not stored");
		
		// create country
		String name = "Germany";
		String country_code = "DE";
		
		Country country = new Country(
				name,
				country_code, 
				null);
		
		Country createdCountry = countryService.createCountry(creator, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testFindCountryByName(): Creator (" + createdCountry + ") not stored");

		// test 1
		String name1 = "germany";
		List<Country> country1 = countryService.findCountryByName(name1);
		
		assertEquals(1, country1.size(), "testFindCountryByName(): Country (" + createdCountry + ") not found a country with name: " + name1 );
		
		// test 2
		String name2 = "Germany";
		List<Country> country2 = countryService.findCountryByName(name2);
		
		assertEquals(1, country2.size(), "testFindCountryByName(): Country (" + createdCountry + ") not found a country with name: " + name2 );

		// test 3
		String name3 = "GERMANY";
		List<Country> country3 = countryService.findCountryByName(name3);
		
		assertEquals(1, country3.size(), "testFindCountryByName(): Country (" + createdCountry + ") not found a country with name: " + name3 );

		// clean up
		countryService.removeCountry(createdCountry.getId());
		userService.removeUser(creator.getId());
	}

	/**
	 * test existsCountryByName 
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void existCountryByName() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "EMail@email.com";
		String password = "password";
		
		User creator = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundCreator.isPresent(), "existCountryByName(): Creator (" + createdCreator + ") not stored");
		
		// create country
		String name = "Germany";
		String country_code = "DE";
		
		Country country = new Country(
				name,
				country_code, 
				null);
		
		Country createdCountry = countryService.createCountry(creator, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "existCountryByName(): Creator (" + createdCountry + ") not stored");

		// test 1
		String name1 = "germany";
		assertTrue( countryService.existsCountryByName(name1), "existCountryByName(): Country \"" + name1 + "\" not exists" );
		
		// test 2
		String name2 = "Germany";
		assertTrue( countryService.existsCountryByName(name2), "existCountryByName(): Country \"" + name2 + "\" not exists" );

		// test 3
		String name3 = "GERMANY";
		assertTrue( countryService.existsCountryByName(name3), "existCountryByName(): Country \"" + name3 + "\" not exists" );

		// clean up
		countryService.removeCountry(createdCountry.getId());
		userService.removeUser(creator.getId());
	}

	/**
	 * test exists country by not defined 
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testFindCountryByNotDefinedName() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "EMail@email.com";
		String password = "password";
		
		User creator = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundCreator.isPresent(), "testFindCountryByNotDefinedName(): Creator (" + createdCreator + ") not stored");
		
		// create country
		String name = "Germany";
		String country_code = "DE";
		
		Country country = new Country(
				name,
				country_code, 
				null);
		
		Country createdCountry = countryService.createCountry(creator, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testFindCountryByNotDefinedName(): Creator (" + createdCountry + ") not stored");

		// test 1
		String name1 = "United Kingdom";
		assertFalse( countryService.existsCountryByName(name1), "testFindCountryByNotDefinedName(): Country " + name1 + " was found");
		
		// clean up
		countryService.removeCountry(createdCountry.getId());
		userService.removeUser(creator.getId());
	}

	/**
	 * test findCountryByCountryCode 
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testFindCountryByCode() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "EMail@email.com";
		String password = "password";
		
		User creator = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundCreator.isPresent(), "testFindCountryByCode(): Creator (" + createdCreator + ") not stored");
		
		// create country
		String name = "Germany";
		String country_code = "DE";
		
		Country country = new Country(
				name,
				country_code, 
				null);
		
		Country createdCountry = countryService.createCountry(creator, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testFindCountryByCode(): Creator (" + createdCountry + ") not stored");

		// test 1
		String code1 = "de";
		List<Country> country1 = countryService.findCountryByCode(code1);
		
		assertEquals(1, country1.size(), "testFindCountryByCode(): Country (" + createdCountry + ") not found a country with code: " + code1 );
		
		// test 2
		String code2 = "De";
		List<Country> country2 = countryService.findCountryByCode(code2);
		
		assertEquals(1, country2.size(), "testFindCountryByCode(): Country (" + createdCountry + ") not found a country with code: " + code2 );

		// test 3
		String code3 = "De";
		List<Country> country3 = countryService.findCountryByCode(code3);
		
		assertEquals(1, country3.size(), "testFindCountryByCode(): Country (" + createdCountry + ") not found a country with code: " + code3 );

		// clean up
		countryService.removeCountry(createdCountry.getId());
		userService.removeUser(creator.getId());
	}

	/**
	 * test find country by not defined country code 
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testFindCountryByNotDefinedCode() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "EMail@email.com";
		String password = "password";
		
		User creator = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundCreator.isPresent(), "testFindCountryByNotDefinedCode(): Creator (" + createdCreator + ") not stored");
		
		// create country
		String name = "Germany";
		String country_code = "DE";
		
		Country country = new Country(
				name,
				country_code, 
				null);
		
		Country createdCountry = countryService.createCountry(creator, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testFindCountryByNotDefinedCode(): Creator (" + createdCountry + ") not stored");

		// test 1
		String code1 = "United Kingdom";
		List<Country> country1 = countryService.findCountryByCode(code1);
		
		assertNotEquals(1, country1.size(), "testFindCountryByNotDefinedCode(): Country (" + createdCountry + ") was found with name: " + code1 );

		// clean up
		countryService.removeCountry(createdCountry.getId());
		userService.removeUser(creator.getId());
	}

	/**
	 * test existsCountryByCountryCode 
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */
	
	@Test
	void testExistsCountryByCode() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "EMail@email.com";
		String password = "password";
		
		User creator = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundCreator.isPresent(), "testExistsCountryByCode(): Creator (" + createdCreator + ") not stored");
		
		// create country
		String name = "Germany";
		String country_code = "DE";
		
		Country country = new Country(
				name,
				country_code, 
				null);
		
		Country createdCountry = countryService.createCountry(creator, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testExistsCountryByCode(): Creator (" + createdCountry + ") not stored");

		// test 1
		String code1 = "de";
		assertTrue(countryService.existsCountryByCode(code1), "testExistsCountryByCode(): Country Code (" + code1 + ") not found");
		
		// test 2
		String code2 = "De";
		assertTrue(countryService.existsCountryByCode(code2), "testExistsCountryByCode(): Country Code (" + code2 + ") not found");

		// test 3
		String code3 = "De";
		assertTrue(countryService.existsCountryByCode(code3), "testExistsCountryByCode(): Country Code (" + code3 + ") not found");

		// clean up
		countryService.removeCountry(createdCountry.getId());
		userService.removeUser(creator.getId());
	}

	/**
	 * test exists country by not defined country code 
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testExistsCountryByNotDefinedCode() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "EMail@email.com";
		String password = "password";
		
		User creator = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundCreator.isPresent(), "testExistsCountryByNotDefinedCode(): Creator (" + createdCreator + ") not stored");
		
		// create country
		String name = "Germany";
		String country_code = "DE";
		
		Country country = new Country(
				name,
				country_code, 
				null);
		
		Country createdCountry = countryService.createCountry(creator, country);
		Optional<Country> foundCountry = countryService.findCountryById(createdCountry.getId());
		
		assertTrue( foundCountry.isPresent(), "testExistsCountryByNotDefinedCountryCode(): Creator (" + createdCountry + ") not stored");

		// test 1
		String code1 = "United Kingdom";
		assertFalse(countryService.existsCountryByCode(code1), "testExistsCountryByNotDefinedCode(): Country Code (" + code1 + ") was found");

		// clean up
		countryService.removeCountry(createdCountry.getId());
		userService.removeUser(creator.getId());
	}
	
	/**
	 * test findCountryByNameOrCountryCode 
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
/*	
	@Test
	void testFindCountryByNameOrCode() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "EMail@email.com";
		String password = "password";
		
		User creator = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundCreator.isPresent(), "testFindCountryByNameOrCode(): Creator (" + createdCreator + ") not stored");
		
		// create country 1
		String country_name1 = "Germany";
		String country_code1 = "DE";
		
		Country createCountry1 = new Country(
				country_name1,
				country_code1, 
				null);
		
		Country createdCountry1 = countryService.createCountry(creator, createCountry1);
		Optional<Country> foundCreatedCountry1 = countryService.findCountryById(createdCountry1.getId());
		
		assertTrue( foundCreatedCountry1.isPresent(), "testFindCountryByNameOrCode(): Creator (" + createdCountry1 + ") not stored");

		// create country 2
		String country_name2 = "United Kingdom";
		String country_code2 = "UK";
		
		Country createCountry2 = new Country(
				country_name2,
				country_code2, 
				null);
		
		Country createdCountry2 = countryService.createCountry(creator, createCountry2);
		Optional<Country> foundCreatedCountry2 = countryService.findCountryById(createdCountry2.getId());
		
		assertTrue( foundCreatedCountry2.isPresent(), "testFindCountryByNameOrCode(): Creator (" + createdCountry1 + ") not stored");
		
		// test 1
		String findName1 = "germany";
		String findCountryCode1 = "de";
		List<Country> foundCountry1 = countryService.findCountryByNameOrCode(findName1, findCountryCode1);
		
		assertEquals(1, foundCountry1.size(), "testFindCountryByCode(): Country (" + createdCountry1 + ") no found a country with code: " + findCountryCode1 + " or name: " + findName1  );

		// test 2	
		String findName2 = "Germany";
		String findCountryCode2 = "de";
		List<Country> foundCountry2 = countryService.findCountryByNameOrCode(findName2, findCountryCode2);
		
		assertEquals(1, foundCountry2.size(), "testFindCountryByCode(): Country (" + createdCountry1 + ") no found a country with code: " + findCountryCode2 + " or name: " + findName2  );

		// test 3	
		String findName3 = "GERMANY";
		String findCountryCode3 = "de";
		List<Country> foundCountry3 = countryService.findCountryByNameOrCode(findName3, findCountryCode3);
		
		assertEquals(1, foundCountry3.size(), "testFindCountryByCode(): Country (" + createdCountry1 + ") no found a country with code: " + findCountryCode3 + " or name: " + findName3  );

		// test 4
		String findName4 = "germany";
		String findCountryCode4 = "De";
		List<Country> foundCountry4 = countryService.findCountryByNameOrCode(findName4, findCountryCode4);
		
		assertEquals(1, foundCountry4.size(), "testFindCountryByCode(): Country (" + createdCountry1 + ") no found a country with code: " + findCountryCode4 + " or name: " + findName4  );

		// test 5
		String findName5 = "germany";
		String findCountryCode5 = "DE";
		List<Country> foundCountry5 = countryService.findCountryByNameOrCountryCode(findName5, findCountryCode5);
		
		assertEquals(1, foundCountry5.size(), "testFindCountryByCountryCode(): Country (" + createdCountry1 + ") no found a country with code: " + findCountryCode5 + " or name: " + findName5  );

		// test 6
		String findName6 = "germany";
		String findCountryCode6 = "UK";
		List<Country> foundCountry6 = countryService.findCountryByNameOrCountryCode(findName6, findCountryCode6);
		
		assertEquals(2, foundCountry6.size(), "testFindCountryByCountryCode(): Country (" + createdCountry1 + ") no found a country with code: " + findCountryCode6 + " or name: " + findName6  );

		// test 7
		String findName7 = "france";
		String findCountryCode7 = "UK";
		List<Country> foundCountry7 = countryService.findCountryByNameOrCountryCode(findName7, findCountryCode7);
		
		assertEquals(1, foundCountry7.size(), "testFindCountryByCountryCode(): Country (" + createdCountry2 + ") no found a country with code: " + findCountryCode7 + " or name: " + findName7  );

		// test 8
		String findName8 = "germany";
		String findCountryCode8 = "fr";
		List<Country> foundCountry8 = countryService.findCountryByNameOrCountryCode(findName8, findCountryCode8);
		
		assertEquals(1, foundCountry8.size(), "testFindCountryByCountryCode(): Country (" + createdCountry1 + ") no found a country with code: " + findCountryCode8 + " or name: " + findName8  );
		
		// clean up
		countryService.removeCountry(createdCountry2.getId());
		countryService.removeCountry(createdCountry1.getId());
		
		userService.removeUser(creator.getId());
	}
*/
	/**
	 * test findCountryByNotDefinedNameOrCountryCode 
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */
/*	
	@Test
	void testexistsCountryByNotDefinedNameOrCountryCode() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "EMail@email.com";
		String password = "password";
		
		User creator = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundCreator.isPresent(), "testexistsCountryByNotDefinedNameOrCountryCode(): Creator (" + createdCreator + ") not stored");
		
		// create country1
		String name1 = "Germany";
		String country_code1 = "DE";
		
		Country country1 = new Country(
				name1,
				country_code1, 
				null);
		
		Country createdCountry1 = countryService.createCountry(creator, country1);
		Optional<Country> foundCountry1 = countryService.findCountryById(createdCountry1.getId());
		
		assertTrue( foundCountry1.isPresent(), "testexistsCountryByNotDefinedNameOrCountryCode(): Creator (" + createdCountry1 + ") not stored");

		// create country2
		String name2 = "United Kingdom";
		String country_code2 = "UK";
		
		Country country2 = new Country(
				name2,
				country_code2, 
				null);
		
		Country createdCountry2 = countryService.createCountry(creator, country2);
		Optional<Country> foundCountry2 = countryService.findCountryById(createdCountry2.getId());
		
		assertTrue( foundCountry2.isPresent(), "testexistsCountryByNotDefinedNameOrCountryCode(): Creator (" + createdCountry1 + ") not stored");
		
		// test 1
		String name3 = "france";
		String code3 = "fr";
		assertTrue(countryService.existsCountryByNameOrCountryCode(name3, code3), "testExistsCountryByCountryCode(): Country (" + createdCountry1 + ") no found a country with code: " + code3 + " or name: " + name3 );
		
		// clean up
		countryService.removeCountry(createdCountry2.getId());
		countryService.removeCountry(createdCountry1.getId());
		
		userService.removeUser(creator.getId());
	}
*/	
    /**
	 * test existsCountryByNameOrCountryCode 
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */
/*	
	@Test
	void testExistsCountryByNameOrCountryCode() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "EMail@email.com";
		String password = "password";
		
		User creator = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundCreator.isPresent(), "testExistsCountryByNameOrCountryCode(): Creator (" + createdCreator + ") not stored");
		
		// create country 1
		String country_name1 = "Germany";
		String country_code1 = "DE";
		
		Country createCountry1 = new Country(
				country_name1,
				country_code1, 
				null);
		
		Country createdCountry1 = countryService.createCountry(creator, createCountry1);
		Optional<Country> foundCreatedCountry1 = countryService.findCountryById(createdCountry1.getId());
		
		assertTrue( foundCreatedCountry1.isPresent(), "testExistsCountryByNameOrCountryCode(): Creator (" + createdCountry1 + ") not stored");

		// create country 2
		String country_name2 = "United Kingdom";
		String country_code2 = "UK";
		
		Country createCountry2 = new Country(
				country_name2,
				country_code2, 
				null);
		
		Country createdCountry2 = countryService.createCountry(creator, createCountry2);
		Optional<Country> foundCreatedCountry2 = countryService.findCountryById(createdCountry2.getId());
		
		assertTrue( foundCreatedCountry2.isPresent(), "testExistsCountryByNameOrCountryCode(): Creator (" + createdCountry1 + ") not stored");
		
		// test 1
		String findName1 = "germany";
		String findCountryCode1 = "de";
		assertTrue( countryService.existsCountryByNameOrCode(findName1, findCountryCode1), "testExistsCountryByNameOrCountryCode(): Country (" + createdCountry1 + ") not exists a country with code: " + findCountryCode1 + " or name: " + findName1 );

		// test 2	
		String findName2 = "Germany";
		String findCountryCode2 = "de";
		assertTrue( countryService.existsCountryByNameOrCode(findName2, findCountryCode2), "testExistsCountryByNameOrCountryCode(): Country (" + createdCountry2 + ") not exists a country with code: " + findCountryCode1 + " or name: " + findName2 );

		// test 3	
		String findName3 = "GERMANY";
		String findCountryCode3 = "de";
		assertTrue( countryService.existsCountryByNameOrCode(findName3, findCountryCode3), "testExistsCountryByNameOrCountryCode(): Country (" + createdCountry1 + ") not exists a country with code: " + findCountryCode3 + " or name: " + findName3 );

		// test 4
		String findName4 = "germany";
		String findCountryCode4 = "De";
		assertTrue( countryService.existsCountryByNameOrCode(findName4, findCountryCode4), "testExistsCountryByNameOrCountryCode(): Country (" + createdCountry1 + ") not exists a country with code: " + findCountryCode4 + " or name: " + findName4 );

		// test 5
		String findName5 = "germany";
		String findCountryCode5 = "DE";
		assertTrue( countryService.existsCountryByNameOrCode(findName5, findCountryCode5), "testExistsCountryByNameOrCountryCode(): Country (" + createdCountry1 + ") not exists a country with code: " + findCountryCode5 + " or name: " + findName5 );

		// test 6
		String findName6 = "germany";
		String findCountryCode6 = "UK";
		assertTrue( countryService.existsCountryByNameOrCode(findName6, findCountryCode6), "testExistsCountryByNameOrCountryCode(): Country (" + createdCountry1 + ") not exists a country with code: " + findCountryCode6 + " or name: " + findName6 );

		// test 7
		String findName7 = "france";
		String findCountryCode7 = "UK";
		assertTrue( countryService.existsCountryByNameOrCode(findName7, findCountryCode7), "testExistsCountryByNameOrCountryCode(): Country (" + createdCountry1 + ") not exists a country with code: " + findCountryCode7 + " or name: " + findName7 );

		// test 8
		String findName8 = "germany";
		String findCountryCode8 = "fr";
		assertTrue( countryService.existsCountryByNameOrCode(findName8, findCountryCode8), "testExistsCountryByNameOrCountryCode(): Country (" + createdCountry1 + ") not exists a country with code: " + findCountryCode8 + " or name: " + findName8 );
		
		// clean up
		countryService.removeCountry(createdCountry2.getId());
		countryService.removeCountry(createdCountry1.getId());
		
		userService.removeUser(creator.getId());
	}
*/
	/**
	 * test existsCountryByNotDefinedNameOrCountryCode 
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
/*	
	@Test
	void testExistsCountryByNotDefinedNameOrCode() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create creator
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "EMail@email.com";
		String password = "password";
		
		User creator = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundCreator.isPresent(), "testExistsCountryByNotDefinedNameOrCode(): Creator (" + createdCreator + ") not stored");
		
		// create country1
		String name1 = "Germany";
		String country_code1 = "DE";
		
		Country country1 = new Country(
				name1,
				country_code1, 
				null);
		
		Country createdCountry1 = countryService.createCountry(creator, country1);
		Optional<Country> foundCountry1 = countryService.findCountryById(createdCountry1.getId());
		
		assertTrue( foundCountry1.isPresent(), "testExistsCountryByNotDefinedNameOrCode(): Creator (" + createdCountry1 + ") not stored");

		// create country2
		String name2 = "United Kingdom";
		String country_code2 = "UK";
		
		Country country2 = new Country(
				name2,
				country_code2, 
				null);
		
		Country createdCountry2 = countryService.createCountry(creator, country2);
		Optional<Country> foundCountry2 = countryService.findCountryById(createdCountry2.getId());
		
		assertTrue( foundCountry2.isPresent(), "testExistsCountryByNotDefinedNameOrCode(): Creator (" + createdCountry1 + ") not stored");
		
		// test 1
		String name3 = "france";
		String code3 = "fr";
		assertFalse(countryService.existsCountryByNameOrCode(name3, code3), "testExistsCountryByNotDefinedNameOrCode(): Country (" + createdCountry1 + ") no found a country with code: " + code3 + " or name: " + name3 );
		
		// clean up
		countryService.removeCountry(createdCountry2.getId());
		countryService.removeCountry(createdCountry1.getId());
		
		userService.removeUser(creator.getId());
	}
*/
}
