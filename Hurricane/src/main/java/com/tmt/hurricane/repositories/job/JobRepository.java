package com.tmt.hurricane.repositories.job;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2020 Thomas Meyer. License see license.txt
 * @version		0.0.1
 --------------------------------------------------------------------------------*/
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tmt.hurricane.model.jobs.Job;

/**
 * the repository for jobs
 * 
 * 	Optional<Job> findByName(String name);			find company by a name
 */
@Repository
public interface JobRepository extends MongoRepository<Job, Long> {
}
