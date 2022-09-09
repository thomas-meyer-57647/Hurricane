package com.tmt.hurricane.user.service;
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
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Service;

import com.tmt.hurricane.exceptions.DuplicateException;
import com.tmt.hurricane.exceptions.NotDefinedException;
import com.tmt.hurricane.exceptions.ResourceNotFoundException;
import com.tmt.hurricane.helper.database.service.SequenceGeneratorService;
import com.tmt.hurricane.model.fields.CustomField;
import com.tmt.hurricane.model.fields.FieldDescription;
import com.tmt.hurricane.services.field.FieldService;
import com.tmt.hurricane.user.model.User;
import com.tmt.hurricane.user.repository.UserRepository;

/**
 * this is the service for the user management
 * 
 * User createUser(User creator, User user) throws ResourceNotFoundException 		
 * 
 * the creator <code>creator</code> create a new <code>user</code>. If <code>user</code> null this 
 * function return <code>ResourceNotFoundException</code>
 * 
 * final User updateUser(User updater, Long id, User user) throws ResourceNotFoundException

 * the <code>updater</code> updated the user with the ID <code>id</code> with the data of
 * <code>user</code>.If a <code>User</code> with the ID <code>id</code> not found this function will
 * be throw a ResourceNotFoundException
 * 
 * final User deleteUser(User deleter, long id) throws ResourceNotFoundException 
 * 
 * the <code>deleter</code> set the user with the ID <code>id</code> as deleted. This function will
 * be throw a ResourceNotFoundException if a User with the ID <code>id</code> not exists
 * 
 * public final User undeleteUser(long id) throws ResourceNotFoundException {
 * 
 * the user with the ID <code>id</code> will be set as undeleted. This function will
 * be throw a ResourceNotFoundException if a User with the ID <code>id</code> not exists
 * 
 * void removeUser(long id) throws ResourceNotFoundException 
 * 
 * removes the specified user with the ID <code>id</code> from the database permanently
 * This function will be throw a ResourceNotFoundExceiptions if the User with the ID
 * code>ide</code> not eixst.
 * 
 * List<User> findAll() 
 * 
 * get all Users
 * 
 * public Optional<User> findById(long userId) 
 * 
 * get a <code>User</code> with the ID <code>id</code>
 * 
 * Optional<User> findByEmail(String email) 
 * 
 * find a user with the email <code>email</code>
 * 
 * List<User> findByQuery(Query query) 
 * 
 * returns the users defined by the search <code>query</code>
 * 
 */
@Service
public class UserService {

    private MongoOperations mongoOperations;

	@Autowired
	private UserRepository userRepository;

	/**
	 * the sequenceGeneratorService for the auto-increment
	 */
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	private FieldService fieldService;

    @Autowired
    public UserService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
    
	/**
	 * create a user
	 * 
	 * @param 	User creator	 						the creator
	 * @param 	User user								the user to save
	 * @return 	User									the created User
	 * @throws 	ResourceNotFoundException 
	 * @throws NotDefinedException 
	 * @throws DuplicateException 
	 * @throw 	ResourceNotFoundException				if a user exist with the email address 
	 *			NotDefinedException						if the adding user is zero
	 */	
	public User createUser(User creator, User user) throws ResourceNotFoundException, NotDefinedException, DuplicateException {
    	if (user == null)
    		throw new NotDefinedException("UserService::createUser(" + creator + ", " + user + "): The adding user must not be zero");
    	
    	if ( creator != null ) {
    		userRepository.findById(creator.getId())
    			.orElseThrow(() -> new ResourceNotFoundException("UserService::createUser(" + creator + ", " + user + "): Creator not found with the id :: " + creator.getId()));
    	}

    	// This is an alternative solution, as @Indexed(unique=true) from the Email field does not seem to work.
    	List<User> userExists = this.findUserByEmail(user.getEmail());
    	
    	if ( userExists.size() > 0 ) {
    		throw new DuplicateException("UserService::createUser(" + creator + ", " + user + "): EMail (" + user.getEmail() +  ") allready exists");
    	}
    	
    	user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
    	user.setCreatedBy(creator);
    	user.setCreatedAt(LocalDateTime.now());
    	
        return userRepository.save(user);
    }
	    
    /**
     * update a <code>User</code> with the id <code>userID</code> from the User 
     * <code>updaterUser</code>
     * 
     * If a <code>User</code> with the id <code>userID</code> not found this function will be
     * throw a ResourceNotFoundException
     * 
     * @param 	User updater							The user who updates this user
     * @param 	Long id									the id of the user
     * @param 	User user								The user to be updated
     * @return 	User									the updated User
     * @throws	ResourceNotFoundException				if no User found with the <code>userID</code>
     *			NotDefinedException						if the updater or the user is zero 
     * @throws NotDefinedException 
     * @throws DuplicateException 
     */
    public final User updateUser(User updater, Long id, User user) throws ResourceNotFoundException, NotDefinedException, DuplicateException {
       	if (updater == null)
    		throw new NotDefinedException("UserService::updateUser(User updater: " + updater + ", Long id: " + id + ", User user: " + user + "): The updater user must not be zero");
       	
       	if (user == null)
    		throw new NotDefinedException("UserService::updateUser(User updater: " + updater + ", Long id: " + id + ", User user: " + user + "): The user to be updated must not be null");
    	
        User foundUser = userRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("UserService::updateUser(User updater: " + updater + ", Long id: " + id + ", User user: " + user + "): User not found for this id :: " + id));
        
        User foundUpdater = userRepository.findById(updater.getId())
        		.orElseThrow(() -> new ResourceNotFoundException("UserService::updateUser(User updater: " + updater + ", Long id: " + id + ", User user: " + user + "): Updater not found for this id :: " + updater.getId()));

        // find a user with not id but with the email user
    	List<User> userExists = this.findUserByEmail(user.getEmail());
    	
    	if ( userExists.size() > 0 && userExists.get(0).getId() != id ) {
    		throw new DuplicateException("UserService::updateUser(User updater: " + updater + ", Long id: " + id + ", User user: " + user + "): Email already exists");
    	}
        
        foundUser.setUpdatedBy(foundUpdater);
        foundUser.setUpdatedAt(LocalDateTime.now());
        foundUser.setFirstname(user.getFirstname());
        foundUser.setLastname(user.getLastname());
        foundUser.setEmail(user.getEmail());
        foundUser.setPassword(user.getPassword());
        foundUser.setBlocked(user.isBlocked());
        foundUser.setEnabled(user.isEnabled());
        foundUser.setPictogram(foundUser.getPictogram());
        foundUser.setDowntimes(user.getDowntimes());
        foundUser.setCommunications(user.getCommunications());
        foundUser.setJobs(user.getJobs());
        foundUser.setCompany(user.getCompany());
        foundUser.setWorkTimes(user.getWorkTimes());

        return userRepository.save(foundUser);
    }    
	    
    /**
     * sets a specified user as deleted
     * 
     * @param 	deleter							the user who sets this user as deleted 
     * @param 	id								the ID of the user who should be set as deleted
     * @return 	void
     * @throws 	ResourceNotFoundException
     *      	NotDefinedException				if the updater or the user is zero
     */
    public final User deleteUser(User deleter, long id) throws ResourceNotFoundException, NotDefinedException  {
       	if (deleter == null)
    		throw new NotDefinedException("UserService::deleteUser(User deleter: " + deleter + ", long id: " + id + "): The deleter user must not be zero");

        User foundDeleter = userRepository.findById(deleter.getId())
        		.orElseThrow(() -> new ResourceNotFoundException("UserService::deleteUser(User deleter: " + deleter + ", long id: " + id + "): The deleter not found"));
       	
        User user = userRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("UserService::deleteUser(User deleter: " + deleter + ", long id: " + id + "): The user not found with the ID: " + id));
        
        user.setDeletedBy(foundDeleter);
        user.setDeletedAt(LocalDateTime.now());
        
        return userRepository.save(user);
    }
	    
    /**
     * sets a specified user as undeleted
     * 
     * @param 	id								the ID of the user who should be set as undeleted
     * @return 	void
     * @throws 	ResourceNotFoundException
     *      	IllegalArgumentException		if the updater or the user is zero
     */
    public final User undeleteUser(long id) throws ResourceNotFoundException {
    	
        User user = userRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("UserService::undeleteUser(long id: " + id + "): User not found for this id :: " + id));
        
        user.setDeletedBy(null);
        user.setDeletedAt(null);
        
        return userRepository.save(user);
    }
	        
    /**
     * removes a specified user from the database permanently
     * 
     * @param id
     * @return void
     * @throws ResourceNotFoundException
     */
    public void removeUser(long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("UserService::removeUser(" + id + "): User not found for this id"));
        
        userRepository.delete(user);
    }
	    
    /**
     * get all Users
     * 
     * @return List<User>
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * get a <code>User</code> with the ID <code>id</code>
     * 
     * @param Long userID
     * @return Optional<User>
     */
    public Optional<User> findUserById(long userId) {
    	return userRepository.findById(userId);
    }
	    
    /**
     * find a user by Email case insensitive
     * 
     * @param String email									the email to found
     * @return List<User>									the found users
     */
    public List<User> findUserByEmail(String email) {
    	Criteria regex = Criteria.where("email").regex(
    			Pattern.compile(email, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)   			
    			);
    	
    	Query query = new Query().addCriteria(regex);
    	
    	return mongoOperations.find(query, User.class);
    }
	    
    /**
     * returns the users defined by the search
     * 
     * @param Query query									the search 
     * @return List<User>									the found users
     */
    public List<User> findByQuery(Query query) {
    	// e.g.
    	//
    	// Query query = new Query();
    	// query.addCriteria(Criteria.where('email').is("...");
    	//
    	// List<User> users = userService.findByQuery(query);
    	//
    	// Criteria regex = Criteria.where("email").regex("", "i");
    	// mongoOperations.find(new Query().addCriteria(regex), User.class);
    	//
    	return mongoOperations.find(query, User.class);
    }
    
    /**
     * add a field to user
     * 
     * @param User creator								the user who add this field
     * @param String name								the field name
     * @param FieldDescription fieldDescription			the field description
     * @throws ResourceNotFoundException 
     */
    /*
    public CustomField addField(@NotNull User creator, @NotBlank String name, @NotNull FieldDescription fieldDescription) throws ResourceNotFoundException {
    	return fieldService.createCustomField(creator, name, "USER", fieldDescription);
    }
    */
}
