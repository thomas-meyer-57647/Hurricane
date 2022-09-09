package com.tmt.hurricane.repositories.field;
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

import com.tmt.hurricane.model.fields.FieldDescription;

/**
 * the repository for field description
 *
 * Optional<FieldDescription> findByName(String name)				find by name
 */
@Repository
public interface FieldDescriptionRepository extends MongoRepository<FieldDescription, Long> {

	/**
	 * find by name
	 * 
	 * @param String							the name to find
	 * @reutrn Optional<FieldDescription>		the found field or null
	 */
	Optional<FieldDescription> findByName(String name);
	
}
