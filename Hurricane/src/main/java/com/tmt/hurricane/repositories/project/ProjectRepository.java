package com.tmt.hurricane.repositories.project;
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

import com.tmt.hurricane.model.project.Project;

/**
 * the repository for projects
 * 
 * Optional<Job> findByName(String name);			find project by a name
 */
@Repository
public interface ProjectRepository extends MongoRepository<Project, Long> {
	
	/**
	 * find project by a name
	 * 
	 * @param String							the name to find
	 * @reutrn Optional<Company>				the found project or null
	 */
	Optional<Project> findByName(String name);
}
