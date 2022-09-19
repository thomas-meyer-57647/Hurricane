package com.tmt.hurricane.repositories.field;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tmt.hurricane.model.fields.FieldDescription;
import com.tmt.hurricane.model.fields.FieldValue;

/**
 * the repository for a field value
 *
 * Optional<FieldDescription> findByName(String name)				find by name
 * List<CustomField> findByClassname(String classname)				find all fields by a classname	
 */
@Repository
public interface FieldValueRepository extends MongoRepository<FieldValue, Long> {

	/**
	 * find by reference id
	 * 
	 * @param String							the reference id
	 * @reutrn Optional<FieldDescription>		the found field or null
	 */
	Optional<FieldDescription> findByRefID(int id);
}
