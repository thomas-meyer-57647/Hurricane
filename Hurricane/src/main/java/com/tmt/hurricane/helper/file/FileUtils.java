package com.tmt.hurricane.helper.file;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2020 Thomas Meyer. License see license.txt
 * @package     database
 * @version		0.1.3
 --------------------------------------------------------------------------------*/
import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.io.filefilter.WildcardFileFilter;

import org.springframework.util.ResourceUtils;

import com.tmt.hurricane.exceptions.NotDefinedException;

/**
 * a helper class for file handling
 */
public class FileUtils {
	
	/**
	 * return the file to the resource directory
	 * 
	 * @return File
	 * @throws FileNotFoundException 
	 */
	static final File getResourceDirectory() throws FileNotFoundException {
		return ResourceUtils.getFile("classpath:application.properties");		
	}

	/**
	 * return a list of matched <code>wildcard</code> files from the given directory
	 * <code>directory</code> 
	 * 
	 * @param File								the directory to show
	 * @param wildcard							the matching
	 * @return Collection<File> 
	 * @throws NotDefinedException 
	 * @throws FileNotFoundException 
	 */
	static final String[]  getMatchedFilesFromDirecotry(File directory, String wildcard) throws NotDefinedException {
		if ( directory == null )
			throw new NotDefinedException("FileUtils::getMatchedFiles(" + directory + "): Not defined file");
		 
		if ( !directory.isDirectory() )
			throw new NotDefinedException("FileUtils::getMatchedFiles(" + directory + "): This is not a directory");

		return directory.list( new  WildcardFileFilter(wildcard));
	}

}
