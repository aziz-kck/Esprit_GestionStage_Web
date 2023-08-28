import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {BodyAdminComponent} from "./body-admin/body-admin.component";
import {SigninComponent} from "./signin/signin.component";
import {TemplateAdminComponent} from "./template-admin/template-admin.component";
import {SignupComponent} from "./signup/signup.component";
import {ViewRolesComponent} from "./role/view-roles/view-roles.component";
import {RoleComponent} from "./role/role.component";
import {UserComponent} from "./user/user.component";
import {UpdateprofilComponent} from "./updateprofil/updateprofil.component";
import {TemplateUserComponent} from "./template-user/template-user.component";
import {BodyUserComponent} from "./body-user/body-user.component";
import {ChangemdpComponent} from "./changemdp/changemdp.component";
import {UpdatepasswordComponent} from "./updatepassword/updatepassword.component";
import {RedirectionComponent} from "./redirection/redirection.component";

const routes: Routes = [
  {  path:'admin',
    component:TemplateAdminComponent,
    children:[
      { path: 'rolelist', component:ViewRolesComponent},
      { path: 'createrole', component:RoleComponent},
      {path: 'userlist', component:UserComponent},
      {path: 'body', component:BodyAdminComponent},
      {path: 'updateprofil', component:UpdateprofilComponent},

    ],

  },{
    path:'signin',
    component: SigninComponent,
    children:[
      // {path: 'signin', component:SigninComponent},
    ]
  },{
    path:'signup',
    component: SignupComponent,
    children:[
      // {path: 'signin', component:SigninComponent},
    //  {path: 'signup', component:SignupComponent},
    ]
  }
  ,{
    path:'user',
    component: TemplateUserComponent,
    children:[
       {path: 'bodyuser', component:BodyUserComponent},
      {path:'forgetPAssword',component:ChangemdpComponent},
      { path: 'updatepPassword/:token/:email', component: UpdatepasswordComponent },

      //  {path: 'signup', component:SignupComponent},
    ]
  },{
    path:'',
    component: ChangemdpComponent,
    children:[
      {path:'forgetPAssword',component:ChangemdpComponent},
      {path: 'redirection', component:RedirectionComponent},
      //  {path: 'signup', component:SignupComponent},
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
