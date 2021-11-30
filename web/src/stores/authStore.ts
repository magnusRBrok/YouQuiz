import { makeAutoObservable } from "mobx";
import { genUserClient } from "../services/apiClients";

export class AuthStore {
  private authenticated = false;

  // components that are "listening" to this AuthStore
  // will re-render because of the makeAutoObservable method called in the constructor.
  constructor() {
    makeAutoObservable(this);

    // Check for existing access-token and cast to boolean
    this.authenticated = !!this.getAccessToken();
  }

  async login(email: string, password: string) {
    try {
      const userClient = await genUserClient();
      const tokenPayload = await userClient.login(email, password);
      localStorage.setItem("access_token", tokenPayload);
      this.setAuthenticated(true);
    } catch (err) {
      this.setAuthenticated(false);
    }
  }

  async logout() {
    try {
      localStorage.setItem("access_token", "");
      this.setAuthenticated(false);
    } catch (err) {
      console.error("LOGOUT ERROR: ", err);
    }
  }

  async checkAuth() {
    try {
      // check for auth token in local storage
      if (!this.getAccessToken()) {
        this.setAuthenticated(false);
        return;
      }

      // retrieves users auth token from backend
      const userClient = await genUserClient();
      const tokenPayload = await userClient.checkAuth();

      if (tokenPayload) {
        this.setAuthenticated(true);
      } else {
        this.setAuthenticated(false);
      }
    } catch (err) {
      this.setAuthenticated(false);
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
