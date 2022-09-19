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

import com.tmt.hurricane.exceptions.ResourceNotFoundException;
import com.tmt.hurricane.helper.database.service.SequenceGeneratorService;
import com.tmt.hurricane.model.fields.FieldDescription;
import com.tmt.hurricane.repositories.field.FieldDescriptionRepository;
import com.tmt.hurricane.user.model.User;
import com.tmt.hurricane.user.repository.UserRepository;

/**
 * this is the service for the additional fields
 * 
 *  private MongoOperations mongoOperations;									used for search operation
 *	private FieldDescriptionRepository fieldDescriptionRepository;				the field description repository
 *	private UserRepository userRepository;										the user repository
 *	SequenceGeneratorService sequenceGeneratorService;							used for auto increment	
 *
 *  createFieldDescription(User, FieldDescription)								create a FieldDescripton by a User
 *  updateFieldDescription(User, Long, FieldDescription) 						update a specific FieldDescription by a User
 *  deleteFieldDescription(@NotNull User deleter, long id) 						sets a specified field description as deleted
 *  undeleteFieldDescription(long) 												sets a specified field description as undeleted
 *  removeFieldDescription(long) 												removes a specified field description from the database permanently
 *  List<FieldDescription> findAllFieldDescription() 							get all field descriptions
 *  Optional<FieldDescription> findFieldDescriptionById(long) 					get a field descriptions by his ID
 *  Optional<FieldDescription> findFieldDescriptionByName(String name)			find a field description by name
 *  List<FieldDescription> findFieldDescriptionByQuery(Query query)				returns the field description defined by the search
 */
@Service
public class FieldDescriptionService {
	
    private MongoOperations mongoOperations;

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
	 * the user <code>creator</code> create a new field description <code>fieldDescription</code>
	 * 
	 * @param 	User creator	 						the creator
	 * @param 	FieldDescription fieldDescription		the field description  to create
	 * @return 	FieldDescripton							the created field description
	 * @throws 	ResourceNotFoundException				if the <code>creator</code> not exist
	 * 			NullPointerException					if user is null 
	 */	
	public FieldDescription createFieldDescription(@NotNull User creator, @NotNull FieldDescription fieldDescription) throws ResourceNotFoundException {
		
    	userRepository.findById(creator.getId())
    		.orElseThrow(() -> new ResourceNotFoundException("FieldDescriptionService::createFieldDescription(User creator: (" + creator + "), FieldDescription fieldDescription: (" + fieldDescription + ")): Creator not found with the id: " + creator.getId()));
    	
    	fieldDescription.setId(sequenceGeneratorService.generateSequence(FieldDescription.SEQUENCE_NAME));
    	fieldDescription.setCreatedBy(creator);
    	fieldDescription.setCreatedAt(LocalDateTime.now());
    	
        return fieldDescriptionRepository.save(fieldDescription);
    }

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
    public final FieldDescription updateFieldDescription(@NotNull User updater, long id, @NotNull FieldDescription fieldDescription) throws ResourceNotFoundException {
    	
        User foundUser = userRepository.findById(updater.getId())
        		.orElseThrow(() -> new ResourceNotFoundException("FieldDescriptionService::updateFieldDescription(@NotNull User updater: " + updater + ", Long id: " + id + ", @NotNull FieldDescription fieldDescription: " + fieldDescription + "): Field description not found for this id :: " + id));
    	
        FieldDescription foundFieldDescription = fieldDescriptionRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("FieldDescriptionService::updateFieldDescription(@NotNull User updater: " + updater + ", Long id: " + id + ", @NotNull FieldDescription fieldDescription: " + fieldDescription + "): Field description not found for this id :: " + id));
        
        foundFieldDescription.setUpdatedBy(updater);
        foundFieldDescription.setUpdatedAt(LocalDateTime.now());
        foundFieldDescription.setName(fieldDescription.getName());
        foundFieldDescription.setFieldType(fieldDescription.getFieldType());
        foundFieldDescription.setOptions(fieldDescription.getOptions());
        
        return fieldDescriptionRepository.save(foundFieldDescription);
    }    
    /**
     * sets a specified field description as deleted
     * 
     * @param 	deleter							the user who sets this field description as deleted 
     * @param 	id								the ID of the field description who should be set as deleted
     * @return 	FieldDescription				the FieldDescription who was set as deleted
     * @throws 	ResourceNotFoundException		if the FieldDescription with the ID <code>id</code> not found
     */
    public final FieldDescription deleteFieldDescription(@NotNull User deleter, long id) throws ResourceNotFoundException {
    	
       	User foundUser = userRepository.findById(deleter.getId())
        		.orElseThrow(() -> new ResourceNotFoundException("FieldDescriptionService::deleteFieldDescription(@NotNull User deleter: " + deleter + ", long id: " + id + "): User not found with ID " + id ));
    	
       	FieldDescription fieldDescription = fieldDescriptionRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("FieldDescriptionService::deleteFieldDescription(@NotNull User deleter: " + deleter + ", long id: " + id + "): FielDescription with the ID " + id + " not found"));
        
       	fieldDescription.setDeletedBy(foundUser);
       	fieldDescription.setDeletedAt(LocalDateTime.now());
        
        return fieldDescriptionRepository.save(fieldDescription);
    }

    /**
     * sets a specified field description as undeleted
     * 
     * @param 	id								the ID of the field description who should be set as undeleted
     * @return 	FieldDescription				the FieldDescription who was set as undeleted
     * @throws 	ResourceNotFoundException
     */
    public final FieldDescription undeleteFieldDescription(long id) throws ResourceNotFoundException {
    	
        FieldDescription fieldDescription = fieldDescriptionRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("FieldDescriptionService::undeleteFieldDescription(long id: " + id + "): FieldDescription with the ID " + id + " not found"));
        
        fieldDescription.setDeletedBy(null);
        fieldDescription.setDeletedAt(null);
        
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
        		.orElseThrow(() -> new ResourceNotFoundException("FieldDescriptionService::removeFieldDescription(long id: " + id + "): Field Description not found for this id :: " + id));
        
        fieldDescriptionRepository.delete(fieldDescription);
    }
    
    /**
     * get all field descriptions
     * 
     * @return List<FieldDescription>
     */
    public List<FieldDescription> findAllFieldDescription() {
        return fieldDescriptionRepository.findAll();
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
     * find a field description by name
     * 
     * @param String name									the name of the field description to find
     * @return List<FieldDescription>						the found field descriptions
     */
    Optional<FieldDescription> findFieldDescriptionByName(String name) {
    	return fieldDescriptionRepository.findByName(name);
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

}
