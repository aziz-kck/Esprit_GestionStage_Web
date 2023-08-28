import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {User} from "./user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiServerUrl=environment.apiBaseUrl;
  constructor(private http: HttpClient) { }
  productURL = "http://localhost:8080/";

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Bearer' + ' ' + sessionStorage.getItem('access_token')
    })

  };

  getRolesfromspring() {

    return this.http.get<string>("http://localhost:8080/roles",this.httpOptions)
  }
  role:any
  async getRoles(): Promise<string> {
    try {
      const response = await this.getRolesfromspring().toPromise();
      this.role = response;
      return this.role[0];
    } catch (error) {
      console.log(error);
      return 'admin';
    }
  }
  async roleMatch(allowedRoles: any): Promise<boolean> {
    let isMatch = false;
    const userRoles = await this.getRoles();
    console.log('role1');
    console.log(userRoles);
    console.log('role2');
    console.log(allowedRoles[0]);
    if (userRoles != null && userRoles) {
      if (userRoles === allowedRoles[0]) {
        isMatch = true
        console.log(isMatch)
        console.log(isMatch)
        return isMatch
      } else {
        return isMatch
      }
    }
    return isMatch
  }

  clear() {
    sessionStorage.clear()
  }
  getToken(): any {
    const token= sessionStorage.getItem('access_token');

    return token
  }

  public checkEmailExists(email: String): Observable<User[]> {
    console.log("email", email);

    return this.http.get<User[]>('http://localhost:8080/admin/all');
  }

  public getUsers(url: string): Observable<User[]> {
    return this.http.get<User[]>(url);
  }

  public addUsers(user: User): Observable<boolean> {
    console.log("service", user);
    return this.http.post<boolean>('http://localhost:8080/user/add', user).pipe(
      catchError((error: any) => {
        console.log("hihihi", error);
        return throwError(error);
      })
    );
  }





  public updateUsers(user: User): Observable<User> {
    console.log("update : ", user);

    return this.http.put<User>(`${this.apiServerUrl}user/update`, user);

  }

  public deleteUsers(userId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}admin/delete/${userId}`);
  }
  login(email: string, password: string): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded'
    });

    const body = new HttpParams()
      .set('email', email)
      .set('password', password);
    console.log(this.httpOptions, body, headers)
    return this.http.post('http://localhost:8080/login', body.toString(), { headers });
  }

  sendEmailpassword(email: string, user: any) {
    return this.http.post("http://localhost:8080/SendEmailForgetpassword/" + email, user)
  }

  changePAssword(email: string, token: string, password: string) {
    return this.http.get(" http://localhost:8080/email/reset/" + token + "/" + email + "/" + password)
  }



  getUserInfo(): Observable<User> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem('access_token')}`);
    return this.http.get<User>('http://localhost:8080/session', { headers });
  }
  getUserbyemail(email: string) {
    return this.http.get<User>('http://localhost:8080/AfficherUserByemail/' + email);
  }

  public addUsersMicrosoft(user: User): Observable<User> {
    // Extract the role id
    console.log("service" ,user);
    return this.http.post<User>(`${this.apiServerUrl}user/add`,user);

  }
}
