/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		  0.1.4
 --------------------------------------------------------------------------------*/
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user';

/**
 * this is the user service class
 */
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  /**
   * get the user list from the backend
   */
  getUserList() {
    return this.http.get<User[]>('http://localhost:8080/api/v1/user/list');
  }

  /**
   * get a spezified user
   * 
   * @param number          the id
   */
   getUser(id: number) {
    return this.http.get<User>(`http://localhost:8080/api/v1/user/${id}`);
  }
  
  /**
   * delete a spezified user
   * 
   * @param number          the id
   */
  deleteUser(id: number) {
    return this.http.delete(`http://localhost:8080/api/v1/user/delete/${id}`);
  }
}
