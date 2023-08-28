import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Role} from "./role";

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private apiServerUrl=environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  public getRoles(): Observable<Role[]> {
    const url = `${this.apiServerUrl}user/allrole`; // remove the extra slash
    console.log("aaaa" ,url);
    return this.http.get<Role[]>(url);
  }
  public getRoleById(id: number) {
    return this.getRoles().pipe(
      map((roles: Role[]) => {
        const foundRole = roles.find(role => role.id === id);
        return foundRole ;
      })
    );
  }

  addRole(role: Role): Observable<void> {
    console.log("rooooooole", role)
    return this.http.post<void>(`${this.apiServerUrl}admin/ajouterrole`, role)
  }


  public deleteRoles(roleId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}admin/deleterole/${roleId}`);
  }
}
