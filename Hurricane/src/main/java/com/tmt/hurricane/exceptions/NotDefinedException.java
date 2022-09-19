package com.tmt.hurricane.exceptions;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/

/**
 * this is an exception thrown when a non-null passing parameter is null
 */
public class NotDefinedException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotDefinedException(String message){
        super(message);
    }
}
