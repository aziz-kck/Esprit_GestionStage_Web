import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import {Role} from "./role";
import {RoleService} from "./role.service";

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit {
  role: Role = { id: 0, name: '' };
  roles: Role[] = [];

  constructor(private roleService: RoleService, private router: Router) {}

  ngOnInit(): void {
    this.loadRoles();
  }

  loadRoles() {
    this.roleService.getRoles().subscribe(
      (data) => {
        this.roles = data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  onSubmit() {
    this.roleService.addRole(this.role).subscribe(
      () => {
        console.log('Role added successfully');
        this.router.navigateByUrl('admin/rolelist');
      },
      (error) => {
        console.log(error);
      }
    );
  }

  viewRoles() {
    this.router.navigateByUrl('admin/rolelist');
  }

}
