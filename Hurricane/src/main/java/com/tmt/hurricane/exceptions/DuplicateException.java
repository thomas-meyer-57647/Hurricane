package com.tmt.hurricane.exceptions;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/

/**
 * This is an exception thrown when there is an invalid duplicate specified
 */
public class DuplicateException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DuplicateException(String message){
        super(message);
    }

}
