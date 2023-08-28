import {Component, OnInit} from '@angular/core';
import {Role} from "../role/role";
import {User} from "./user";
import {RoleService} from "../role/role.service";
import {UserService} from "./user.service";
import {HttpErrorResponse} from "@angular/common/http";
import {NgForm} from "@angular/forms";
declare var $: any;
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  errorMessage: string = '';
  public editEmployee!: User;
  public deleteEmployee!: User;

  apiServerUrl = 'http://localhost:8080';
  ngOnInit(): void {
    this.getUsers();
    this.getRoles();

  }
  selectedRole!: Role;
  userUpdated!: User;

  public users: User[] = [];
  public roles: Role[] = [];
  constructor(private userService: UserService, private roleService: RoleService) { }

  // ...

  public getUsers(): void {
    const url = `${this.apiServerUrl}/admin/all`; // construct the URL
    this.userService.getUsers(url).subscribe(
      (response: User[]) => {
        this.users= response;
        console.log(this.users)
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    )

  }
  public getRoles(): void {
    this.roleService.getRoles().subscribe(
      (response: Role[]) => {
        this.roles = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }

  onAddEmployee(addForm: NgForm) {
    const role1: Role = {
      id: this.selectedRole.id,
      name: this.selectedRole.name
    };
    const user: User = {
      idUser: 0,
      username: addForm.value.name,
      mail: addForm.value.email,
      password: addForm.value.password,
      verifPassword: "",
      roles: role1,
      roleName: "Default"
    };

    this.userService.addUsers(user).subscribe(
      (response) => {
        if (response === false) {
          // Show a message
          this.errorMessage = "Le mail existe deja";
        } else {
          // Redirect to the sign in page
          this.errorMessage = "Utilisateur ajouté avec succés";
        }
      },
      (error) => {
        console.log(error);
      }
    );


  }


  public onUpdateEmloyee(employee: User): void {
    const role2: Role = {
      id: this.selectedRole.id,
      name: this.selectedRole.name
    };
    const user1: User = {
      username: employee.username,
      mail: employee.mail,
      password: employee.password,
      verifPassword: "",
      idUser: this.editEmployee.idUser,

      roles: role2,
      roleName: "Default"
      //roles: this.roles.find(roles => roles.id === addForm.value.role)

    };
    employee.idUser=2;
    employee.roles=role2;

    this.userService.updateUsers(user1).subscribe(
      (response: User) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteEmployee(employeeId: number): void {

    this.userService.deleteUsers(employeeId).subscribe(
      (response: void) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  public searchEmployees(key: string): void {
    console.log(key);
    const results: User[] = [];
    for (const employee of this.users) {
      if (employee.username.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || employee.mail.toLowerCase().indexOf(key.toLowerCase()) !== -1
      ) {
        results.push(employee);
      }
    }
    this.users = results;
    if (results.length === 0 || !key) {

      this.getUsers();

    }
  }

  public onOpenModal(employee: User, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {

      button.setAttribute('data-target', '#addEmployeeModal');
    }
    if (mode === 'edit') {
      this.editEmployee = employee;
      button.setAttribute('data-target', '#updateEmployeeModal');
    }
    if (mode === 'delete') {
      this.deleteEmployee = employee;
      button.setAttribute('data-target', '#deleteEmployeeModal');
    }
    // attach event handler to the button
    button.addEventListener('click', () => {
      $(button.getAttribute('data-target')).modal('show');
    });

    container?.appendChild(button);
    button.click();
  }

}
