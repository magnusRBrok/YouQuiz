import { User } from "../../model/user";
import { AuthBase, ClientBase } from "../clientBase";

export interface IUserClient {
  getUser: (id: number) => Promise<User>;
  createUser: (user: User) => Promise<User>;
  updateUser: (id: number, user: User) => Promise<User>;
  deleteUser: (id: number) => Promise<void>;
}

export class UserClient extends ClientBase implements IUserClient {
  private baseUrl?: string;

  constructor(configuration: AuthBase, baseUrl?: string) {
    super(configuration);
    // if client constructor is called without a baseUrl, localhost:8080 will be used
    this.baseUrl =
      baseUrl !== undefined && baseUrl !== null
        ? baseUrl
        : process.env.REACT_APP_API_URL;
  }

  getUser = async (id: number): Promise<User> => {
    const url = `${this.baseUrl}/rest/user/${id}`;
    const options: RequestInit = {};

    return (
      this.transformOptions(options)
        .then((transformedOptions_) => fetch(url, transformedOptions_))
        .then((response: Response) => this.processResponse(response))
        // TODO handle the returned JSON object in next chain
        .then()
    );
  };

  createUser = async (user: User): Promise<User> => {
    const url = `${this.baseUrl}/rest/user`;
    const options: RequestInit = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(user),
    };
    return (
      this.transformOptions(options)
        .then((transformedOptions_) => fetch(url, transformedOptions_))
        .then((response: Response) => this.processResponse(response))
        .catch((e) => console.log("ERROR", e))
        // TODO handle the returned JSON object in next chain
        .then()
    );
  };

  updateUser = async (id: number, user: User): Promise<User> => {
    const url = `${this.baseUrl}/rest/user`;
    const options: RequestInit = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(user),
    };
    return (
      this.transformOptions(options)
        .then((transformedOptions_) => fetch(url, transformedOptions_))
        .then((response: Response) => this.processResponse(response))
        .catch((e) => console.log("ERROR", e))
        // TODO handle the returned JSON object in next chain
        .then()
    );
  };

  deleteUser = async (id: number): Promise<void> => {
    const url = `${this.baseUrl}/rest/user/${id}`;
    const options: RequestInit = {
      method: "DELETE",
    };
    return this.transformOptions(options)
      .then((transformedOptions_) => fetch(url, transformedOptions_))
      .then((response: Response) => this.processResponse<void>(response))
      .catch((e) => console.log("ERROR", e));
  };
}
