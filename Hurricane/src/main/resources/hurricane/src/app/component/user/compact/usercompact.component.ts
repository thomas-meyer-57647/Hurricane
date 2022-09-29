/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		  0.1.4
 --------------------------------------------------------------------------------*/
import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';

/**
 * this is the list of the users
 */
@Component({
  selector: 'app-usercompact',
  templateUrl: './usercompact.component.html',
  styleUrls: ['./usercompact.component.scss']
})
export class UserCompactComponent implements OnInit {

  constructor(
    private userService: UserService
  ) { }

  ngOnInit(): void {
  }

  /**
   * get the list of users from the server
   */
   getUserList() {
    console.log( this.userService.getUserList() );
    this.userService.getUserList().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
  }

  /**
   * handling the response from getUserList
   * 
   * @param response 
   */
  handleSuccessfulResponse(response: any) {
    console.log("Rückgabe ist: " + response);
  }

  /**
   * handling the error response from getUserList
   * 
   * @param response 
   */
   handleErrorResponse(error: any) {
    console.log(error.error.message);
  }

}
