package com.tmt.hurricane.services.field;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2020 Thomas Meyer. License see license.txt
 * @version		0.0.1
 --------------------------------------------------------------------------------*/
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

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
 */
@Service
public class FieldService {
    private MongoOperations mongoOperations;

	@Autowired
	private FieldDescriptionRepository fieldDescriptionRepository;
	
	@Autowired
	private CustomFieldRepository customFieldRepository;

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * the sequenceGeneratorService for the auto-increment
	 */
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	/**
	 * the user <code>creator</code> create a new field description <code>fieldDescription</code>
	 * 
	 * @param 	User creator	 						the creator
	 * @param 	FieldDescription fieldDescription		the field description  to create
	 * @return 	FieldDescripton							the created field description
	 * @throws 	ResourceNotFoundException 
	 * @throw 	ResourceNotFoundException				if the <code>creator</code> not exist 
	 *			IllegalArgumentException				if the <code>fieldDescription</code> ist null
	 */	
	public FieldDescription createFieldDescription(User creator, FieldDescription fieldDescription) throws ResourceNotFoundException {
		
		if (creator == null)
    		throw new IllegalArgumentException("FieldService::createFieldDescription(User creator: (" + creator + "), FieldDescription fieldDescription: (" + fieldDescription + ")): The creator must not be zero");
		
    	if (fieldDescription == null)
    		throw new IllegalArgumentException("FieldService::createFieldDescription(User creator: (\" + creator + \"), FieldDescription fieldDescription: (\" + fieldDescription + \")): The adding field description must not be zero");
    	
    	userRepository.findById(creator.getId())
    		.orElseThrow(() -> new ResourceNotFoundException("FieldService::createFieldDescription(User creator: (\" + creator + \"), FieldDescription fieldDescription: (\" + fieldDescription + \")): Creator not found with the id: " + creator.getId()));
    	
    	fieldDescription.setId(sequenceGeneratorService.generateSequence(FieldDescription.SEQUENCE_NAME));
    	fieldDescription.setCreatedBy(creator);
    	fieldDescription.setCreatedAt(LocalDateTime.now());
    	
        return fieldDescriptionRepository.save(fieldDescription);
    }
	
	/**
	 * the user <code>creator</code> creates a new data field based on field type <code>fieldDescription</code>
	 *  with the name <code>name</code> for the class <code>classname</code>
	 * 
	 * @param 	@NotNull User creator	 						the creator
	 * @param 	@NotBlank String name 	 						the custom field name
	 * @param 	@NotBlank String classname 						the assoziated class
	 * @param 	@NotNull FieldDescription fieldDescription		the assoziated field description
	 * @return  CustomField										the created CustomField
	 * @throw 	ResourceNotFoundException						if the <code>creator</code> or <code>fieldDescription</code> 
	 * 															not exist 
	 */	
	/*
	public CustomField createCustomField(@NotNull User creator, @NotBlank String name, @NotBlank String classname, @NotNull FieldDescription fieldDescription) throws ResourceNotFoundException {

    	userRepository.findById(creator.getId())
			.orElseThrow(() -> new ResourceNotFoundException("FieldService::createCustomField(createCustomField(User creator: " + creator + ", String name: " + name + ", String classname: " + classname + ", FieldDescription fieldDescription: " + fieldDescription + "): Creator not found with the id: " + creator.getId()));
    	
    	fieldDescriptionRepository.findById(fieldDescription.getId())
			.orElseThrow(() -> new ResourceNotFoundException("FieldService::createCustomField(createCustomField(User creator: " + creator + ", String name: " + name + ", String classname: " + classname + ", FieldDescription fieldDescription: " + fieldDescription + "): Field description not found with the id: " + fieldDescription.getId()));

 		CustomField customField = new CustomField(name, classname, fieldDescription);

    	customField.setId(sequenceGeneratorService.generateSequence(CustomField.SEQUENCE_NAME));
    	customField.setCreatedBy(creator);
    	customField.setCreatedAt(LocalDateTime.now());
    	
        return customFieldRepository.save(customField);
    }
    */
    /**
     * update a <code>fieldDescription</code> with the ID <code>id</code> by the User <code>updater</code>
     * 
     * If a <code>fieldDescription</code> with the ID <code>id</code> not found this function will be
     * throw a ResourceNotFoundException
     * 
     * @param 	User updater							The user who updates this <code>fieldDescription</code>
     * @param 	Long id									the id of the Field description to update
     * @param 	FieldDescription fieldDescription		The field description to be updated
     * @return 	FieldDescription						the updated field description
     * @throws	ResourceNotFoundException				if no Field Descirpiton found with the <code>id</code>
     *			IllegalArgumentException				if the updater or the field description is zero 
     */
    public final FieldDescription updateFieldDescription(User updater, Long id, FieldDescription fieldDescription) throws ResourceNotFoundException {
       	if (updater == null)
    		throw new IllegalArgumentException("The updater user must not be zero");
       	
       	if (fieldDescription == null)
    		throw new IllegalArgumentException("The field desciprion to be updated must not be null");
    	
        FieldDescription foundFieldDescription = fieldDescriptionRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Field description not found for this id :: " + id));
        
        fieldDescription.setUpdatedBy(updater);
        fieldDescription.setUpdatedAt(LocalDateTime.now());
        
        return fieldDescriptionRepository.save(fieldDescription);
    }    
    

    /**
     * removes a specified field description from the database permanently
     * 
     * @param id
     * @return void
     * @throws ResourceNotFoundException
     */
    public void removeFieldDescription(long id) throws ResourceNotFoundException {
        FieldDescription fieldDescription = fieldDescriptionRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("removeFieldDescription(" + id + "): Field Description not found for this id :: " + id));
        
        fieldDescriptionRepository.delete(fieldDescription);
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
        		.orElseThrow(() -> new ResourceNotFoundException("removeCustomField(" + id + "): Custom Field not found for this id :: " + id));
        
        customFieldRepository.delete(customField);
    }
	    
    /**
     * get all field descriptions
     * 
     * @return List<FieldDescription>
     */
    public List<FieldDescription> findAllFieldDescriptions() {
        return fieldDescriptionRepository.findAll();
    }
    
    /**
     * get all custom field
     * 
     * @return List<CustomField>
     */
    public List<CustomField> findAllCustomFields() {
        return customFieldRepository.findAll();
    }
    
    /**
     * get a field descriptions by his ID
     * 
     * @param long id						the id of field description to be found
     * @return Optional<FieldDescription>
     */
    public Optional<FieldDescription> findFieldDescriptionById(long id) {
    	return fieldDescriptionRepository.findById(id);
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
     * find a field description by name
     * 
     * @param String name									the name of the field description to find
     * @return List<FieldDescription>						the found field descriptions
     */
    Optional<FieldDescription> findFieldDescriptionByName(String name) {
    	return fieldDescriptionRepository.findByName(name);
    }
    
    /**
     * find a custom field by classname
     * 
     * @param String classname								the classname of the custom description to find
     * @return List<CCustomField>							the found custom fields
     */
    List<CustomField> findCustomFieldByClassname(String classname) {
    	return customFieldRepository.findByClassname(classname);
    }
	    
    /**
     * returns the field description defined by the search
     * 
     * @param Query query									the search 
     * @return List<CFieldDescription>						the found field descriptions
     */
    List<FieldDescription> findFieldDescriptionByQuery(Query query) {
    	// e.g.
    	//
    	// Query query = new Query();
    	// query.addCriteria(Criteria.where('email').is("...");
    	//
    	// List<User> users = userService.findByQuery(query);
    	//
    	return mongoOperations.find(query, FieldDescription.class);
    }
    
    /**
     * returns the custom field defined by the search
     * 
     * @param Query query									the search 
     * @return List<CustomField>							the found field descriptions
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
