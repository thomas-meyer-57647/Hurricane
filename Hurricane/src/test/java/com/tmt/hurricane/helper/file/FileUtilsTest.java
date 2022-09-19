package com.tmt.hurricane.helper.file;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * this is the test for the helper class FileUtils
 */
class FileUtilsTest {

	/**
	 * test get get resource directory
	 * @throws IOException 
	 */
	@Test
	void testGetResourceDirectory() throws IOException {
		FileUtils.getResourceDirectory();
	}

}
