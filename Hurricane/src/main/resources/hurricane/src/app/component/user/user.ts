/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		  0.1.4
 --------------------------------------------------------------------------------*/

 /**
  * this is the user class, which get as response from the server
  */
export interface User {
        id: number,
        firstname: string,    
        lastname: string,    
        email: string,    
        password: string,    
        blocked: boolean,    
        enabled: boolean,    
        pictogram: any,    
        communication: any[],    
        worktime: any[],
        downtime: any[],
        jobs: any[],
        company: any,
        createdAt: Date | null,
        createdBy: any | null,
        updatedAt: Date | null,
        updatedBy: any | null,
        deletedAt: Date | null,
        deletedBy: any | null,
}