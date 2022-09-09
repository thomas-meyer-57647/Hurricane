package com.tmt.hurricane.services.field;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2020 Thomas Meyer. License see license.txt
 * @version		0.0.1
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
 * test function for the field description service
 */
@SpringBootTest
class FieldDescriptionServiceTest {
	
	@Autowired 
	FieldDescriptionService fieldDescriptionService;

	@Autowired 
	UserService userService;
	
	/**
	 * test create field description with minimalst field description data
	 * @throws ResourceNotFoundException 
	 */	
	/*
	@Test
	public void testCreateFieldDescriptionthMinimalistValidData() throws ResourceNotFoundException {

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
		
		Optional<FieldDescription> foundFieldDescription = fieldDescriptionService.findFieldDescriptionById(fieldDescription.getId());
		
		assertTrue( foundFieldDescription.isPresent(), "testCreateFieldDescriptionthMinimalistValidData(): Not found FieldDescription" );
		
		assertNotNull(foundFieldDescription.get().getCreatedBy());
		assertNotNull(foundFieldDescription.get().getCreatedAt());
		assertNull(foundFieldDescription.get().getUpdatedBy());
		assertNull(foundFieldDescription.get().getUpdatedAt());
		assertNull(foundFieldDescription.get().getDeletedBy());
		assertNull(foundFieldDescription.get().getDeletedAt());
		
		assertEquals(name, foundFieldDescription.get().getName(), "testCreateWiFieldDescriptionthMinimalistValidData(): Name of FieldDescription not identical");
		assertEquals(fieldType, foundFieldDescription.get().getFieldType(), "testCreateWiFieldDescriptionthMinimalistValidData(): FieldType of FieldDescription not identical");
		assertEquals(options, foundFieldDescription.get().getOptions(), "testCreateWiFieldDescriptionthMinimalistValidData(): Options of FieldDescription not identical");
		
		// clean up
		fieldDescriptionService.removeFieldDescription(fieldDescription.getId());
		userService.removeUser(user.getId());
	}
	*/
	/**
	 * test create field description with null user
	 * @throws ResourceNotFoundException 
	 */
	/*
	@Test
	public void testCreateFieldDescriptionWithNullUser() throws ResourceNotFoundException {
		
		// create basic field description
		String name = "aFieldname";
		EFieldType fieldType = EFieldType.TEXT;
		String options = "options";
		
		FieldDescription fieldDescription = new FieldDescription(name, fieldType, options);

		assertThrows(NullPointerException.class, () -> fieldDescriptionService.createFieldDescription(null, fieldDescription));
	}
 */	
	/**
	 * test create field description with not exists user
	 * @throws ResourceNotFoundException 
	 */
/*	
	@Test
	public void testCreateFieldDescriptionCreateFieldDescriptionWithNullUser() throws ResourceNotFoundException {

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
		
		// userService.createUser(null, user);
		
		// create basic field description
		String name = "aFieldname";
		EFieldType fieldType = EFieldType.TEXT;
		String options = "options";
		
		FieldDescription fieldDescription = new FieldDescription(name, fieldType, options);

		assertThrows(ResourceNotFoundException.class, () -> fieldDescriptionService.createFieldDescription(user, fieldDescription));
	}
*/
	/**
	 * test update field description with minimalst field description data
	 * @throws ResourceNotFoundException 
	 */	
/*	
	@Test
	public void testUpdateFieldDescriptionWithMinimalistValidData() throws ResourceNotFoundException {

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

		// create new field description
		String name = "aFieldname";
		EFieldType fieldType = EFieldType.TEXT;
		String options = "options";
		
		FieldDescription fieldDescription = new FieldDescription(name, fieldType, options);

		fieldDescriptionService.createFieldDescription(user, fieldDescription);

		// update this field description
		EFieldType fieldType1 = EFieldType.NUMBER;
		fieldDescription.setFieldType(fieldType1);
		
		FieldDescription updatedFieldDescription = fieldDescriptionService.updateFieldDescription(user, fieldDescription.getId(), fieldDescription);
		
		Optional<FieldDescription> foundFieldDescription = fieldDescriptionService.findFieldDescriptionById(fieldDescription.getId());
		
		assertTrue( foundFieldDescription.isPresent(), "testUpdateFieldDescriptionWithMinimalistValidData(): Not found FieldDescription" );
		
		assertNotNull(foundFieldDescription.get().getUpdatedBy(), "testUpdateFieldDescriptionWithMinimalistValidData(): Updater not set");
		assertNotNull(foundFieldDescription.get().getUpdatedAt(), "testUpdateFieldDescriptionWithMinimalistValidData(): Update date not set");
		assertEquals(fieldType1, foundFieldDescription.get().getFieldType(), "testCreateWiFieldDescriptionthMinimalistValidData(): FieldType of FieldDescription not identical");
		
		// clean up
		fieldDescriptionService.removeFieldDescription(fieldDescription.getId());
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
