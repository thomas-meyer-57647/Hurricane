package com.tmt.hurricane.repositories.company;
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

import com.tmt.hurricane.model.company.Company;

/**
 * the repository for company
 * 
 * 	Optional<Company> findByName(String name);			find company by a name
 */
@Repository
public interface CompanyRepository extends MongoRepository<Company, Long> {
	
	/**
	 * find company by a name
	 * 
	 * @param String							the name to find
	 * @reutrn Optional<Company>				the found company or null
	 */
	Optional<Company> findByName(String name);

}
