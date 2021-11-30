import { makeAutoObservable } from "mobx";
import { genUserClient } from "../services/apiClients";
import { UserStore } from "./userStore";

export class AuthStoreImpl {
  private authenticated = false;

  // components that are "listening" to this AuthStore
  // will re-render because of the makeAutoObservable method called in the constructor.
  constructor() {
    makeAutoObservable(this);

    // Check for existing access-token
    this.checkAuth().then((res) => {
      this.authenticated = res;

      console.log("check", this.authenticated);

      // if existing access-token then we check if its valid
      if (this.authenticated) {
        UserStore.setUser({ first_name: "Bund", id: 1 });
      }
    });
  }

  async login(email: string, password: string) {
    try {
      const userClient = await genUserClient();
      const tokenPayload = await userClient.login(email, password);
      localStorage.setItem("access_token", tokenPayload);

      // set user here depending on JWT data
      UserStore.setUser({ first_name: "Bund", id: 1 });

      this.setAuthenticated(true);
    } catch (err) {
      this.setAuthenticated(false);
    }
  }

  async logout() {
    try {
      localStorage.setItem("access_token", "");
      this.setAuthenticated(false);

      // set user to null on logout
      UserStore.setUser(null);
    } catch (err) {
      console.error("LOGOUT ERROR: ", err);
    }
  }

  async checkAuth() {
    try {
      // check for auth token in local storage
      if (!this.getAccessToken()) {
        console.log("no token");
        this.setAuthenticated(false);
        return false;
      }

      // if a token exists - checks if the access token from local storage is valid
      const userClient = await genUserClient();
      const tokenPayload = await userClient.checkAuth();

      if (tokenPayload) {
        console.log("payload", tokenPayload);
        this.setAuthenticated(true);
        return true;
      } else {
        console.log("no token from check");
        this.setAuthenticated(false);
        return false;
      }
    } catch (err) {
      console.log("ERROR", err);
      this.setAuthenticated(false);
      return false;
    }
  }

  private setAuthenticated(isAuthenticated: boolean) {
    this.authenticated = isAuthenticated;
  }

  getAccessToken() {
    return localStorage.getItem("access_token");
  }

  isAuthenticated() {
    return this.authenticated;
  }
}

export const AuthStore = new AuthStoreImpl();
