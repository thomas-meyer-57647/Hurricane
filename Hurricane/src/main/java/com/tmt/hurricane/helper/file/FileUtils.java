package com.tmt.hurricane.helper.file;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @package     database
 * @version		0.1.4
 --------------------------------------------------------------------------------*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.core.io.ClassPathResource;
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
	 * @throws IOException 
	 */
	public static final File getResourceDirectory() throws IOException {
		return new ClassPathResource("").getFile();
//		return ResourceUtils.getFile("classpath:");		
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
	public static final String[]  getMatchedFilesFromDirecotry(File directory, String wildcard) throws NotDefinedException {
		if ( directory == null )
			throw new NotDefinedException("FileUtils::getMatchedFiles(" + directory + "): Not defined file");
		 
		if ( !directory.isDirectory() )
			throw new NotDefinedException("FileUtils::getMatchedFiles(" + directory + "): This is not a directory");

		return directory.list( new  WildcardFileFilter(wildcard));
	}

}
