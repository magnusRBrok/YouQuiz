import { AuthBase } from "./clientBase";

type BaseConstructor<T> = {
  new (configuration: AuthBase, baseUrl?: string): T;
};

export const api = async <T, U extends BaseConstructor<T>>(
  Client: U
): Promise<T> => {
  //TODO here we should get the current users authToken instead of empty string
  //TODO here we should also configure the URL string so it matches our server-backend url
  const authToken = "";
  return new Client(new AuthBase(authToken), process.env.REACT_APP_API_URL);
};
