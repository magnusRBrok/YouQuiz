import { makeAutoObservable } from "mobx";
import { UserModel } from "../model/user";

export class UserStoreImpl {
  private user: UserModel | null;

  constructor() {
    makeAutoObservable(this);
    this.user = this.getUser();
  }

  setUser(user: UserModel | null) {
    this.user = user;
  }

  getUser() {
    return this.user;
  }

  getFirstName() {
    return this.user?.first_name;
  }

  getUserId() {
    return this.user?.id;
  }
}

export const UserStore = new UserStoreImpl();
