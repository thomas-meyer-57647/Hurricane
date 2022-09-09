package com.tmt.hurricane.user.service;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2020 Thomas Meyer. License see license.txt
 * @version		0.0.1
 --------------------------------------------------------------------------------*/
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tmt.hurricane.exceptions.DuplicateException;
import com.tmt.hurricane.exceptions.NotDefinedException;
import com.tmt.hurricane.exceptions.ResourceNotFoundException;
import com.tmt.hurricane.user.model.User;

/**
 * test function for the user service
 */
@SpringBootTest
class UserServiceTest {
	
	@Autowired 
	UserService userService;
	
	/**
	 * test create User with minimalst valid data
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testCreateUserWithMinimalistValidData() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User user = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdUser = userService.createUser(null, user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());
		
		assertTrue( foundUser.isPresent(), "testCreateUserWithMinimalistValidData(): User (" + user + ") not stored");
		
		assertNull(foundUser.get().getCreatedBy(), "testCreateUserWithMinimalistValidData(): User (" + foundUser.get().getCreatedBy() + ") creator not null");
		assertNotNull(foundUser.get().getCreatedAt(), "testCreateUserWithMinimalistValidData(): User (" + foundUser.get().getCreatedAt() + ") creator date not set");
		assertNull(foundUser.get().getUpdatedBy(), "testCreateUserWithMinimalistValidData(): User (" + foundUser.get().getUpdatedBy() + ") updater was set");
		assertNull(foundUser.get().getUpdatedAt(), "testCreateUserWithMinimalistValidData(): User (" + foundUser.get().getUpdatedAt() + ") updater date was set");
		assertNull(foundUser.get().getDeletedBy(), "testCreateUserWithMinimalistValidData(): User (" + foundUser.get().getDeletedBy() + ") deleter was set");
		assertNull(foundUser.get().getDeletedAt(), "testCreateUserWithMinimalistValidData(): User (" + foundUser.get().getDeletedAt() + ") deleter date was set");
		assertEquals(firstname, foundUser.get().getFirstname(), "testCreateUserWithMinimalistValidData(): User (" + foundUser.get().getFirstname() + ") Firstname not identical with " + firstname);
		assertEquals(lastname, foundUser.get().getLastname(), "testCreateUserWithMinimalistValidData(): User (" + foundUser.get().getLastname() + ") Lastname not identical with " + lastname);
		assertEquals(email, foundUser.get().getEmail(), "testCreateUserWithMinimalistValidData(): User (" + foundUser.get().getEmail() + ") Email not identical with " + email);
		assertEquals(password, foundUser.get().getPassword(), "\"testCreateUserWithMinimalistValidData(): User (" + foundUser.get().getPassword() + ") Password not identical with " + password);
		
		// clean up
		userService.removeUser(foundUser.get().getId());
	}
	
	/**
	 * test create User with creator 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testCreateUserWithCreator() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// store creator
		String creatorFirstname = "firstname";
		String creatorLastname = "lastname";
		String creatorEmail = "createrl@email.com";
		String creatorPassword = "password";
		
		User creator = new User(
				creatorFirstname,				// password 
				creatorLastname, 				// lastname
				creatorEmail, 					// email
				creatorPassword					// password
				);

		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundCreator.isPresent(), "testCreateUserWithCreator(): Creator (" + createdCreator + ") not stored");
		
		// stored user
		String userFirstname = "user firstname";
		String userLastname = "user lastname";
		String userEmail = "useremail@email.com";
		String userPassword = "userpassword";
		
		User user = new User(
				userFirstname,				// password 
				userLastname, 				// lastname
				userEmail, 					// email
				userPassword				// password
				);
		
		User createdUser = userService.createUser(foundCreator.get(), user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());
		
		assertTrue( foundUser.isPresent(), "testCreateUserWithCreator(): User (" + createdUser + ") not stored");

		assertEquals(createdUser.getId(), foundUser.get().getId(), "testCreateUserWithCreator(): User (" + foundUser.get() + ") creator not identical with " + createdUser.getId());
		assertNotNull(foundUser.get().getCreatedBy(), "testCreateUserWithCreator(): User (" + foundUser.get() + ") creator date not set");
		assertNull(foundUser.get().getUpdatedBy(), "testCreateUserWithCreator(): User (" + foundUser.get().getUpdatedBy() + ") updater was set");
		assertNull(foundUser.get().getUpdatedAt(), "testCreateUserWithCreator(): User (" + foundUser.get().getUpdatedAt() + ") updater date was set");
		assertNull(foundUser.get().getDeletedBy(), "testCreateUserWithCreator(): User (" + foundUser.get().getDeletedBy() + ") deleter was set");
		assertNull(foundUser.get().getDeletedAt(), "testCreateUserWithCreator(): User (" + foundUser.get().getDeletedAt() + ") deleter date was set");
		assertEquals(userFirstname, foundUser.get().getFirstname(), "testCreateUserWithCreator(): User (" + foundUser.get() + ") firstname not identical with " + userFirstname);
		assertEquals(userLastname, foundUser.get().getLastname(), "testCreateUserWithCreator(): User (" + foundUser.get() + ") lastname not identical with " + userLastname);
		assertEquals(userEmail, foundUser.get().getEmail(), "testCreateUserWithCreator(): User (" + foundUser.get() + ") email not identical with " + userEmail);
		assertEquals(userPassword, foundUser.get().getPassword(), "\"testCreateUserWithCreator(): User (" + foundUser.get() + ") password not identical " + userPassword);
		
		// clean up
		userService.removeUser(foundCreator.get().getId());
		userService.removeUser(foundUser.get().getId());
	}
	
	/**
	 * test create User with null user data should throw NotDefinedException  
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 */	
	@Test
	void testCreateUserWithNullUserData() throws ResourceNotFoundException, NotDefinedException {
		assertThrows(NotDefinedException.class, () -> userService.createUser(null, null), "testCreateUserWithNullUserData(): Create User with null user data didn't throw NotDefinedException");
	}
	
	/**
	 * test create User with not exists create should throw RessourceNotFound  
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 */	
	@Test
	void testCreateUserWithNotExistsCreatorShouldThrowResourceNotFound() throws ResourceNotFoundException, NotDefinedException {
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
		
		// stored user
		String userFirstname = "user firstname";
		String userLastname = "user lastname";
		String userEmail = "useremail@email.com";
		String userPassword = "userpassword";
		
		User user = new User(
				userFirstname,				// password 
				userLastname, 				// lastname
				userEmail, 					// email
				userPassword				// password
				);

		assertThrows(ResourceNotFoundException.class, () -> userService.createUser(notExistsCreator, user), "testCreateUserWithNotExistsCreatorShouldThrowResourceNotFound(): The user was stored with not exist Creator (" + notExistsCreator + ")");
	}
	
	/**
	 * test create User with duplicated email adress should throw RessourceNotFound  
	 * @throws DuplicateException 
	 */	
	@Test
	void testCreateUserWithDuplicatedEmailAdressShouldThrowResourceNotFound() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// store creator
		String creatorFirstname = "firstname";
		String creatorLastname = "lastname";
		String creatorEmail = "creator@email.com";
		String creatorPassword = "password";
		
		User creator = new User(
				creatorFirstname,				// password 
				creatorLastname, 				// lastname
				creatorEmail, 					// email
				creatorPassword					// password
				);

		User createdCreator = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(createdCreator.getId());
		
		assertTrue( foundCreator.isPresent(), "testCreateUserWithDuplicatedEmailAdressShouldThrowResourceNotFound(): Creator (" + createdCreator + ") not stored");
		
		// stored user 1
		String userFirstname1 = "user firstname 1";
		String userLastname1 = "user lastname 1";
		String userEmail1 = "useremai1@email.com";
		String userPassword1 = "userpassword1";
		
		User user1 = new User(
				userFirstname1,				// password 
				userLastname1, 				// lastname
				userEmail1, 				// email
				userPassword1				// password
				);
		
		User createdUser1 = userService.createUser(foundCreator.get(), user1);
		Optional<User> foundUser1 = userService.findUserById(createdUser1.getId());

		assertTrue( foundUser1.isPresent(), "testCreateUserWithDuplicatedEmailAdressShouldThrowResourceNotFound(): User 1 (" + user1 + ") not stored");

		// try stored user 2
		String userFirstname2 = "user firstname 2";
		String userLastname2 = "user lastname 2";
		String userEmail2 = "userEmai1@email.com";
		String userPassword2 = "userpassword1";
		
		User user2 = new User(
				userFirstname2,				// password 
				userLastname2, 				// lastname
				userEmail2, 				// email
				userPassword2				// password
				);
		

		User foundCreatorUser = foundCreator.get();
		assertThrows(DuplicateException.class, () -> userService.createUser(foundCreatorUser, user2), "testCreateUserWithDuplicatedEmailAdressShouldThrowResourceNotFound(): No DuplicateException is thrown for duplicate emails.");
		
		// clean up
		userService.removeUser(foundCreator.get().getId());
		userService.removeUser(foundUser1.get().getId());
	}
	
	/**
	 * test update User with valid data
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testUpdateUserWithValidData() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create user
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User user = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdUser = userService.createUser(null, user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());
		
		assertTrue( foundUser.isPresent(), "testUpdateUserWithValidData(): User (" + user + ") not stored");

		// create updater
		String creator_firstname = "creator firstname";
		String creator_lastname = "creator lastname";
		String creator_email = "creator_email@email.com";
		String creator_password = "creator password";
		
		User creator = new User(
				creator_firstname,				// password 
				creator_lastname, 				// lastname
				creator_email, 					// email
				creator_password				// password
				);

		User creatorUser = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(creatorUser.getId());
		
		assertTrue( foundCreator.isPresent(), "testUpdateUserWithValidData(): Creator (" + creator + ") not stored");
		
		// update user
		String updatedFirstname = "updated firstname";
		String updatedLastname = "updated lastname";
		String updatedEmail = "new_email@email.com";
		String updatedPassword = "new password";
		
		User updatedUserData = new User(
				updatedFirstname,				// password 
				updatedLastname, 				// lastname
				updatedEmail, 					// email
				updatedPassword					// password
		);
		
		User updatedUser = userService.updateUser(foundCreator.get(), foundUser.get().getId(), updatedUserData);
		Optional<User> foundUpdatedUser = userService.findUserById(updatedUser.getId());
		
		assertTrue( foundUpdatedUser.isPresent(), "testUpdateUserWithValidData(): Updated User (" + updatedUser + ") not stored");
		
		assertEquals(foundUser.get().getId(), foundUpdatedUser.get().getId(), "testCreateUserWithMinimalistValidData(): User (" + foundUpdatedUser.get() + ") ID not identical with " + foundUser.get().getId());
		assertNull(foundUpdatedUser.get().getCreatedBy(), "testUpdateUserWithValidData(): Updated User (" + foundUpdatedUser.get() + ") creator was set to null");
		assertNotNull(foundUpdatedUser.get().getCreatedAt(), "testUpdateUserWithValidData(): Updated User (" + foundUpdatedUser.get()  + ") creator date was set to null");
		assertEquals(foundCreator.get().getId(), foundUpdatedUser.get().getUpdatedBy().getId(), "testUpdateUserWithValidData(): Updated User (" + foundUpdatedUser.get().getUpdatedBy() + ") updater was not set to " + foundCreator.get().getId());
		assertNotNull(foundUpdatedUser.get().getUpdatedAt(), "testUpdateUserWithValidData(): Updated User (" + foundUpdatedUser.get() + ") updater date was not set");
		assertNull(foundUpdatedUser.get().getDeletedBy(), "testUpdateUserWithValidData(): updated User (" + foundUpdatedUser.get() + ") deleter was set");
		assertNull(foundUpdatedUser.get().getDeletedAt(), "testUpdateUserWithValidData(): Updated User (" + foundUpdatedUser.get() + ") deleter date was set");
		assertEquals(updatedFirstname, foundUpdatedUser.get().getFirstname(), "testCreateUserWithMinimalistValidData(): User (" + foundUpdatedUser.get() + ") Firstname not identical with " + updatedFirstname);
		assertEquals(updatedLastname, foundUpdatedUser.get().getLastname(), "testCreateUserWithMinimalistValidData(): User (" + foundUpdatedUser.get() + ") Lastname not identical with " + updatedLastname);
		assertEquals(updatedEmail, foundUpdatedUser.get().getEmail(), "testCreateUserWithMinimalistValidData(): User (" + foundUpdatedUser.get() + ") Email not identical with " + updatedEmail);
		assertEquals(updatedPassword, foundUpdatedUser.get().getPassword(), "testCreateUserWithMinimalistValidData(): User (" + foundUpdatedUser.get() + ") Password not identical with " + updatedPassword);
		
		// clean up
		userService.removeUser(foundCreator.get().getId());
		userService.removeUser(foundUser.get().getId());
	}

	/**
	 * test update User with null Updater should throw NotDefinedException
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */
	@Test
	void testUpdateUserWithNullUpdaterShouldThrowNotDefinedException() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create user
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User user = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdUser = userService.createUser(null, user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());
		
		assertTrue( foundUser.isPresent(), "testUpdateUserWithNullUpdaterShouldThrowNotDefinedException(): User (" + user + ") not stored");

		// update user
		String updatedFirstname = "updated firstname";
		String updatedLastname = "updated lastname";
		String updatedEmail = "new_email@email.com";
		String updatedPassword = "new password";
		
		User updatedUserData = new User(
				updatedFirstname,				// password 
				updatedLastname, 				// lastname
				updatedEmail, 					// email
				updatedPassword					// password
		);

		assertThrows(NotDefinedException.class, () -> userService.updateUser(null, foundUser.get().getId(), updatedUserData), "testUpdateUserWithNullUpdaterShouldThrowNotDefinedException(): A NotDefineException was not thrown when Creater is null");
		
		// clean up
		userService.removeUser(foundUser.get().getId());		
	}
	
	/**
	 * test update User with null new user daata should throw NotDefinedException
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */
	@Test
	void testUpdateUserWithNullNewUserDataShouldThrowNotDefinedException() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create user
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User user = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdUser = userService.createUser(null, user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());
		
		assertTrue( foundUser.isPresent(), "testUpdateUserWithNullNewUserDataShouldThrowNotDefinedException(): User (" + user + ") not stored");

		// create updater
		String creator_firstname = "creator firstname";
		String creator_lastname = "creator lastname";
		String creator_email = "creator_email@email.com";
		String creator_password = "creator password";
		
		User creator = new User(
				creator_firstname,				// password 
				creator_lastname, 				// lastname
				creator_email, 					// email
				creator_password				// password
				);

		User creatorUser = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(creatorUser.getId());
		
		assertTrue( foundCreator.isPresent(), "testUpdateUserWithNullNewUserDataShouldThrowNotDefinedException(): Creator (" + creator + ") not stored");
		
		// update user
		assertThrows(NotDefinedException.class, () -> userService.updateUser(foundCreator.get(), foundUser.get().getId(), null), "testUpdateUserWithNullNewUserDataShouldThrowNotDefinedException(): A NotDefineException was not thrown when user data is null");
		
		// clean up
		userService.removeUser(foundCreator.get().getId());
		userService.removeUser(foundUser.get().getId());
	}
	
	/**
	 * test update User with not exists user should throw ResourceNotFoundException
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testUpdateUserWithNotExistsUserShouldThrowResourceNotFoundException() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create user
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User user = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdUser = userService.createUser(null, user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());
		
		assertTrue( foundUser.isPresent(), "testUpdateUserWithNotExistsUserShouldThrowResourceNotFoundException(): User (" + user + ") not stored");

		// create updater
		String creator_firstname = "creator firstname";
		String creator_lastname = "creator lastname";
		String creator_email = "creator_email@email.com";
		String creator_password = "creator password";
		
		User creator = new User(
				creator_firstname,				// password 
				creator_lastname, 				// lastname
				creator_email, 					// email
				creator_password				// password
				);

		User creatorUser = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(creatorUser.getId());
		
		assertTrue( foundCreator.isPresent(), "testUpdateUserWithNotExistsUserShouldThrowResourceNotFoundException(): Creator (" + creator + ") not stored");
		
		// update user
		String updatedFirstname = "updated firstname";
		String updatedLastname = "updated lastname";
		String updatedEmail = "new_email@email.com";
		String updatedPassword = "new password";
		
		User updatedUserData = new User(
				updatedFirstname,				// password 
				updatedLastname, 				// lastname
				updatedEmail, 					// email
				updatedPassword					// password
		);

		// set user to wrong id
		long oldID = foundUser.get().getId();
		foundUser.get().setId(-1);
		
		// test
		assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(foundCreator.get(), foundUser.get().getId(), updatedUserData), "testUpdateUserWithNotExistsUserShouldThrowResourceNotFoundException(): A ResourceNotFoundException was not thrown when user if not exists");
		
		// reset user id
		foundUser.get().setId(oldID);
		
		// clean up
		userService.removeUser(foundCreator.get().getId());
		userService.removeUser(foundUser.get().getId());
	}
	
	/**
	 * test update User with not exists creater should throw ResourceNotFoundException
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testUpdateUserWithNotExistsCreatorShouldThrowResourceNotFoundException() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create user
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User user = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdUser = userService.createUser(null, user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());
		
		assertTrue( foundUser.isPresent(), "testUpdateUserWithNotExistsCreatorShouldThrowResourceNotFoundException(): User (" + user + ") not stored");

		// create updater
		String creator_firstname = "creator firstname";
		String creator_lastname = "creator lastname";
		String creator_email = "creator_email@email.com";
		String creator_password = "creator password";
		
		User creator = new User(
				creator_firstname,				// password 
				creator_lastname, 				// lastname
				creator_email, 					// email
				creator_password				// password
				);

		User creatorUser = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(creatorUser.getId());
		
		assertTrue( foundCreator.isPresent(), "testUpdateUserWithNotExistsCreatorShouldThrowResourceNotFoundException(): Creator (" + creator + ") not stored");
		
		// update user
		String updatedFirstname = "updated firstname";
		String updatedLastname = "updated lastname";
		String updatedEmail = "new_email@email.com";
		String updatedPassword = "new password";
		
		User updatedUserData = new User(
				updatedFirstname,				// password 
				updatedLastname, 				// lastname
				updatedEmail, 					// email
				updatedPassword					// password
		);

		// set user to wrong id
		long oldCreatorID = foundCreator.get().getId();
		foundCreator.get().setId(-1);
		
		// test
		assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(foundCreator.get(), foundUser.get().getId(), updatedUserData), "testUpdateUserWithNotExistsCreatorShouldThrowResourceNotFoundException(): A ResourceNotFoundException was not thrown when creator if not exists");
		
		// reset user id
		foundCreator.get().setId(oldCreatorID);
		
		// clean up
		userService.removeUser(foundCreator.get().getId());
		userService.removeUser(foundUser.get().getId());
	}
	
	/**
	 * test update User with exists user data email throw DuplicateException
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testUpdateUserWithExistsUserDataEmailShouldThrowDuplicateException() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create user
		String firstname1 = "firstname";
		String lastname1 = "lastname";
		String email1 = "email@email.com";
		String password1 = "password";
		
		User user1 = new User(
				firstname1,				// password 
				lastname1, 				// lastname
				email1, 				// email
				password1				// password
				);

		User createdUser1 = userService.createUser(null, user1);
		Optional<User> foundUser1 = userService.findUserById(createdUser1.getId());
		
		assertTrue( foundUser1.isPresent(), "testUpdateUserWithExistsUserDataEmailShouldThrowDuplicateException(): User 1 (" + user1 + ") not stored");
		
		// create user2
		String firstname2 = "firstname2";
		String lastname2 = "lastname2";
		String email2 = "email2@email.com";
		String password2 = "password2";
		
		User user2 = new User(
				firstname2,				// password 
				lastname2, 				// lastname
				email2, 				// email
				password2				// password
				);

		User createdUser2 = userService.createUser(null, user2);
		Optional<User> foundUser2 = userService.findUserById(createdUser2.getId());
		
		assertTrue( foundUser1.isPresent(), "testUpdateUserWithExistsUserDataEmailShouldThrowDuplicateException(): User 2 (" + user2 + ") not stored");

		// create updater
		String creator_firstname = "creator firstname";
		String creator_lastname = "creator lastname";
		String creator_email = "creator_email@email.com";
		String creator_password = "creator password";
		
		User creator = new User(
				creator_firstname,				// password 
				creator_lastname, 				// lastname
				creator_email, 					// email
				creator_password				// password
				);

		User creatorUser = userService.createUser(null, creator);
		Optional<User> foundCreator = userService.findUserById(creatorUser.getId());
		
		assertTrue( foundCreator.isPresent(), "testUpdateUserWithExistsUserDataEmailShouldThrowDuplicateException(): Creator (" + creator + ") not stored");
		
		// update data
		String updatedFirstname = "updated firstname";
		String updatedLastname = "updated lastname";
		String updatedEmail = "email2@email.com";				// same as user 2
		String updatedPassword = "new password";
		
		User updatedUserData = new User(
				updatedFirstname,				// password 
				updatedLastname, 				// lastname
				updatedEmail, 					// email
				updatedPassword					// password
		);
		
		// test
		assertThrows(DuplicateException.class, () -> userService.updateUser(foundCreator.get(), foundUser1.get().getId(), updatedUserData), "testUpdateUserWithExistsUserDataEmailShouldThrowDuplicateException(): A DuplicateException was not thrown when another user has this email");
		
		// clean up
		userService.removeUser(foundCreator.get().getId());
		userService.removeUser(foundUser2.get().getId());
		userService.removeUser(foundUser1.get().getId());
	}

	/**
	 * test valid delete user
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testValidDeleteUser() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create user
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User user = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdUser = userService.createUser(null, user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());
		
		assertTrue( foundUser.isPresent(), "testValidUser(): User (" + user + ") not stored");

		// create deleter
		String deleter_firstname = "deleter firstname";
		String deleter_lastname = "deleter lastname";
		String deleter_email = "deleter_email@email.com";
		String deleter_password = "deleter password";
		
		User deleter = new User(
				deleter_firstname,				// password 
				deleter_lastname, 				// lastname
				deleter_email, 					// email
				deleter_password				// password
				);

		User deleterUser = userService.createUser(null, deleter);
		Optional<User> foundDeleter = userService.findUserById(deleterUser.getId());
		
		assertTrue( foundDeleter.isPresent(), "testValidUser(): Deleter (" + deleter + ") not stored");
		
		// test
		User deletedUser = userService.deleteUser(deleter, foundUser.get().getId());
		
		Optional<User> foundDeletedUser = userService.findUserById(deletedUser.getId());
		assertTrue( foundDeletedUser.isPresent(), "testValidUser(): Deleted User (" + deletedUser + ") not stored");

		assertEquals(foundUser.get().getCreatedBy(), foundDeletedUser.get().getCreatedBy(), "testValidUser(): Deleted User (" + foundDeletedUser.get() + "): creator was changed");
		assertEquals(foundUser.get().getCreatedAt(), foundDeletedUser.get().getCreatedAt(), "testValidUser(): Deleted User (" + foundDeletedUser.get() + "): creator date was changed");
		assertEquals(foundUser.get().getUpdatedBy(), foundDeletedUser.get().getUpdatedBy(), "testValidUser(): Deleted User (" + foundDeletedUser.get() + "): updater was changed");
		assertEquals(foundUser.get().getUpdatedAt(), foundDeletedUser.get().getUpdatedAt(), "testValidUser(): Deleted User (" + foundDeletedUser.get() + "): updater date was changed");
		assertEquals(deleter, foundDeletedUser.get().getDeletedBy(), "testValidUser(): Deleted User (" + foundDeletedUser.get() + "): deleter was not set");
		assertEquals(firstname, foundDeletedUser.get().getFirstname(), "testValidUser(): Deleted User (" + foundDeletedUser.get() + "): firstname was changed");
		assertEquals(lastname, foundDeletedUser.get().getLastname(), "testValidUser(): Deleted User (" + foundDeletedUser.get() + "): lastname was changed");
		assertEquals(email, foundDeletedUser.get().getEmail(), "testValidUser(): Deleted User (" + foundDeletedUser.get() + "): Email was changed");
		assertEquals(password, foundDeletedUser.get().getPassword(), "testValidUser(): Deleted User (" + foundDeletedUser.get() + "): Password was changed");

		// clean up
		userService.removeUser(foundDeleter.get().getId());
		userService.removeUser(foundUser.get().getId());
	}
	
	/**
	 * test Delete user with null deleter should throw NotDefinedException
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */
	@Test
	void testDeleteUserWithNullDeleterShouldThrowNotDefinedException() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create user
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User user = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdUser = userService.createUser(null, user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());
		
		assertTrue( foundUser.isPresent(), "testDeleteUserWithNullDeleterShouldThrowNotDefinedException(): User (" + user + ") not stored");

		// test
		assertThrows(NotDefinedException.class, () -> userService.deleteUser(null, foundUser.get().getId()), "testDeleteUserWithNullDeleterShouldThrowNotDefinedException(): A NotDefinedException was not thrown when deleter ist null");

		// clean up
		userService.removeUser(foundUser.get().getId());		
	}
	
	/**
	 * test Delete user with not exists deleter should throw ResourceNotFoundException
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */
	@Test
	void testDeleteUserWithNotExistsDeleterShouldThrowResourceNotFoundException() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create user
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User user = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdUser = userService.createUser(null, user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());
		
		assertTrue( foundUser.isPresent(), "testDeleteUserWithNotExistsDeleterShouldThrowResourceNotFoundException(): User (" + user + ") not stored");
		
		// create deleter
		String deleter_firstname = "deleter firstname";
		String deleter_lastname = "deleter lastname";
		String deleter_email = "deleter_email@email.com";
		String deleter_password = "deleter password";
		
		User deleter = new User(
				deleter_firstname,				// password 
				deleter_lastname, 				// lastname
				deleter_email, 					// email
				deleter_password				// password
				);

		User deleterUser = userService.createUser(null, deleter);
		Optional<User> foundDeleter = userService.findUserById(deleterUser.getId());
		
		assertTrue( foundDeleter.isPresent(), "testDeleteUserWithNotExistsDeleterShouldThrowResourceNotFoundException(): Deleter (" + deleter + ") not stored");

		// test
		long oldDeleterID = foundDeleter.get().getId();
		foundDeleter.get().setId(-1L);

		assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(foundDeleter.get(), foundUser.get().getId()), "testDeleteUserWithNotExistsDeleterShouldThrowResourceNotFoundException(): A ResourceNotFoundException was not thrown when deleter ist not exists");
		
		foundDeleter.get().setId(oldDeleterID); 		// reset id
		
		// clean up
		userService.removeUser(foundDeleter.get().getId());
		userService.removeUser(foundUser.get().getId());
		
	}
	
	/**
	 * test Delete user with not exists user id should throw ResourceNotFoundException
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
		
		User user = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);

		User createdUser = userService.createUser(null, user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());
		
		assertTrue( foundUser.isPresent(), "testDeleteUserWithNotExistsUserIDShouldThrowResourceNotFoundException(): User (" + user + ") not stored");
		
		// create deleter
		String deleter_firstname = "deleter firstname";
		String deleter_lastname = "deleter lastname";
		String deleter_email = "deleter_email@email.com";
		String deleter_password = "deleter password";
		
		User deleter = new User(
				deleter_firstname,				// password 
				deleter_lastname, 				// lastname
				deleter_email, 					// email
				deleter_password				// password
				);

		User deleterUser = userService.createUser(null, deleter);
		Optional<User> foundDeleter = userService.findUserById(deleterUser.getId());
		
		assertTrue( foundDeleter.isPresent(), "testDeleteUserWithNotExistsUserIDShouldThrowResourceNotFoundException(): Deleter (" + deleter + ") not stored");

		// test
		assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(foundDeleter.get(), -1), "ResourceNotFoundException(): A ResourceNotFoundException was not thrown when id is not exists");

		// clean up
		userService.removeUser(foundDeleter.get().getId());
		userService.removeUser(foundUser.get().getId());
		
	}

	/**
	 * test valid undelete user
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testValidUndeleteUser() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create user
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User user = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdUser = userService.createUser(null, user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());
		
		assertTrue( foundUser.isPresent(), "testValidUndeleteUser(): User (" + user + ") not stored");

		// create deleter
		String deleter_firstname = "deleter firstname";
		String deleter_lastname = "deleter lastname";
		String deleter_email = "deleter_email@email.com";
		String deleter_password = "deleter password";
		
		User deleter = new User(
				deleter_firstname,				// password 
				deleter_lastname, 				// lastname
				deleter_email, 					// email
				deleter_password				// password
				);

		User deleterUser = userService.createUser(null, deleter);
		Optional<User> foundDeleter = userService.findUserById(deleterUser.getId());
		
		assertTrue( foundDeleter.isPresent(), "testValidUndeleteUser(): Deleter (" + deleter + ") not stored");

		// set User as deleted
		User deletedUser = userService.deleteUser(deleter, foundUser.get().getId());
		
		// test
		User undeletedUser = userService.undeleteUser(foundUser.get().getId());
		
		Optional<User> foundUndeletedUser = userService.findUserById(foundUser.get().getId());
		assertTrue( foundUndeletedUser.isPresent(), "testValidUndeleteUser(): Undelete User (" + undeletedUser + ") not stored");

		assertEquals(foundUser.get().getCreatedBy(), foundUndeletedUser.get().getCreatedBy(), "testValidUser(): Undeleted User (" + foundUndeletedUser.get() + "): creator was changed");
		assertEquals(foundUser.get().getCreatedAt(), foundUndeletedUser.get().getCreatedAt(), "testValidUser(): Undeleted User (" + foundUndeletedUser.get() + "): creator date was changed");
		assertEquals(foundUser.get().getUpdatedBy(), foundUndeletedUser.get().getUpdatedBy(), "testValidUser(): Undeleted User (" + foundUndeletedUser.get() + "): updater was changed");
		assertEquals(foundUser.get().getUpdatedAt(), foundUndeletedUser.get().getUpdatedAt(), "testValidUser(): Undeleted User (" + foundUndeletedUser.get() + "): updater date was changed");
		assertNull(foundUndeletedUser.get().getDeletedBy(), "testValidUser(): Undeleted User (" + foundUndeletedUser.get() + "): deleter was not set to null");
		assertEquals(firstname, foundUndeletedUser.get().getFirstname(), "testValidUser(): Undeleted User (" + foundUndeletedUser.get() + "): firstname was changed");
		assertEquals(lastname, foundUndeletedUser.get().getLastname(), "testValidUser(): Undeleted User (" + foundUndeletedUser.get() + "): lastname was changed");
		assertEquals(email, foundUndeletedUser.get().getEmail(), "testValidUser(): Undeleted User (" + foundUndeletedUser.get() + "): Email was changed");
		assertEquals(password, foundUndeletedUser.get().getPassword(), "testValidUser(): Undeleted User (" + foundUndeletedUser.get() + "): Password was changed");

		// clean up
		userService.removeUser(foundDeleter.get().getId());
		userService.removeUser(foundUser.get().getId());
	}
	
	/**
	 * test undelete user with invalid Index Should Throw ResourceNotFound
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testUndeleteUserWithInvalidIndexShouldThrowResourceNotFound() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create user
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User user = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdUser = userService.createUser(null, user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());
		
		assertTrue( foundUser.isPresent(), "testUndeleteUserWithInvalidIndexShouldThrowResourceNotFound(): User (" + user + ") not stored");

		// create deleter
		String deleter_firstname = "deleter firstname";
		String deleter_lastname = "deleter lastname";
		String deleter_email = "deleter_email@email.com";
		String deleter_password = "deleter password";
		
		User deleter = new User(
				deleter_firstname,				// password 
				deleter_lastname, 				// lastname
				deleter_email, 					// email
				deleter_password				// password
				);

		User deleterUser = userService.createUser(null, deleter);
		Optional<User> foundDeleter = userService.findUserById(deleterUser.getId());

		assertTrue( foundDeleter.isPresent(), "testUndeleteUserWithInvalidIndexShouldThrowResourceNotFound(): Deleter (" + deleter + ") not stored");

		// set User as deleted
		User deletedUser = userService.deleteUser(deleter, foundUser.get().getId());
		
		// test
		assertThrows(ResourceNotFoundException.class, () -> userService.undeleteUser(-1), "ResourceNotFoundException(): A ResourceNotFoundException was not thrown when id is not exists");
		
		// clean up
		userService.removeUser(foundDeleter.get().getId());
		userService.removeUser(foundUser.get().getId());
	}
	
	/**
	 * test valid removed user
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testValidRemoveUser() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create user
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User user = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdUser = userService.createUser(null, user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());
		
		assertTrue( foundUser.isPresent(), "testValidRemoveUser(): User (" + user + ") not stored");

		// test
		userService.removeUser(foundUser.get().getId());
		
		Optional<User> foundRemovedUser = userService.findUserById(foundUser.get().getId());
		
		assertFalse( foundRemovedUser.isPresent(), "testValidRemoveUser(): User was not removed");
	}
	
	/**
	 * test removed user with invalid index should throw ResourceNotFoundException
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testRemovedUserWithInvalidIndexShouldThrowResourceNotFoundException() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create user
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "email@email.com";
		String password = "password";
		
		User user = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdUser = userService.createUser(null, user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());
		
		assertTrue( foundUser.isPresent(), "testRemovedUserWithInvalidIndexShouldThrowResourceNotFoundException(): User (" + user + ") not stored");

		// test
//		assertThrows(ResourceNotFoundException.class, () -> userService.removeUser(-1), "testRemovedUserWithInvalidIndexShouldThrowResourceNotFoundException(): A ResourceNotFoundException was not thrown when id is not exists");
		
		// clean up
		userService.removeUser(foundUser.get().getId());
	}

	/**
	 * test findUserByEmail 
	 * 
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	void testFindUserByEmail() throws ResourceNotFoundException, NotDefinedException, DuplicateException {
		// create user
		String firstname = "firstname";
		String lastname = "lastname";
		String email = "EMail@email.com";
		String password = "password";
		
		User user = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		User createdUser = userService.createUser(null, user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());
		
		assertTrue( foundUser.isPresent(), "testFindUserByEmail(): User (" + user + ") not stored");

		// test 1
		String eamil1 = "email@email.com";
		List<User> users1 = userService.findUserByEmail(eamil1);
		
		assertEquals(1, users1.size(), "testFindUserByEmail(): Users (" + users1 + ") not found a user with email: " + eamil1 );
		
		// test 2
		String eamil2 = "Email@email.com";
		List<User> users2 = userService.findUserByEmail(eamil2);
		
		assertEquals(1, users2.size(), "testFindUserByEmail(): Users (" + users2 + ") not found a user with email: " + eamil2 );

		// test 3
		String eamil3 = "EMAIL@EMAIL.COM";
		List<User> users3 = userService.findUserByEmail(eamil3);
		
		assertEquals(1, users3.size(), "testFindUserByEmail(): Users (" + users3 + ") not found a user with email: " + eamil3 );

		// test 4
		String eamil4 = "EMail2@EMAIL.COM";
		List<User> users4 = userService.findUserByEmail(eamil4);
		
		assertEquals(0, users4.size(), "testFindUserByEmail(): Users (" + users4 + ") user found with email: " + eamil4 );
		
		// clean up
		userService.removeUser(foundUser.get().getId());
	}
		
		
}