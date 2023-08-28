import {Component, OnInit} from '@angular/core';
import {UserService} from "../user/user.service";
import {RoleService} from "../role/role.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar-admin.component.html',
  styleUrls: ['./navbar-admin.component.css']
})
export class NavbarAdminComponent implements OnInit{
  logout() {
    sessionStorage.removeItem('access_token');
    sessionStorage.removeItem('refresh_token');
    sessionStorage.removeItem('user_role');
    this.route.navigate(['signin']);

  }
  constructor(private route:Router) { }

  ngOnInit(): void {
  }
}
