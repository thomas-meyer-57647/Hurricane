package com.tmt.hurricane.repositories.field;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2020 Thomas Meyer. License see license.txt
 * @version		0.0.1
 --------------------------------------------------------------------------------*/

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tmt.hurricane.model.fields.CustomField;

/**
 * the repository for a custom field
 *
 * Optional<FieldDescription> findByName(String name)				find by name
 * List<CustomField> findByClassname(String classname)				find all fields by a classname	
 */
@Repository
public interface CustomFieldRepository extends MongoRepository<CustomField, Long> {
	
	/**
	 * find all fields by a classname
	 * 
	 * @param String							the classname to find
	 * @reutrn List<CustomField>				the found custom fields or null
	 */
	List<CustomField> findByClassname(String classname);
	
	/**
	 * find all fields by a name and classname
	 * 
	 * @param String							the name to find
	 * @param String							the classname to find
	 * @reutrn List<CustomField>				the found custom fields or null
	 */
	List<CustomField> findByNameAndClassname(String name, String classname);
	
}
