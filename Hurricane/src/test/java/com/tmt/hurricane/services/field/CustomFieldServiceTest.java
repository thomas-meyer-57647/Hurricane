package com.tmt.hurricane.services.field;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tmt.hurricane.exceptions.DuplicateException;
import com.tmt.hurricane.exceptions.NotDefinedException;
import com.tmt.hurricane.exceptions.ResourceNotFoundException;
import com.tmt.hurricane.model.fields.CustomField;
import com.tmt.hurricane.model.fields.EFieldType;
import com.tmt.hurricane.model.fields.FieldDescription;
import com.tmt.hurricane.user.model.User;
import com.tmt.hurricane.user.service.UserService;


/**
 * test function for the custom field service
 */
@SpringBootTest
class CustomFieldServiceTest {

	@Autowired 
	CustomFieldService customFieldService;
	
	@Autowired 
	FieldDescriptionService fieldDescriptionService;

	@Autowired 
	UserService userService;
	
	/**
	 * test create custom field with minimalst custom field data
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 */	
	@Test
	public void testCreateCustomFieldWithMinimalistValidData() throws ResourceNotFoundException, NotDefinedException, DuplicateException {

		// create a basic user
		String userfirstname = "firstname";
		String userLastname = "Lastname";
		String userEmail = "email@email.com";
		String userPassword = "password";
		
		User user = new User(
				userfirstname,				// password 
				userLastname, 				// lastname
				userEmail, 					// email
				userPassword				// password
				);

		User createdUser = userService.createUser(null, user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());

		assertTrue(foundUser.isPresent(), "testCreateCustomFieldWithMinimalistValidData(): user not created");
		
		// create basic field description
		String fieldDescriptionName = "a field Fieldname";
		EFieldType fieldDescriptionFieldType = EFieldType.TEXT;
		String fieldDescriptionOptions = "options";
		
		FieldDescription fieldDescription = new FieldDescription(
				fieldDescriptionName, 
				fieldDescriptionFieldType, 
				fieldDescriptionOptions);

		FieldDescription createdFieldDescription = fieldDescriptionService.createFieldDescription(user, fieldDescription);
		Optional<FieldDescription> foundFieldDescription = fieldDescriptionService.findFieldDescriptionById(createdFieldDescription.getId());

		assertTrue(foundFieldDescription.isPresent(), "testCreateCustomFieldWithMinimalistValidData(): field description not created");
		
		// create basic custom field
		String customFieldName = "a custom field";
		String customFieldClassname = "a classname";

		CustomField customField = new CustomField(
				customFieldName, 
				customFieldClassname, 
				foundFieldDescription.get());
		
		CustomField createdCustomField = customFieldService.createCustomField(user, customField);
		Optional<CustomField> foundCustomField = customFieldService.findCustomFieldById(createdCustomField.getId());

		assertTrue(foundCustomField.isPresent(), "testCreateCustomFieldWithMinimalistValidData(): custom field not created");
		
		assertEquals(foundUser.get().getId(), foundCustomField.get().getCreatedBy(), "testCreateCustomFieldWithMinimalistValidData(): creator not set");
		assertNotNull(foundCustomField.get().getCreatedAt(), "testCreateCustomFieldWithMinimalistValidData(): create date not set");
		assertEquals(customFieldName, foundCustomField.get().getName(), "testCreateCustomFieldWithMinimalistValidData(): custom field name not identical");
		assertEquals(customFieldClassname, foundCustomField.get().getClassname(), "testCreateCustomFieldWithMinimalistValidData(): custom field classname not identical");
		assertEquals(foundFieldDescription.get().getId(), foundCustomField.get().getFieldDescription().getId(), "testCreateCustomFieldWithMinimalistValidData(): custom field classname not identical");
		
		// clean up
		customFieldService.removeCustomField(createdCustomField.getId());
		fieldDescriptionService.removeFieldDescription(fieldDescription.getId());
		userService.removeUser(user.getId());
	}
	
	/**
	 * test create custom field with null user
	 * @throws ResourceNotFoundException 
	 * @throws NotDefinedException 
	 */	
	/*
	@Test
	public void testCreateCustomFieldWithNullUserShouldThrowNotDefinedException() throws ResourceNotFoundException, NotDefinedException {
		
		// create a basic user
		String userfirstname = "firstname";
		String userLastname = "Lastname";
		String userEmail = "email@email.com";
		String userPassword = "password";
		
		User user = new User(
				userfirstname,				// password 
				userLastname, 				// lastname
				userEmail, 					// email
				userPassword				// password
				);

		User createdUser;
		try {
			createdUser = userService.createUser(null, user);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotDefinedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Optional<User> foundUser = userService.findUserById(createdUser.getId());

		assertTrue(foundUser.isPresent(), "testCreateCustomFieldWithNullUserShouldThrowNotDefinedException(): user not created");
		
		// create basic field description
		String fieldDescriptionName = "a field Fieldname";
		EFieldType fieldDescriptionFieldType = EFieldType.TEXT;
		String fieldDescriptionOptions = "options";
		
		FieldDescription fieldDescription = new FieldDescription(
				fieldDescriptionName, 
				fieldDescriptionFieldType, 
				fieldDescriptionOptions);

		FieldDescription createdFieldDescription = fieldDescriptionService.createFieldDescription(user, fieldDescription);
		Optional<FieldDescription> foundFieldDescription = fieldDescriptionService.findFieldDescriptionById(createdFieldDescription.getId());

		assertTrue(foundFieldDescription.isPresent(), "testCreateCustomFieldWithNullUserShouldThrowNotDefinedException(): field description not created");
		
		// create basic custom field
		String customFieldName = "a custom field";
		String customFieldClassname = "a classname";

		CustomField customField = new CustomField(
				customFieldName, 
				customFieldClassname, 
				foundFieldDescription.get());

		assertThrows(NotDefinedException.class, () -> customFieldService.createCustomField(null, customField), "testCreateCustomFieldWithNullUserShouldThrowNotDefinedException(): NotDefinedException was not thrown");
		
		// clean up
		fieldDescriptionService.removeFieldDescription(fieldDescription.getId());
		userService.removeUser(user.getId());
	}
    */	
													/**
													 * test create custom field with not exists user
													 * @throws ResourceNotFoundException 
													 * @throws NotDefinedException 
													 */	
	/*
													@Test
													public void testCreateCustomFieldWithNotExistsUser() throws ResourceNotFoundException, NotDefinedException {
												
														// create a basic user
														String userfirstname = "firstname";
														String userLastname = "Lastname";
														String userEmail = "email@email.com";
														String userPassword = "password";
														
														User user = new User(
																userfirstname,				// password 
																userLastname, 				// lastname
																userEmail, 					// email
																userPassword				// password
																);
												
														User createdUser;
														try {
															createdUser = userService.createUser(null, user);
														} catch (ResourceNotFoundException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														} catch (NotDefinedException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
														Optional<User> foundUser = userService.findUserById(createdUser.getId());
												
														assertTrue(foundUser.isPresent(), "testCreateCustomFieldWithNotExistsUser(): user not created");
																
														// create basic field description
														String fieldDescriptionName = "a field Fieldname";
														EFieldType fieldDescriptionFieldType = EFieldType.TEXT;
														String fieldDescriptionOptions = "options";
														
														FieldDescription fieldDescription = new FieldDescription(
																fieldDescriptionName, 
																fieldDescriptionFieldType, 
																fieldDescriptionOptions);
												
														FieldDescription createdFieldDescription = fieldDescriptionService.createFieldDescription(user, fieldDescription);
														Optional<FieldDescription> foundFieldDescription = fieldDescriptionService.findFieldDescriptionById(createdFieldDescription.getId());
												
														assertTrue(foundUser.isPresent(), "testCreateCustomFieldWithNotExistsUser(): field description not created");
														
														// create basic custom field
														String customFieldName = "a custom field";
														String customFieldClassname = "a classname";
												
														CustomField customField = new CustomField(
																customFieldName, 
																customFieldClassname, 
																foundFieldDescription.get());
														
														// test
														long userID = user.getId();	
														user.setId(-1);
												
														assertThrows(ResourceNotFoundException.class, () -> customFieldService.createCustomField(user, customField));
														
														user.setId(userID);
														
														// clean up
														fieldDescriptionService.removeFieldDescription(fieldDescription.getId());
														userService.removeUser(user.getId());
													}
*/
	/**
	 * test create custom field with null custom field
	 * @throws ResourceNotFoundException 
	 */
/*	
	@Test
	public void testCreateCustomFieldWithNullCustomFieldShouldThrowNotDefinedException() throws ResourceNotFoundException {
		
		// create a basic user
		String userfirstname = "firstname";
		String userLastname = "Lastname";
		String userEmail = "email@email.com";
		String userPassword = "password";
		
		User user = new User(
				userfirstname,				// password 
				userLastname, 				// lastname
				userEmail, 					// email
				userPassword				// password
				);

		User createdUser = userService.createUser(null, user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());

		assertTrue(foundUser.isPresent(), "testCreateCustomFieldWithNullCustomFieldShouldThrowNotDefinedException(): user not created");
		
		// create basic field description
		String fieldDescriptionName = "a field Fieldname";
		EFieldType fieldDescriptionFieldType = EFieldType.TEXT;
		String fieldDescriptionOptions = "options";
		
		FieldDescription fieldDescription = new FieldDescription(
				fieldDescriptionName, 
				fieldDescriptionFieldType, 
				fieldDescriptionOptions);

		FieldDescription createdFieldDescription = fieldDescriptionService.createFieldDescription(user, fieldDescription);
		Optional<FieldDescription> foundFieldDescription = fieldDescriptionService.findFieldDescriptionById(createdFieldDescription.getId());

		assertTrue(foundFieldDescription.isPresent(), "testCreateCustomFieldWithNullCustomFieldShouldThrowNotDefinedException(): field description not created");
		
		// create basic custom field
		assertThrows(NotDefinedException.class, () -> customFieldService.createCustomField(user, null), "testCreateCustomFieldWithNullCustomFieldShouldThrowNotDefinedException(): NotDefinedException was not thrown");
		
		// clean up
		fieldDescriptionService.removeFieldDescription(fieldDescription.getId());
		userService.removeUser(user.getId());
	}
	// Was passiert wenn ich eine Field Descirption definier, sie aber nicht existiert.
*/	
	/**
	 * test update custom field with minimalst field description data
	 * @throws ResourceNotFoundException 
	 */
/*	
	@Test
	public void testUpdateCustomFieldWithMinimalistValidData() throws ResourceNotFoundException {

		// create a basic user
		String userfirstname = "firstname";
		String userLastname = "Lastname";
		String userEmail = "email@email.com";
		String userPassword = "password";
		
		User user = new User(
				userfirstname,				// password 
				userLastname, 				// lastname
				userEmail, 					// email
				userPassword				// password
				);

		User createdUser = userService.createUser(null, user);
		Optional<User> foundUser = userService.findUserById(createdUser.getId());

		assertTrue(foundUser.isPresent(), "testUpdateCustomFieldWithMinimalistValidData(): user not found");
		
		// create first field description
		String firstFieldDescriptionName = "a field Fieldname";
		EFieldType firstFieldDescriptionFieldType = EFieldType.TEXT;
		String firstFieldDescriptionOptions = "options";
		
		FieldDescription firstFieldDescription = new FieldDescription(
				firstFieldDescriptionName, 
				firstFieldDescriptionFieldType, 
				firstFieldDescriptionOptions);

		FieldDescription createdFirstFieldDescription = fieldDescriptionService.createFieldDescription(user, firstFieldDescription);
		Optional<FieldDescription> foundFirstFieldDescription = fieldDescriptionService.findFieldDescriptionById(createdFirstFieldDescription.getId());

		assertTrue(foundFirstFieldDescription.isPresent(), "testUpdateCustomFieldWithMinimalistValidData(): first Field Description not found");
		
		// create secound field description
		String secoundFieldDescriptionName = "another field Fieldname";
		EFieldType secoundFieldDescriptionFieldType = EFieldType.NUMBER;
		String secoundFieldDescriptionOptions = "another options";
		
		FieldDescription secoundfieldDescription = new FieldDescription(
				secoundFieldDescriptionName, 
				secoundFieldDescriptionFieldType, 
				secoundFieldDescriptionOptions);

		FieldDescription createdSecoundFieldDescription = fieldDescriptionService.createFieldDescription(user, secoundfieldDescription);
		Optional<FieldDescription> foundSecoundFieldDescription = fieldDescriptionService.findFieldDescriptionById(createdSecoundFieldDescription.getId());

		assertTrue(foundSecoundFieldDescription.isPresent(), "testUpdateCustomFieldWithMinimalistValidData(): secound Field Description not found");
		
		// create basic custom field
		String customFieldName = "a custom field name";
		String customFieldClassname = "a classname";

		CustomField customField = new CustomField(
				customFieldName, 
				customFieldClassname, 
				foundFirstFieldDescription.get());
		
		CustomField createdCustomField = customFieldService.createCustomField(user, customField);
		Optional<CustomField> foundCustomField = customFieldService.findCustomFieldById(createdCustomField.getId());
		
		assertTrue(foundCustomField.isPresent(), "testUpdateCustomFieldWithMinimalistValidData(): custom field not found");
		
		// test update
		String newCustomFieldName = "new custom field name";
		String newCustomFieldClassname = "new custom field classname";
		
		CustomField updateCustomField = new CustomField(
				newCustomFieldName, 
				newCustomFieldClassname, 
				foundSecoundFieldDescription.get());
		
		customFieldService.updateCustomField(createdUser, foundCustomField.get().getId(), updateCustomField);
		Optional<CustomField> foundUpdatedCustomField = customFieldService.findCustomFieldById(createdCustomField.getId());

		assertTrue(foundUpdatedCustomField.isPresent(), "testUpdateCustomFieldWithMinimalistValidData(): updated custom field not found");
		
		assertEquals(foundCustomField.get().getId(), foundUpdatedCustomField.get().getId(), "testUpdateCustomFieldWithMinimalistValidData(): Id not identical");
		assertEquals(foundCustomField.get().getCreatedBy(), foundUpdatedCustomField.get().getCreatedBy(), "testUpdateCustomFieldWithMinimalistValidData(): Creator not identical");
		assertEquals(foundCustomField.get().getCreatedAt(), foundUpdatedCustomField.get().getCreatedAt(), "testUpdateCustomFieldWithMinimalistValidData(): Creation date not identical");
		assertNotNull(foundUpdatedCustomField.get().getUpdatedBy(), "testUpdateCustomFieldWithMinimalistValidData(): updater not set");
		assertNotNull(foundUpdatedCustomField.get().getUpdatedAt(), "testUpdateCustomFieldWithMinimalistValidData(): update date not set");
		assertNull(foundUpdatedCustomField.get().getDeletedBy(), "testUpdateCustomFieldWithMinimalistValidData(): deleter was setting");
		assertNull(foundUpdatedCustomField.get().getDeletedAt(), "testUpdateCustomFieldWithMinimalistValidData(): delete date was set");
		assertEquals(newCustomFieldName, foundUpdatedCustomField.get().getName(), "testUpdateCustomFieldWithMinimalistValidData(): Name was not updated");
		assertEquals(newCustomFieldClassname, foundUpdatedCustomField.get().getName(), "testUpdateCustomFieldWithMinimalistValidData(): Classname was not updated");
		assertEquals(foundSecoundFieldDescription.get().getId(), foundUpdatedCustomField.get().getFieldDescription().getId(), "testUpdateCustomFieldWithMinimalistValidData(): FieldDescription was not updated");
		
		// clean up
		customFieldService.removeCustomField(foundUpdatedCustomField.get().getId());
		fieldDescriptionService.removeFieldDescription(foundFirstFieldDescription.get().getId());
		fieldDescriptionService.removeFieldDescription(foundSecoundFieldDescription.get().getId());
		userService.removeUser(user.getId());
	}
*/	
	/**
	 * test update field description with null user should throw NullPointerException
	 * @throws ResourceNotFoundException 
	 */
/*	
	@Test
	public void testUpdateFieldDescriptionWithNullUserShouldThrowNullPointerException() throws ResourceNotFoundException {

		// create a basic user
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

		userService.createUser(null, user);

		// create basic field description
		String name = "aFieldname";
		EFieldType fieldType = EFieldType.TEXT;
		String options = "options";
		
		FieldDescription fieldDescription = new FieldDescription(name, fieldType, options);

		fieldDescriptionService.createFieldDescription(user, fieldDescription);

		// update field description
		fieldDescription.setFieldType(EFieldType.NUMBER);
		
		assertThrows(NullPointerException.class, () -> fieldDescriptionService.updateFieldDescription(null, fieldDescription.getId(), fieldDescription));
		
		// clean up
		fieldDescriptionService.removeFieldDescription(fieldDescription.getId());
		userService.removeUser(user.getId());
	}
*/
	/**
	 * test update field description with not exists user should trhrow ResourceNotFoundExceipton
	 * @throws ResourceNotFoundException 
	 */	
/*	
	@Test
	public void testUpdateFieldDescriptionWithNotExistsUserShouldThrowResourceNotFoundException() throws ResourceNotFoundException {
		
		// create a basic user
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

		userService.createUser(null, user);

		// create basic field description
		String name = "aFieldname";
		EFieldType fieldType = EFieldType.TEXT;
		String options = "options";
		
		FieldDescription fieldDescription = new FieldDescription(name, fieldType, options);

		fieldDescriptionService.createFieldDescription(user, fieldDescription);

		// update field description
		User notExistsUser = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		
		fieldDescription.setFieldType(EFieldType.NUMBER);
		
		assertThrows(ResourceNotFoundException.class, () -> fieldDescriptionService.updateFieldDescription(notExistsUser, fieldDescription.getId(), fieldDescription));
		
		// clean up
		fieldDescriptionService.removeFieldDescription(fieldDescription.getId());
		userService.removeUser(user.getId());
		
	}
*/	
	/**
	 * test update field description with not exists field description ID should trhrow ResourceNotFoundExceipton
	 * @throws ResourceNotFoundException 
	 */	
/*	
	@Test
	public void testUpdateFieldDescriptionWithNotExistsFieldDescriptionIDShouldThrowResourceNotFoundException() throws ResourceNotFoundException {
		
		// create a basic user
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

		userService.createUser(null, user);

		// create basic field description
		String name = "aFieldname";
		EFieldType fieldType = EFieldType.TEXT;
		String options = "options";
		
		FieldDescription fieldDescription = new FieldDescription(name, fieldType, options);

		fieldDescriptionService.createFieldDescription(user, fieldDescription);

		// update field description
		fieldDescription.setFieldType(EFieldType.NUMBER);
		
		assertThrows(ResourceNotFoundException.class, () -> fieldDescriptionService.updateFieldDescription(user, -1L, fieldDescription));
		
		// clean up
		fieldDescriptionService.removeFieldDescription(fieldDescription.getId());
		userService.removeUser(user.getId());
		
	}
*/
	
	/**
	 * test update field description with null field description ID should trhrow NullPointerException
	 * @throws ResourceNotFoundException 
	 */	
/*	
	@Test
	public void testUpdateFieldDescriptionWithNullFieldDescriptionIDShouldThrowNullPointerException() throws ResourceNotFoundException {
		
		// create a basic user
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

		userService.createUser(null, user);

		// create basic field description
		String name = "aFieldname";
		EFieldType fieldType = EFieldType.TEXT;
		String options = "options";
		
		FieldDescription fieldDescription = new FieldDescription(name, fieldType, options);

		fieldDescriptionService.createFieldDescription(user, fieldDescription);

		// update field description
		fieldDescription.setFieldType(EFieldType.NUMBER);
		
		assertThrows(NullPointerException.class, () -> fieldDescriptionService.updateFieldDescription(user, fieldDescription.getId(), null));
		
		// clean up
		fieldDescriptionService.removeFieldDescription(fieldDescription.getId());
		userService.removeUser(user.getId());
	}
*/
	/**
	 * test set deleted field description 
	 * @throws ResourceNotFoundException 
	 */	
/*	
	@Test
	public void testSetDeletedFieldDescription() throws ResourceNotFoundException {
		
		// create a basic user
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

		// create basic field description
		String name = "aFieldname";
		EFieldType fieldType = EFieldType.TEXT;
		String options = "options";
		
		FieldDescription fieldDescription = new FieldDescription(name, fieldType, options);

		fieldDescriptionService.createFieldDescription(createdUser, fieldDescription);

		// set field description deleted
		fieldDescriptionService.deleteFieldDescription(createdUser, fieldDescription.getId());
		
		Optional<FieldDescription> foundFieldDescription = fieldDescriptionService.findFieldDescriptionById(fieldDescription.getId());
		assertEquals(user.getId(), foundFieldDescription.get().getDeletedBy().getId(), "testSetDeletedFieldDescription(): DeletedBy not set");
		assertNotNull(foundFieldDescription.get().getDeletedAt(), ", \"testSetDeletedFieldDescription(): DeletedAt not set");
		
		// clean up
		fieldDescriptionService.removeFieldDescription(fieldDescription.getId());
		userService.removeUser(user.getId());
		
	}
*/
	/**
	 * test set deleted field description with null user should throw NullPointerException
	 * @throws ResourceNotFoundException 
	 */	
/*
	@Test
	public void testSetDeletedFieldDescriptionWithNullUserShouldThrowNullPointerException() throws ResourceNotFoundException {
		// create a basic user
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

		userService.createUser(null, user);

		// create basic field description
		String name = "aFieldname";
		EFieldType fieldType = EFieldType.TEXT;
		String options = "options";
		
		FieldDescription fieldDescription = new FieldDescription(name, fieldType, options);

		fieldDescriptionService.createFieldDescription(user, fieldDescription);

		// set field description
		assertThrows(NullPointerException.class, () -> fieldDescriptionService.deleteFieldDescription(null, fieldDescription.getId()));

		// clean up
		fieldDescriptionService.removeFieldDescription(fieldDescription.getId());
		userService.removeUser(user.getId());		
	}
*/
	/**
	 * test set deleted field description with not exists user should throw ResourceNotFoundException
	 * @throws ResourceNotFoundException 
	 */	
/*	
	@Test
	public void testSetDeletedFieldDescriptionWithNotExistsUserShouldThrowResourceNotFoundException() throws ResourceNotFoundException {
		// create a basic user
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

		userService.createUser(null, user);

		// create basic field description
		String name = "aFieldname";
		EFieldType fieldType = EFieldType.TEXT;
		String options = "options";
		
		FieldDescription fieldDescription = new FieldDescription(name, fieldType, options);

		fieldDescriptionService.createFieldDescription(user, fieldDescription);

		// set field description
		User notStoredUser = new User(
				firstname,				// password 
				lastname, 				// lastname
				email, 					// email
				password				// password
				);
		notStoredUser.setId(-1);
		
		assertThrows(ResourceNotFoundException.class, () -> fieldDescriptionService.deleteFieldDescription(notStoredUser, fieldDescription.getId()));
		
		// clean up
		fieldDescriptionService.removeFieldDescription(fieldDescription.getId());
		userService.removeUser(user.getId());			
	}
*/
	/**
	 * test set deleted field description with not exists field description ID should throw ResourceNotFoundException
	 * @throws ResourceNotFoundException 
	 */	
/*	
	@Test
	public void testSetDeletedFieldDescriptionWithNotExistsIDShouldThrowResourceNotFoundException() throws ResourceNotFoundException {
		// create a basic user
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

		userService.createUser(null, user);

		// create basic field description
		String name = "aFieldname";
		EFieldType fieldType = EFieldType.TEXT;
		String options = "options";
		
		FieldDescription fieldDescription = new FieldDescription(name, fieldType, options);

		fieldDescriptionService.createFieldDescription(user, fieldDescription);

		// set field description
		assertThrows(ResourceNotFoundException.class, () -> fieldDescriptionService.deleteFieldDescription(user, -1L));
		
		// clean up
		fieldDescriptionService.removeFieldDescription(fieldDescription.getId());
		userService.removeUser(user.getId());			
	}
*/
	/**
	 * test set undeleted field description 
	 * @throws ResourceNotFoundException 
	 */	
/*	
	@Test
	public void testSetUndeletedFieldDescription() throws ResourceNotFoundException {
		
		// create a basic user
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

		// create basic field description
		String name = "aFieldname";
		EFieldType fieldType = EFieldType.TEXT;
		String options = "options";
		
		FieldDescription fieldDescription = new FieldDescription(name, fieldType, options);

		fieldDescriptionService.createFieldDescription(createdUser, fieldDescription);

		// set field description deleted
		fieldDescriptionService.deleteFieldDescription(createdUser, fieldDescription.getId());
		
		// set field description undeleted
		fieldDescriptionService.undeleteFieldDescription(fieldDescription.getId());
		
		Optional<FieldDescription> foundFieldDescription = fieldDescriptionService.findFieldDescriptionById(fieldDescription.getId());
		assertNull(foundFieldDescription.get().getDeletedBy(), "testSetDeletedFieldDescription(): DeletedBy not set null");
		assertNull(foundFieldDescription.get().getDeletedAt(), ", \"testSetDeletedFieldDescription(): DeletedAt not set null");
		
		// clean up
		fieldDescriptionService.removeFieldDescription(fieldDescription.getId());
		userService.removeUser(user.getId());
		
	}
*/
	/**
	 * test set undeleted field description with not exists field description ID should throw ResourceNotFoundException
	 * @throws ResourceNotFoundException 
	 */	
/*	
	@Test
	public void testSetUndeletedFieldDescriptionWithNotExistsIDShouldThrowResourceNotFoundException() throws ResourceNotFoundException {
		// create a basic user
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

		userService.createUser(null, user);

		// create basic field description
		String name = "aFieldname";
		EFieldType fieldType = EFieldType.TEXT;
		String options = "options";
		
		FieldDescription fieldDescription = new FieldDescription(name, fieldType, options);

		fieldDescriptionService.createFieldDescription(user, fieldDescription);

		// set field description deleted
		fieldDescriptionService.deleteFieldDescription(user, fieldDescription.getId());
		
		// set field description
		assertThrows(ResourceNotFoundException.class, () -> fieldDescriptionService.undeleteFieldDescription(-1L));

		Optional<FieldDescription> foundFieldDescription = fieldDescriptionService.findFieldDescriptionById(fieldDescription.getId());
		assertNotNull(foundFieldDescription.get().getDeletedBy(), "testSetDeletedFieldDescription(): DeletedBy set null");
		assertNotNull(foundFieldDescription.get().getDeletedAt(), ", \"testSetDeletedFieldDescription(): DeletedAt set null");
		
		// clean up
		fieldDescriptionService.removeFieldDescription(fieldDescription.getId());
		userService.removeUser(user.getId());			
	}
*/
}
