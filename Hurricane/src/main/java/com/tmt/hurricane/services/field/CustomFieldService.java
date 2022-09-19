package com.tmt.hurricane.services.field;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.tmt.hurricane.exceptions.NotDefinedException;
import com.tmt.hurricane.exceptions.ResourceNotFoundException;
import com.tmt.hurricane.helper.database.service.SequenceGeneratorService;
import com.tmt.hurricane.model.fields.CustomField;
import com.tmt.hurricane.model.fields.FieldDescription;
import com.tmt.hurricane.repositories.field.CustomFieldRepository;
import com.tmt.hurricane.repositories.field.FieldDescriptionRepository;
import com.tmt.hurricane.user.model.User;
import com.tmt.hurricane.user.repository.UserRepository;

/**
 * this is the service for the additional fields
 * 
 */
@Service
public class CustomFieldService {
	
    private MongoOperations mongoOperations;

	@Autowired
	private CustomFieldRepository customFieldRepository;
	
	@Autowired
	private FieldDescriptionRepository fieldDescriptionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * the sequenceGeneratorService for the auto-increment
	 */
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	/**
	 * the user <code>creator</code> create a new custom description <code>customField</code>
	 * 
	 * @param 	User creator	 					the creator
	 * @param 	CustomField customField				the custom field to create
	 * @return 	CustomField							the created custom field
	 * @throws 	ResourceNotFoundException			if the <code>creator</code> not exist
	 * @throws 	NotDefinedException 				if <code>creater</code> or <code>customField</code> null
	 */	
	public CustomField createCustomField(@NotNull User creator, @NotNull CustomField customField) throws ResourceNotFoundException, NotDefinedException {
		
		if ( creator == null )
			throw new NotDefinedException("CustomFieldService::createCustomField(User creator: (" + creator + "), CustomField customField: (" + customField + ")): Creater must not be zero");
		
		if ( customField == null )
			throw new NotDefinedException("CustomFieldService::createCustomField(User creator: (" + creator + "), CustomField customField: (" + customField + ")): Custom field must not be zero");

		if ( customField.getFieldDescription() == null )
			throw new NotDefinedException("CustomFieldService::createCustomField(User creator: (" + creator + "), CustomField customField: (" + customField + ")): Field Description of Custom Field must not be null");
		
    	userRepository.findById(creator.getId())
    		.orElseThrow(() -> new ResourceNotFoundException("CustomFieldService::createCustomField(User creator: (" + creator + "), CustomField customField: (" + customField + ")): Creator not found with the id: " + creator.getId()));
    	
    	fieldDescriptionRepository.findById(customField.getFieldDescription().getId())
			.orElseThrow(() -> new ResourceNotFoundException("CustomFieldService::createCustomField(User creator: (" + creator + "), CustomField customField: (" + customField + ")): Field Description not found with the id: " + creator.getId()));
    	
    	customField.setId(sequenceGeneratorService.generateSequence(FieldDescription.SEQUENCE_NAME));
    	customField.setCreatedBy(creator);
    	customField.setCreatedAt(LocalDateTime.now());
    	
        return customFieldRepository.save(customField);
    }

    /**
     * update a <code>customField</code> with the ID <code>id</code> by the User <code>updater</code>
     * 
     * If a <code>customField</code> with the ID <code>id</code> not found this function will be
     * throw a ResourceNotFoundException
     * 
     * @param 	User updater							The user who updates this <code>customField</code>
     * @param 	Long id									the id of the custom field to update
     * @param 	CustomField customField					The custom field to be updated
     * @return 	CustomField								the updated custom field
     * @throws	ResourceNotFoundException				if no custom field found with the <code>id</code>
     */
    public final CustomField updateCustomField(@NotNull User updater, long id, @NotNull CustomField customField) throws ResourceNotFoundException {
    	
        User foundUser = userRepository.findById(updater.getId())
        		.orElseThrow(() -> new ResourceNotFoundException("CustomFieldService::updateCustomField(@NotNull User updater: " + updater + ", Long id: " + id + ", @NotNull CustomField customField: " + customField + "): Custom field not found for this id :: " + id));
    	
        CustomField foundCustomField = customFieldRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("CustomFieldService::updateCustomField(@NotNull User updater: " + updater + ", Long id: " + id + ", @NotNull CustomField customField: " + customField + "): Custom field not found for this id :: " + id));
        
        foundCustomField.setUpdatedBy(updater);
        foundCustomField.setUpdatedAt(LocalDateTime.now());
        foundCustomField.setName(customField.getName());
        foundCustomField.setClassname(customField.getClassname());
        foundCustomField.setFieldDescription(customField.getFieldDescription());
        
        return customFieldRepository.save(foundCustomField);
    }    
    
    /**
     * sets a specified custom field as deleted
     * 
     * @param 	deleter							the user who sets this custom field as deleted 
     * @param 	id								the ID of the custom field who should be set as deleted
     * @return 	CustomField						the CustomField who was set as deleted
     * @throws 	ResourceNotFoundException		if the CustomField with the ID <code>id</code> not found
     */
    public final CustomField deleteCustomField(@NotNull User deleter, long id) throws ResourceNotFoundException {
    	
       	User foundUser = userRepository.findById(deleter.getId())
        		.orElseThrow(() -> new ResourceNotFoundException("CustomFieldService::deleteCustomField(@NotNull User deleter: " + deleter + ", long id: " + id + "): User not found with ID " + id ));
    	
       	CustomField customField = customFieldRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("CustomFieldService::deleteCustomField(@NotNull User deleter: " + deleter + ", long id: " + id + "): CustomField with the ID " + id + " not found"));
        
       	customField.setDeletedBy(foundUser);
       	customField.setDeletedAt(LocalDateTime.now());
        
        return customFieldRepository.save(customField);
    }

    /**
     * sets a specified custom field as undeleted
     * 
     * @param 	id								the ID of the custom field who should be set as undeleted
     * @return 	CustomField						the CustomField woh was set as undeleted
     * @throws 	ResourceNotFoundException
     */
    public final CustomField undeleteCustomField(long id) throws ResourceNotFoundException {
    	
        CustomField customField = customFieldRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("CustomFieldService::undeleteCustomField(long id: " + id + "): CustomField with the ID " + id + " not found"));
        
        customField.setDeletedBy(null);
        customField.setDeletedAt(null);
        
        return customFieldRepository.save(customField);
    }

    /**
     * removes a specified custom field from the database permanently
     * 
     * @param id
     * @return void
     * @throws ResourceNotFoundException
     */
    public void removeCustomField(long id) throws ResourceNotFoundException {
        CustomField customField = customFieldRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("CustomFieldService::removeCustomField(long id: " + id + "): CustomField not found for this id :: " + id));
        
        customFieldRepository.delete(customField);
    }
    
    /**
     * get all custom fields
     * 
     * @return List<FieldDescription>
     */
    public List<CustomField> findAllCustomField() {
        return customFieldRepository.findAll();
    }
    
    /**
     * get a custom field by his ID
     * 
     * @param long id						the id of custom field to be found
     * @return Optional<CustomField>
     */
    public Optional<CustomField> findCustomFieldById(long id) {
    	return customFieldRepository.findById(id);
    }
    
    /**
     * find a custom field by classname
     * 
     * @param String classname				the classname of the custom field to find
     * @return List<CustomField>			the found field descriptions
     */
    List<CustomField> findCustomFieldByClassname(String classname) {
    	return customFieldRepository.findByClassname(classname);
    }
    
    /**
     * returns the custom field defined by the search
     * 
     * @param Query query					the search 
     * @return List<CCustomField>			the found custom fields
     */
    List<CustomField> findCustomFieldByQuery(Query query) {
    	// e.g.
    	//
    	// Query query = new Query();
    	// query.addCriteria(Criteria.where('email').is("...");
    	//
    	// List<User> users = userService.findByQuery(query);
    	//
    	return mongoOperations.find(query, CustomField.class);
    }

}
