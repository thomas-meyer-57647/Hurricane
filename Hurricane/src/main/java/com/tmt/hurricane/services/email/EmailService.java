package com.tmt.hurricane.services.email;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.tmt.hurricane.exceptions.ResourceNotFoundException;
import com.tmt.hurricane.helper.database.service.SequenceGeneratorService;
import com.tmt.hurricane.model.communication.email.EMailTemplate;
import com.tmt.hurricane.repositories.communication.email.EMailTemplateRepository;
import com.tmt.hurricane.user.model.User;
import com.tmt.hurricane.user.repository.UserRepository;

/**
 * this is the service for the email management
 */
@Service
public class EmailService {
    private MongoOperations mongoOperations;

	@Autowired
	private EMailTemplateRepository emailTemplateRepository;

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * the sequenceGeneratorService for the auto-increment
	 */
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	/**
	 * the user <code>creator</code> create a new email template <code>template</code>
	 * 
	 * @param 	User creator	 						the creator
	 * @param 	EMailTemplate template					the template to create
	 * @return 	EMailTemplate 							the created EMailTemplate
	 * @throws 	ResourceNotFoundException 
	 * @throw 	ResourceNotFoundException				if the <code>a user exist with the email address 
	 *			IllegalArgumentException				if the adding user is zero
	 */	
	public EMailTemplate createEmailTemplate(User creator, EMailTemplate template) throws ResourceNotFoundException {
    	if (template == null)
    		throw new IllegalArgumentException("The adding template must not be zero");
    	
    	userRepository.findById(creator.getId())
    		.orElseThrow(() -> new ResourceNotFoundException("Creator not found with the id :: " + creator.getId()));
    	
    	template.setId(sequenceGeneratorService.generateSequence(EMailTemplate.SEQUENCE_NAME));
    	template.setCreatedBy(creator);
    	template.setCreatedAt(LocalDateTime.now());
    	
        return emailTemplateRepository.save(template);
    }
	    
    /**
     * update a <code>emailTemplate</code> with the ID <code>id</code> from the User 
     * <code>updater</code>
     * 
     * If a <code>emailTemplate</code> with the ID <code>id</code> not found this function will be
     * throw a ResourceNotFoundException
     * 
     * @param 	User updater							The user who updates this EMailTemplate
     * @param 	Long id									the id of the EMailTemplate to update
     * @param 	EMailTemplate emailTemplate				The EMailTemplate to be updated
     * @return 	EMailTemplate							the updated EMailTemplate
     * @throws	ResourceNotFoundException				if no User found with the <code>userID</code>
     *			IllegalArgumentException				if the updater or the user is zero 
     */
    public final EMailTemplate updateEMailTemplate(User updater, Long id, EMailTemplate emailTemplate) throws ResourceNotFoundException {
       	if (updater == null)
    		throw new IllegalArgumentException("The updater user must not be zero");
       	
       	if (emailTemplate == null)
    		throw new IllegalArgumentException("The EMailTemplate to be updated must not be null");
    	
        EMailTemplate foundEMailTemplate = emailTemplateRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("EMailTemplate not found for this id :: " + id));
        
        foundEMailTemplate.setUpdatedBy(updater);
        foundEMailTemplate.setUpdatedAt(LocalDateTime.now());
        
        return emailTemplateRepository.save(emailTemplate);
    }    
	    
    /**
     * sets a specified email template as deleted
     * 
     * @param 	deleter							the user who sets this user as deleted 
     * @param 	id								the ID of the email template who should be set as deleted
     * @return 	void
     * @throws 	ResourceNotFoundException
     *      	IllegalArgumentException		if the delete is zero
     */
    public final EMailTemplate deleteEmailTemplate(User deleter, long id) throws ResourceNotFoundException {
       	if (deleter == null)
    		throw new IllegalArgumentException("The deleter user must not be zero");
    	
        EMailTemplate emailTemplate = emailTemplateRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("EMailTemplate not found for this id :: " + id));
        
        emailTemplate.setDeletedBy(deleter);
        emailTemplate.setDeletedAt(LocalDateTime.now());
        
        return emailTemplateRepository.save(emailTemplate);
    }
	    
    /**
     * sets a specified email template as undeleted
     * 
     * @param 	id								the ID of the email template who should be set as undeleted
     * @return 	void
     * @throws 	ResourceNotFoundException
     */
    public final EMailTemplate undeleteCompany(long id) throws ResourceNotFoundException {
    	
        EMailTemplate emailTemplate = emailTemplateRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("EMailTemplate not found for this id :: " + id));
        
        emailTemplate.setDeletedBy(null);
        emailTemplate.setDeletedAt(null);
        
        return emailTemplateRepository.save(emailTemplate);
    }
	        
    /**
     * removes a specified email template from the database permanently
     * 
     * @param id
     * @return void
     * @throws ResourceNotFoundException
     */
    public void removeEMailTemplate(long id) throws ResourceNotFoundException {
        EMailTemplate emailTemplate = emailTemplateRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("EMailTemplate not found for this id :: " + id));
        
        emailTemplateRepository.delete(emailTemplate);
    }
	    
    /**
     * get all email repositories
     * 
     * @return List<EMailTemplate>
     */
    public List<EMailTemplate> findAll() {
        return emailTemplateRepository.findAll();
    }

    /**
     * get a <code>EMailTemplate</code> with the ID <code>id</code>
     * 
     * @param Long id
     * @return Optional<EMailTemplate>
     */
    public Optional<EMailTemplate> findById(long id) {
    	return emailTemplateRepository.findById(id);
    }
	    
    /**
     * find a email template by name
     * 
     * @param String name									the email template name to found
     * @return List<EMailTemplate>							the found users
     */
    Optional<EMailTemplate> findByName(String namel) {
    	return emailTemplateRepository.findByName(namel);
    }
	    
    /**
     * returns the email template defined by the search
     * 
     * @param Query query									the search 
     * @return List<EMailTemplateU>							the found email templates
     */
    List<EMailTemplate> findByQuery(Query query) {
    	// e.g.
    	//
    	// Query query = new Query();
    	// query.addCriteria(Criteria.where('email').is("...");
    	//
    	// List<User> users = userService.findByQuery(query);
    	//
    	return mongoOperations.find(query, EMailTemplate.class);
    }

}
