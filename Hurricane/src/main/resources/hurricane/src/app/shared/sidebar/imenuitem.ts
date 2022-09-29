/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/

/**
 * the menu item for the sidebar
 */
export interface IMenuItem {
  path: string;             // path to the component
  title: string;            // the menu title
  icon: string;             // an associated icon
  class: string;            // the class
  extralink: boolean;       // external link
  submenu: IMenuItem[];     // Submenu
}
