import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { IMenuItem } from './imenuitem';     // Menu definition
import { MENUITEM } from './menu-items';           // Menu deklaration


@Injectable({
    providedIn: 'root'
})
export class VerticalSidebarService {

    public screenWidth: any;
    public collapseSidebar: boolean = false;
    public fullScreen: boolean = false;

    MENUITEMS: IMenuItem[] = MENUITEM;

    items = new BehaviorSubject<IMenuItem[]>(this.MENUITEMS);

    constructor() {
    }
}
