import {Component, OnInit} from '@angular/core';
import {NgForm} from "@angular/forms";
import {User} from "../user/user";
import { UserService } from '../user/user.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {
  user: User = new User();
  userInfo: User = new User();
  constructor(private userService: UserService, private route: Router) { }

  role: string = ''
  ngOnInit(): void {

    if (sessionStorage.getItem('access_token') !== null && sessionStorage.getItem('access_token') !== undefined) {
      this.route.navigateByUrl('body')}
    this.userService.getUserInfo().subscribe(
      (data) => {
        this.userInfo = data;


      },
      (error) => {
        console.log(error);
      }
    );

  }
  login(myForm: NgForm) {
    console.log(myForm.value)
    this.userService.login(this.user.mail, this.user.password).subscribe(
      (response: any) => {
        console.log(response)
        // Login successful, store access_token and refresh_token in sessionStorage
        sessionStorage.setItem('access_token', response.access_token);
        sessionStorage.setItem('refresh_token', response.refresh_token);

        this.userService.getUserInfo().subscribe(
          (data) => {

            this.userInfo = data;

            console.log("id user: ",this.userInfo.idUser );


            sessionStorage.setItem('user_role', data.roles.name );
            if (data.roles.name === 'admin') {
              this.route.navigateByUrl('/admin/userlist')
            }
            if (data.roles.name === 'user') {
              this.route.navigateByUrl('/user/bodyuser')
            }
            if (data.roles.name === 'societe') {
              this.route.navigateByUrl('/user/bodyuser')
            }
          },
          (error) => {
            console.log(error);
          }
        );

      },
      (error) => {
        // Login failed, display error message to user
        console.error(error);
      })



  }
  test() {
    console.log("aaaaaaaaaaa")
    this.role = this.userInfo.roles.name
  }

}
