import { Routes } from '@angular/router';
import { NgbdpaginationBasicComponent } from './pagination/pagination.component';
import { NgbdAlertBasicComponent } from './alert/alert.component';

import { NgbdDropdownBasicComponent } from './dropdown-collapse/dropdown-collapse.component';
import { NgbdnavBasicComponent } from './nav/nav.component';
import { BadgeComponent } from './badge/badge.component';
import { ButtonsComponent } from './buttons/buttons.component';
import { CardsComponent } from './card/card.component';
import { TableComponent } from './table/table.component';
import { UserCompactComponent } from './user/compact/usercompact.component';
import { UserListComponent } from './user/list/userlist.component';
import { UserMaskComponent } from './user/mask/usermask.component';


export const ComponentsRoutes: Routes = [
	{
		path: '',
		children: [
			// User Management
			{
				path: 'user/compact',
				component: UserCompactComponent
			},
			{
				path: 'user/list',
				component: UserListComponent
			},
			{
				path: 'user/mask',
				component: UserMaskComponent
			},
			{
				path: 'user/mask/:id',
				component: UserMaskComponent
			},
			/*
			{
				path: 'table',
				component: TableComponent
			},
			{
				path: 'card',
				component: CardsComponent
			},
			{
				path: 'pagination',
				component: NgbdpaginationBasicComponent
			},
			{
				path: 'badges',
				component: BadgeComponent
			},
			{
				path: 'alert',
				component: NgbdAlertBasicComponent
			},
			{
				path: 'dropdown',
				component: NgbdDropdownBasicComponent
			},
			{
				path: 'nav',
				component: NgbdnavBasicComponent
			},
			{
				path: 'buttons',
				component: ButtonsComponent
			}
			*/
		]
	}
];
