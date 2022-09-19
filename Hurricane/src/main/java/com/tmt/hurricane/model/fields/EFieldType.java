package com.tmt.hurricane.model.fields;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/

/**
 * an enumration of field type
 *
 * 	TEXT,					Text
 *	NUMBER,					an Integer or Float Number
 *	BOOLEAN,				a boolean
 *	RADIOBUTTONS,			a list of Radiobuttons (Multiple)
 *	COMBOBOX,				a combox - single value
 *	LIST					a list with multiple values
 */
public enum EFieldType {
	TEXT,					// Text
	NUMBER,					// an Integer or Float Number
	BOOLEAN,				// a boolean
	RADIOBUTTONS,			// a list of Radiobuttons (Multiple)
	COMBOBOX,				// a combox - single value
	LIST					// a list with multiple values
}
