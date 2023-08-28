import {Component, OnInit} from '@angular/core';
declare const $: any;
declare interface RouteInfo {
  path: string;
  title: string;
  class: string;
}
export const ROUTES: RouteInfo[] = [
  { path: '/admin/rolelist', title: 'Role List',  class: '' },
  { path: '/admin/userlist', title: 'User List',  class: '' },



];
@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  menuItems!: any[];

  constructor() { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    console.log(this.menuItems)
  }

}
