import { AuthBase } from "./clientBase";

type BaseConstructor<T> = {
  new (configuration: AuthBase, baseUrl?: string): T;
};

export const api = async <T, U extends BaseConstructor<T>>(
  Client: U
): Promise<T> => {
  // get access token from local storage, defaults to empty string if local storage is empty
  const authToken = localStorage.getItem("access_token") ?? "";
  return new Client(new AuthBase(authToken), process.env.REACT_APP_API_URL);
};
