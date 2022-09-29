/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		  0.1.4
 --------------------------------------------------------------------------------*/
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

/**
 * this is the mask for the users
 */
@Component({
  selector: 'app-usermask',
  templateUrl: './usermask.component.html',
  styleUrls: ['./usermask.component.scss']
})
export class UserMaskComponent implements OnInit {

  user: User = {
    id: 1,
    firstname: "Thomas",    
    lastname: "Meyer",    
    email: "thomas.meyer@gmail.com",    
    password: "abcd",    
    blocked: true,    
    enabled: true,    
    pictogram: null,    
    communication: [],    
    worktime: [],
    downtime: [],
    jobs: [],
    company: null,
    createdAt: new Date(),
    createdBy: null,
    updatedAt: new Date(),
    updatedBy: null,
    deletedAt: new Date(),
    deletedBy: null,
  };
  message : string = 'test';
  confirmed_password: string = "";

  constructor(
    private userService: UserService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.getUser( this.route.snapshot.params['id'] );
  }

  /**
   * get the list of users from the server
   * 
   * @param number              the id to get
   * @return User               the found user or null
   */
   getUser(id: number) {
    this.userService.getUser(id).subscribe(
      response => {
        this.user = response;
        },
      error => this.handleErrorResponse(error)
    );
  }

  /**
   * save the user
   */
  saveUser() {

  }
  
  /**
   * handling the response from getUserList
   * 
   * @param response 
   */
  handleSuccessfulResponse(response: User) {
    console.log("Rückgabe ist: " + response.firstname);
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
