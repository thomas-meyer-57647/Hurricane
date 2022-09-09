package com.tmt.hurricane.repositories.communication.email;
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

import com.tmt.hurricane.model.communication.email.EMailTemplate;

/**
 * the repository for email templates
 * 
 * List<EMailTemplate> findByName(String name);			find all email templates by a name
 */
@Repository
public interface EMailTemplateRepository extends MongoRepository<EMailTemplate, Long> {
	
	/**
	 * find email templates by a name
	 * 
	 * @param String							the name to find
	 * @reutrn Optional<EMailTemplate>			the found email templates or null
	 */
	Optional<EMailTemplate> findByName(String name);
}
