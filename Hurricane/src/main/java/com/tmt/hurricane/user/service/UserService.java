package com.tmt.hurricane.user.service;
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
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.tmt.hurricane.country.service.CountryService;
import com.tmt.hurricane.exceptions.DuplicateException;
import com.tmt.hurricane.exceptions.ResourceNotFoundException;
import com.tmt.hurricane.helper.database.service.SequenceGeneratorService;
import com.tmt.hurricane.services.field.FieldService;
import com.tmt.hurricane.user.model.User;
import com.tmt.hurricane.user.repository.UserRepository;

/**
 * this is the service for the user management
 * 
 * 	User createUser(User creator, User user) 						create a user
 *  final User updateUser(User updater, Long id, User user) 		update a user
 *  final User deleteUser(User deleter, long id) 					sets a specified user as deleted
 *  final User undeleteUser(long id)								sets a specified user as undeleted
 *  void removeUser(long id)										removes a specified user from the database permanently
 *  List<User> findAllUsers()										get all Users
 *  Optional<User> findUserById(long userId)						get a <code>User</code> with the ID <code>id</code>
 *  List<User> findUserByEmail(String email)						find a user by Email case insensitive
 *  boolean existsUserByEmail(String email)							exists a user by Email case insensitive
 *  List<User> findUserByQuery(Query query)							returns the users defined by the search
 *  existsUserByQuery(Query query)									exists a user by query
 * 
 */
@Service
public class UserService {
	
	private static Logger logger = LogManager.getLogger(CountryService.class);
	
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
	 * @throws ResourceNotFoundException 
	 * @throws DuplicateException 
	 */	
	public User createUser(User creator, User user) throws ResourceNotFoundException, DuplicateException {
		logger.debug("UserService::createUser(" + creator + ", " + user + ")");
		
		Preconditions.checkNotNull(user, "UserService::createUser(" + creator + ", " + user + "): The adding user must not be zero");
    	
    	if ( creator != null && !userRepository.existsById(creator.getId()) ) 
    		throw new ResourceNotFoundException("UserService::createUser(" + creator + ", " + user + "): Creator not found with the id :: " + creator.getId());

    	// This is an alternative solution, as @Indexed(unique=true) from the Email field does not seem to work.
    	if ( existsUserByEmail(user.getEmail()) )
    		throw new DuplicateException("UserService::createUser(" + creator + ", " + user + "): EMail (" + user.getEmail() +  ") allready exists");
    	
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
     * @throws	NullPointerException					if the updater or the user is zero 
     * @throws  DuplicateException 
     */
    public final User updateUser(User updater, Long id, User user) throws ResourceNotFoundException, DuplicateException {
    	logger.debug("UserService::updateUser(" + updater + ", " + id + ", " + user + ")");
    	
    	Preconditions.checkNotNull(updater, "UserService::updateUser(User updater: " + updater + ", Long id: " + id + ", User user: " + user + "): The updater user must not be zero");
		Preconditions.checkNotNull(user, "UserService::updateUser(User updater: " + updater + ", Long id: " + id + ", User user: " + user + "): The user to be updated must not be null");

        if ( !userRepository.existsById(updater.getId()) )
    		throw new ResourceNotFoundException("UserService::updateUser(User updater: " + updater + ", Long id: " + id + ", User user: " + user + "): Updater not found for this id :: " + updater.getId());
		
        User foundUser = userRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("UserService::updateUser(User updater: " + updater + ", Long id: " + id + ", User user: " + user + "): User not found for this id :: " + id));

        // find a user with not id but with the email user
    	List<User> userExists = this.findUserByEmail(user.getEmail());
    	
    	if ( userExists.size() > 0 && userExists.get(0).getId() != id ) {
    		throw new DuplicateException("UserService::updateUser(User updater: " + updater + ", Long id: " + id + ", User user: " + user + "): Email already exists");
    	}
        
        foundUser.setUpdatedBy(updater);
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
     *      	NullPointerException			if the updater or the user is null
     */
    public final User deleteUser(User deleter, long id) throws ResourceNotFoundException  {
    	logger.debug("UserService::deleteUser(" + deleter + ", " + id + ")");

    	Preconditions.checkNotNull(deleter, "UserService::deleteUser(User deleter: " + deleter + ", long id: " + id + "): The deleter user must not be zero");

    	if ( !userRepository.existsById(deleter.getId()) ) 
    		throw new ResourceNotFoundException("UserService::deleteUser(User deleter: " + deleter + ", long id: " + id + "): The deleter not found");
    	
        User user = userRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("UserService::deleteUser(User deleter: " + deleter + ", long id: " + id + "): The user not found with the ID: " + id));
        
        user.setDeletedBy(deleter);
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
    	logger.debug("UserService::undeleteUser(" + id + ")");
    	
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
    @Transactional
    public void removeUser(long id) throws ResourceNotFoundException {
    	logger.debug("UserService::removeUser(" + id + ")");

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
    	logger.debug("UserService::findAllUsers()");

        return userRepository.findAll();
    }

    /**
     * get a <code>User</code> with the ID <code>id</code>
     * 
     * @param Long userID
     * @return Optional<User>
     */
    public Optional<User> findUserById(long userId) {
    	logger.debug("UserService::findUserById(" + userId + ")");

    	return userRepository.findById(userId);
    }
	    
    /**
     * find a user by Email case insensitive
     * 
     * @param String email									the email to found
     * @return List<User>									the found users
     */
    public List<User> findUserByEmail(String email) {
    	logger.debug("UserService::findUserByEmail(" + email + ")");

    	Criteria regex = Criteria.where("email").regex(
    			Pattern.compile("\\b" + email + "\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)   			
    			);
    	
    	Query query = new Query().addCriteria(regex);
    	
    	return mongoOperations.find(query, User.class);
    }

    /**
     * exists a user by Email case insensitive
     * 
     * @param String email								the email to found
     * @return boolean									
     */
    public boolean existsUserByEmail(String email) {
    	logger.debug("UserService::existsUserByEmail(" + email + ")");

    	Criteria regex = Criteria.where("email").regex(
    			Pattern.compile("\\b" + email + "\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)   			
    			);
    	
    	Query query = new Query().addCriteria(regex);
    	
    	return mongoOperations.exists(query, User.class);
    }
    
    /**
     * returns the users defined by the search
     * 
     * @param Query query									the search 
     * @return List<User>									the found users
     */
    public List<User> findUserByQuery(Query query) {
    	logger.debug("UserService::findUserByQuery(" + query + ")");

    	return mongoOperations.find(query, User.class);
    }

    /**
     * exists a user by query
     * 
     * @param Query query								the search 
     * @return boolean									the found users
     */
    public boolean existsUserByQuery(Query query) {
    	logger.debug("UserService::existsByQuery(" + query + ")");

    	return mongoOperations.exists(query, User.class);
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
