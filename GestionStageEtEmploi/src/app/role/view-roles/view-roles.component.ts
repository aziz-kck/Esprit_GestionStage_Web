import {Component, OnInit} from '@angular/core';
import {Role} from "../role";
import {RoleService} from "../role.service";
@Component({
  selector: 'app-view-roles',
  templateUrl: './view-roles.component.html',
  styleUrls: ['./view-roles.component.css']
})
export class ViewRolesComponent implements OnInit {
  roles!:Role[];
  constructor(private roleService:RoleService) { }

  ngOnInit(): void {
    this.getAllRoles();
  }


  private getAllRoles(){
    this.roleService.getRoles().subscribe(res => {
      this.roles = res;
    })
    console.log(this.roles);
  }

  deleteRole(id: number) {
    this.roleService.deleteRoles(id).subscribe(() => {
      this.roles = this.roles.filter(role => role.id !== id);
    });
  }
}
