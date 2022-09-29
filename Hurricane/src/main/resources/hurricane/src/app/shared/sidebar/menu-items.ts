/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		  0.1.4
 --------------------------------------------------------------------------------*/
import { IMenuItem } from './imenuitem';

/**
 * this is the description of the menu of the sidebar
 */
export const MENUITEM: IMenuItem[] = [
 
  {
    path: '/dashboard',
    title: 'Dashboard',
    icon: 'bi bi-speedometer2',
    class: '',
    extralink: false,
    submenu: []
  },

  {                                 // Users Management
    path: '/component/user/list',
    title: 'Users',
    icon: 'bi bi-people',
    class: '',
    extralink: false,
    submenu: []
  },
/*
  {
    path: '/component/alert',
    title: 'Alert',
    icon: 'bi bi-bell',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/badges',
    title: 'Badges',
    icon: 'bi bi-patch-check',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/buttons',
    title: 'Button',
    icon: 'bi bi-hdd-stack',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/card',
    title: 'Card',
    icon: 'bi bi-card-text',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/dropdown',
    title: 'Dropdown',
    icon: 'bi bi-menu-app',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/pagination',
    title: 'Pagination',
    icon: 'bi bi-dice-1',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/nav',
    title: 'Nav',
    icon: 'bi bi-pause-btn',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/table',
    title: 'Table',
    icon: 'bi bi-layout-split',
    class: '',
    extralink: false,
    submenu: []
  },
*/  
  {
    path: '/about',
    title: 'About',
    icon: 'bi bi-people',
    class: '',
    extralink: false,
    submenu: []
  }  
];
