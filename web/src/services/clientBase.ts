export class AuthBase {
    private accessToken: string;
    constructor(accessToken: string) {
      this.accessToken = accessToken;
    }
  
    transformHttpRequestOptions(options: RequestInit): Promise<RequestInit> {
      if (this.accessToken) {
        // Add headers object, if it doesn't already exist.
        if (!options.headers) {
          options.headers = {};
        }
        // eslint-disable-next-line @typescript-eslint/consistent-type-assertions
        (<Record<string, string>>options.headers).Authorization = "Bearer " + this.accessToken;
        return Promise.resolve(options);
      }
      return Promise.resolve(options);
    }
  }

  export class ClientBase {
    constructor(private AuthBase: AuthBase) {}
  
    /**
     *
     * @param options
     * @param skipAuth If true, no auth headers will be added to the request.
     * @returns
     */
    transformOptions(options: RequestInit, skipAuth = false): Promise<RequestInit> {
      return !skipAuth && this.AuthBase
        ? this.AuthBase.transformHttpRequestOptions(options)
        : Promise.resolve(options);
    }
  
    setAuthBase(authBase: AuthBase): void {
      this.AuthBase = authBase;
    }
  
    /**
     * Processes the response and checks the status message.
     * If the status is not 200 or 201, it throws an exception.
     * If the status is 200 or 201, it returns the json of the response.
     * @param response
     * @returns
     */
    processResponse<T>(response: Response): Promise<T> {
      const status = response.status;
    
      if (status === 200 || status === 201) {
        return response.text().then(_responseText => {
          try {
            const result = _responseText === "" ? null : (JSON.parse(_responseText) as T);
            return result;
          } catch (e) {
            return null as any;
          }
        });
      } else {
        return response.text().then(_responseText => {
          //TODO throw appropriate exception here 
          console.log(_responseText, status)
          return null as any
        })
      }
    }
  }