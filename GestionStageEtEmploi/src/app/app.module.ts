import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarAdminComponent } from './navbar-admin/navbar-admin.component';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { SidebarComponent } from './sidebar-admin/sidebar.component';
import { FooterComponent } from './footer/footer.component';
import { BodyAdminComponent } from './body-admin/body-admin.component';
import { TemplateAdminComponent } from './template-admin/template-admin.component';
import { RoleComponent } from './role/role.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { ViewRolesComponent } from './role/view-roles/view-roles.component';
import { HttpClientModule } from '@angular/common/http';
import { UserComponent } from './user/user.component';
import { UpdateprofilComponent } from './updateprofil/updateprofil.component';
import { UpdatepasswordComponent } from './updatepassword/updatepassword.component';
import { TemplateUserComponent } from './template-user/template-user.component';
import { SidebarUserComponent } from './sidebar-user/sidebar-user.component';
import { BodyUserComponent } from './body-user/body-user.component';
import { RedirectionComponent } from './redirection/redirection.component';
import { ChangemdpComponent } from './changemdp/changemdp.component';
// import { RecaptchaModule } from "ng-recaptcha";


@NgModule({
  declarations: [
    AppComponent,
    NavbarAdminComponent,
    SigninComponent,
    SignupComponent,
    SidebarComponent,
    FooterComponent,
    BodyAdminComponent,
    TemplateAdminComponent,
    RoleComponent,
    ViewRolesComponent,
    UserComponent,
    UpdateprofilComponent,
    UpdatepasswordComponent,
    TemplateUserComponent,
    SidebarUserComponent,
    BodyUserComponent,
    RedirectionComponent,
    ChangemdpComponent,
  ],
    imports: [
        HttpClientModule,
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        ReactiveFormsModule,
      // RecaptchaModule

    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
