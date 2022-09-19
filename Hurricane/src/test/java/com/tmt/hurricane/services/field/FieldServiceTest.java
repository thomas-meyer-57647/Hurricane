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

import com.tmt.hurricane.exceptions.ResourceNotFoundException;
import com.tmt.hurricane.model.fields.EFieldType;
import com.tmt.hurricane.model.fields.FieldDescription;
import com.tmt.hurricane.user.model.User;
import com.tmt.hurricane.user.service.UserService;

/**
 * test function for the user service
 */
@SpringBootTest
class FieldServiceTest {
	
	@Autowired 
	FieldService fieldService;
	
	@Autowired 
	UserService userService;

	/**
	 * test create field description with minimalst field description data
	 * @throws ResourceNotFoundException 
	 */	
/*	
	@Test
	void testCreateFieldDescriptionthMinimalistValidData() throws ResourceNotFoundException {

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
		boolean exception = false;
		
		try {
			userService.createUser(null, user);
		} catch (ResourceNotFoundException e) {
			exception = true;
			e.printStackTrace();
		}
		
		assertFalse(exception, "testCreateWiFieldDescriptionthMinimalistValidData(): Couldnt create user");
		
		// create basic field description
		String name = "aFieldname";
		EFieldType fieldType = EFieldType.TEXT;
		String options = "options";
		
		FieldDescription fieldDescription = new FieldDescription(name, fieldType, options);
		
		exception = false;
		
		try {
			fieldService.createFieldDescription(user, fieldDescription);
		} catch (ResourceNotFoundException e) {
			exception = true;
			e.printStackTrace();
		}
		
		assertFalse(exception, "testCreateFieldDescriptionthMinimalistValidData(): Couldnt create FieldDescription");
		
		Optional<FieldDescription> foundFieldDescription = fieldService.findFieldDescriptionById(fieldDescription.getId());
		
		assertTrue( foundFieldDescription.isPresent(), "testCreateFieldDescriptionthMinimalistValidData(): Not found FieldDescription" );
		
		assertEquals(name, foundFieldDescription.get().getName(), "testCreateWiFieldDescriptionthMinimalistValidData(): Name of FieldDescription not identical");
		assertEquals(fieldType, foundFieldDescription.get().getFieldType(), "testCreateWiFieldDescriptionthMinimalistValidData(): FieldType of FieldDescription not identical");
		assertEquals(options, foundFieldDescription.get().getOptions(), "testCreateWiFieldDescriptionthMinimalistValidData(): Options of FieldDescription not identical");
		
		// clean up
		fieldService.removeFieldDescription(fieldDescription.getId());
		userService.removeUser(user.getId());
	}
*/
	/**
	 * test create custom Field with minimalst field description data
	 * @throws ResourceNotFoundException 
	 */	
/*	
	@Test
	void testCreateCustomFieldWithMinimalistValidData() throws ResourceNotFoundException {

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
		boolean exception = false;
		
		try {
			userService.createUser(null, user);
		} catch (ResourceNotFoundException e) {
			exception = true;
			e.printStackTrace();
		}
		
		assertFalse(exception, "testCreateCustomFieldWithMinimalistValidData(): Couldnt create user");
		
		// create basic field description
		String fieldDescriptionName = "aFieldname";
		EFieldType fieldType = EFieldType.TEXT;
		String options = "options";
		
		FieldDescription fieldDescription = new FieldDescription(fieldDescriptionName, fieldType, options);
		
		exception = false;
		
		try {
			fieldService.createFieldDescription(user, fieldDescription);
		} catch (ResourceNotFoundException e) {
			exception = true;
			e.printStackTrace();
		}
		
		assertFalse(exception, "testCreateCustomFieldWithMinimalistValidData(): Couldnt create FieldDescription");
		
		// create basic custom description
		String customFieldName = "aCustomField";
		String classname = "aClassname";

		CustomField createdCustomField = fieldService.createCustomField(user, customFieldName, classname, fieldDescription);
		assertNotNull(createdCustomField, "testCreateCustomFieldWithMinimalistValidData(): custom field not created");
		
		Optional<CustomField> foundCustomField = fieldService.findCustomFieldById(createdCustomField.getId());
		
		assertTrue( foundCustomField.isPresent(), "testCreateCustomFieldWithMinimalistValidData(): Not found FieldDescription" );
		
		assertEquals(customFieldName, foundCustomField.get().getName(), "testCreateCustomFieldWithMinimalistValidData: Name of CustomField not identical");
		assertEquals(classname, foundCustomField.get().getClassname(), "testCreateCustomFieldWithMinimalistValidData(): Classname of CustomField not identical");
		assertEquals(fieldDescription, foundCustomField.get().getFieldDescription(), "testCreateCustomFieldWithMinimalistValidData(): FieldDescription of FieldDescription not identical");
		
		// clean up
		fieldService.removeCustomField(foundCustomField.get().getId());
		fieldService.removeFieldDescription(fieldDescription.getId());
		userService.removeUser(user.getId());
	}
*/	
	/**
	 * test create field description without user should throw IllegalArgumentException
	 * @throws ResourceNotFoundException 
	 */	
/*	
	@Test
	void testCreateFieldDescriptionthWithoutUserShouldThrowIllegalArgumentationException() throws ResourceNotFoundException {

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
		boolean exception = false;
		
		try {
			userService.createUser(null, user);
		} catch (ResourceNotFoundException e) {
			exception = true;
			e.printStackTrace();
		}
		
		assertFalse(exception, "testCreateFieldDescriptionthWithoutUserShouldThrowIllegalArgumentationException(): Couldnt create user");
		
		// create basic field description
		String name = "aFieldname";
		EFieldType fieldType = EFieldType.TEXT;
		String options = "options";
		
		FieldDescription fieldDescription = new FieldDescription(name, fieldType, options);
		
		exception = false;
		
		try {
			fieldService.createFieldDescription(null, fieldDescription);
		} catch (IllegalArgumentException e) {
			exception = true;
			e.printStackTrace();
		}
		
		assertTrue(exception, "testCreateFieldDescriptionthMinimalistValidData(): FieldDescription could be create");
		
		// clean up
		userService.removeUser(user.getId());
	}
*/
	/**
	 * test create field description without field description should throw IllegalArgumentException
	 * @throws ResourceNotFoundException 
	 */	
/*	
	@Test
	void testCreateWiFieldDescriptionthWithoutFieldDescriptionShouldThrowIllegalArgumentationException() throws ResourceNotFoundException {
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
		boolean exception = false;
		
		try {
			userService.createUser(null, user);
		} catch (ResourceNotFoundException e) {
			exception = true;
			e.printStackTrace();
		}

		assertFalse(exception, "testCreateWiFieldDescriptionthWithoutFieldDescriptionShouldThrowIllegalArgumentationException(): Couldnt create user");
		assertNotNull(user);
		
		// create basic field description
		exception = false;
		
		try {
			fieldService.createFieldDescription(user, null);
		} catch (IllegalArgumentException e) {
			exception = true;
			e.printStackTrace();
		}
		
		assertTrue(exception, "testCreateWiFieldDescriptionthWithoutFieldDescriptionShouldThrowIllegalArgumentationException(): FieldDescription could be create");

		// clean up
		userService.removeUser(user.getId());
	}
*/	
	/**
	 * test create field description with not exists User should throw ResourceNotFoundException
	 * @throws ResourceNotFoundException 
	 */	
	// PROVIDED FOR LATER CHECK: This function does not work correctly because ResourceNotFound is not caught.
	/*
	@Test
	void testCreateFieldDescriptionthWithNotExistsUserShouldThrowResourceNotFoundException() throws ResourceNotFoundException {
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
		boolean exception = false;
		
		try {
			userService.createUser(null, user);
		} catch (ResourceNotFoundException e) {
			exception = true;
			e.printStackTrace();
		}

		assertFalse(exception, "testCreateFieldDescriptionthWithNotExistsUserShouldThrowResourceNotFoundException(): Couldnt create user");
		assertNotNull(user);
		
		// create basic field description
		user.setId(999999);                   // set user to an illagel index
		
		String name = "aFieldname";
		EFieldType fieldType = EFieldType.TEXT;
		String options = "options";
		
		FieldDescription fieldDescription = new FieldDescription(name, fieldType, options);
		
		exception = false;
		
		try {
			fieldService.createFieldDescription(user, fieldDescription);
		} catch (ResourceNotFoundException e) {
			exception = true;
//			e.printStackTrace();
		}
		
		assertTrue(exception, "testCreateWiFieldDescriptionthWithNotExistsUserShouldThrowResourceNotFoundException(): FieldDescription could be create");

		// clean up
		userService.removeUser(user.getId());
	}
	*/
	
}
