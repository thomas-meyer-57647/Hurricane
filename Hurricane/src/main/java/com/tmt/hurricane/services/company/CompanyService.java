package com.tmt.hurricane.services.company;
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
import com.tmt.hurricane.model.company.Company;
import com.tmt.hurricane.repositories.company.CompanyRepository;
import com.tmt.hurricane.user.model.User;
import com.tmt.hurricane.user.repository.UserRepository;

/**
 * this is the service for the email management
 */
@Service
public class CompanyService {
    private MongoOperations mongoOperations;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * the sequenceGeneratorService for the auto-increment
	 */
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	/**
	 * the user <code>creator</code> create a new company <code>company</code>
	 * 
	 * @param 	User creator	 						the creator
	 * @param 	Company company							the company to create
	 * @return 	Company 								the created company
	 * @throws 	ResourceNotFoundException 
	 * @throw 	ResourceNotFoundException				if the <code>creator</code> not exist 
	 *			IllegalArgumentException				if the <code>company</code> ist null
	 */	
	public Company createCompany(User creator, Company company) throws ResourceNotFoundException {
    	if (company == null)
    		throw new IllegalArgumentException("The adding company must not be zero");
    	
    	userRepository.findById(creator.getId())
    		.orElseThrow(() -> new ResourceNotFoundException("Creator not found with the id :: " + creator.getId()));
    	
    	company.setId(sequenceGeneratorService.generateSequence(Company.SEQUENCE_NAME));
    	company.setCreatedBy(creator);
    	company.setCreatedAt(LocalDateTime.now());
    	
        return companyRepository.save(company);
    }
	    
    /**
     * update a <code>company</code> with the ID <code>id</code> by the User <code>updater</code>
     * 
     * If a <code>company</code> with the ID <code>id</code> not found this function will be
     * throw a ResourceNotFoundException
     * 
     * @param 	User updater							The user who updates this <code>Company</code>
     * @param 	Long id									the id of the company to update
     * @param 	Company company							The Company to be updated
     * @return 	Company									the updated Company
     * @throws	ResourceNotFoundException				if no User found with the <code>id</code>
     *			IllegalArgumentException				if the updater or the company is zero 
     */
    public final Company updateCompany(User updater, Long id, Company company) throws ResourceNotFoundException {
       	if (updater == null)
    		throw new IllegalArgumentException("The updater user must not be zero");
       	
       	if (company == null)
    		throw new IllegalArgumentException("The company to be updated must not be null");
    	
        Company foundCompany = companyRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + id));
        
        company.setUpdatedBy(updater);
        company.setUpdatedAt(LocalDateTime.now());
        
        return companyRepository.save(company);
    }    
	    
    /**
     * sets a specified company as deleted
     * 
     * @param 	deleter							the user who sets this user as deleted 
     * @param 	id								the ID of the company who should be set as deleted
     * @return 	void
     * @throws 	ResourceNotFoundException
     *      	IllegalArgumentException		if the delete is zero
     */
    public final Company deleteCompany(User deleter, long id) throws ResourceNotFoundException {
       	if (deleter == null)
    		throw new IllegalArgumentException("The deleter user must not be zero");
    	
        Company company = companyRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + id));
        
        company.setDeletedBy(deleter);
        company.setDeletedAt(LocalDateTime.now());
        
        return companyRepository.save(company);
    }
	    
    /**
     * sets a specified company as undeleted
     * 
     * @param 	id								the ID of the company who should be set as undeleted
     * @return 	void
     * @throws 	ResourceNotFoundException
     */
    public final Company undeleteCompanyr(long id) throws ResourceNotFoundException {
    	
        Company company = companyRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + id));
        
        company.setDeletedBy(null);
        company.setDeletedAt(null);
        
        return companyRepository.save(company);
    }
	        
    /**
     * removes a specified company from the database permanently
     * 
     * @param id
     * @return void
     * @throws ResourceNotFoundException
     */
    public void removeCompany(long id) throws ResourceNotFoundException {
        Company company = companyRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + id));
        
        companyRepository.delete(company);
    }
	    
    /**
     * get all companies
     * 
     * @return List<Company>
     */
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    /**
     * get a <code>Company</code> with the ID <code>id</code>
     * 
     * @param Long id
     * @return Optional<Company>
     */
    public Optional<Company> findById(long id) {
    	return companyRepository.findById(id);
    }
	    
    /**
     * find a company by name
     * 
     * @param String name									the email template name to found
     * @return List<Company>								the found companies
     */
    Optional<Company> findByName(String name) {
    	return companyRepository.findByName(name);
    }
	    
    /**
     * returns the companies defined by the search
     * 
     * @param Query query									the search 
     * @return List<Company>								the found companies
     */
    List<Company> findByQuery(Query query) {
    	// e.g.
    	//
    	// Query query = new Query();
    	// query.addCriteria(Criteria.where('email').is("...");
    	//
    	// List<User> users = userService.findByQuery(query);
    	//
    	return mongoOperations.find(query, Company.class);
    }

}
