package com.tmt.hurricane.user.repository;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2020 Thomas Meyer. License see license.txt
 * @version		0.0.1
 --------------------------------------------------------------------------------*/
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tmt.hurricane.user.model.User;

/**
 * the repository for projects
 * 
 * Optional<Job> findByName(String name);			find project by a name
 */
@Repository
public interface UserRepository extends MongoRepository<User, Long> {
	
	/**
	 * find user by a email
	 * 
	 * @param String							the email to find
	 * @reutrn Optional<User>					the found user or null
	 */
	Optional<User> findByEmail(String email);

	/**
	 * check if a user exists with the given email
	 * 
	 * @param boolean							true if a user exists
	 * @reutrn Optional<User>					the found user or null
	 */
	boolean existsByEmail(String email);
}
