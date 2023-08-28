import {Role} from "../role/role";

export class User{
  idUser!: number;
  username!: string;
  mail!: string;
  password!: string;
  verifPassword!: string;
  roles!: Role;
  roleName!: string;
}
