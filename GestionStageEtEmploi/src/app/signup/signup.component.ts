import {Component, OnInit} from '@angular/core';
import {Role} from "../role/role";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../user/user";
import {RoleService} from "../role/role.service";
import {UserService} from "../user/user.service";
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";

export function matchPasswordValidator(g: FormGroup) {
  const password = g.get('password')?.value;
  const verifpassword = g.get('verifpassword')?.value;

  if (password !== verifpassword) {
    return { mismatch: true };
  } else {
    return null;
  }

}
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  user: User = new User();
  passwordForm: FormGroup;
  roles: Role[] = [];
  selectedRole!: Role;
  formcaptcha:FormGroup;
  constructor(
    private userService: UserService,
    private route: Router,
    private fb: FormBuilder,
    private roleService: RoleService
  ) {
    this.passwordForm = this.fb.group(
      {
        password: ['', Validators.required],
        verifpassword: ['', Validators.required],
        roles: [this.selectedRole, Validators.required] // Set selectedRole as initial value
      },
      { validator: matchPasswordValidator }
    );
    this.formcaptcha = this.fb.group({
      recaptcha: ['', Validators.required]
    })

  }

  ngOnInit(): void {
    if (sessionStorage.getItem('access_token') !== null && sessionStorage.getItem('access_token') !== undefined) {
      this.route.navigateByUrl('body')}
    this.getRoles();
    localStorage.removeItem('_grecaptcha')
  }
  captchaResponse!: string;

  resolved(captchaResponse: string) {
    console.log(`Resolved captcha with response ${captchaResponse}`);
    // Do something with the captchaResponse, such as sending it to your server
  }
  msg:string=''
  getRoles(): void {
    this.roleService.getRoles().subscribe(
      (response: Role[]) => {
        this.roles = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  saveUser() {
    console.log("here");
    console.log(this.selectedRole);
    this.user = {
      idUser: 0,
      username: this.user.username,
      mail: this.user.mail,
      password: this.user.password,
      verifPassword: this.user.verifPassword,
      roles: this.selectedRole,
      roleName: "Default"
    };

      this.userService.addUsers(this.user).subscribe(
        (response) => {
          if (response === false) {
            // Show a message
            this.msg = "Le mail existe deja";
          } else {
            // Redirect to the sign in page
            this.route.navigateByUrl('/signin');
          }
        },
        (error) => {
          console.log(error);
        }
      );

  }

}
