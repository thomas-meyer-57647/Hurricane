/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		  0.1.4
 --------------------------------------------------------------------------------*/
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

/**
 * this is the list of the users
 */
@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.scss']
})
export class UserListComponent implements OnInit {

  users: Array<User> = [];
  message : string = '';

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getUserList();
  }

  /**
   * get the list of users from the server
   */
  getUserList() {
    this.userService.getUserList().subscribe(
      response => {
              this.users = response;
      },
      error => this.handleErrorResponse(error)
    );
  }

  /**
   * handling the error response from getUserList
   * 
   * @param response 
   */
   handleErrorResponse(error: any) {
    console.log(error.error.message);
  }

  /**
   * delete a user
   * 
   * @param response 
   */
  deleteUser(id: number) {
    this.userService.deleteUser(id).subscribe(
      response => {
        this.message = `Delete of User ${id} successful`;
        this.getUserList();
      }
    );
  }

  /**
   * edit a user
   * 
   * @param number          the id to get
   *  
   */
  editUser(id: number) {
    this.router.navigate(["/component/user/mask", id]); 
  }
  
}
