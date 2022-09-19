package com.tmt.hurricane.language.service;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @package     database
 * @version		0.1.4
 --------------------------------------------------------------------------------*/
import org.springframework.stereotype.Service;

import com.tmt.hurricane.exceptions.NotDefinedException;
import com.tmt.hurricane.helper.file.FileUtils;

/**
 * the service class for languages
 */
@Service
public class LanguageService {

	public static final String PRE_FILENAME = "messages";
	public static final String FILE_EXTENSION = "properties";
	
	/**
	 * get all available language files
	 * @throws FileNotFoundException 
	 * @throws NotDefinedException 
	 */
/*	
	public String[] getAllAvailableLanuageFiles() throws FileNotFoundException, NotDefinedException {
		
		return FileUtils.getMatchedFilesFromDirecotry(
					FileUtils.getResourceDirectory(),
					PRE_FILENAME + "*." + FILE_EXTENSION
				);
	}	
*/
	
	/**
	 * get all available languages codes
	 * 
	 * @throws FileNotFoundException 
	 * @throws NotDefinedException 
	 */
/*	
	public final List<String> getAllAvailableLanguageCodes() throws FileNotFoundException, NotDefinedException {
		String[] languageFiles = getAllAvailableLanuageFiles();
		int startindex = PRE_FILENAME.length();
		int file_extension_length = FILE_EXTENSION.length();
		List<String> languages = new ArrayList<String>();
		
		for (int idx=0; idx < languageFiles.length; idx++ ) {
			languages.add(
					languageFiles[idx].substring(
							startindex, 
							languageFiles[idx].length() - file_extension_length - 1)
					.toUpperCase()
			);
		}
		
		return languages;
	}
*/	
}
