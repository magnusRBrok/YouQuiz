package token;

import login.LoginData;
import login.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Path("login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TokenService {

    @POST
    public String postLoginData(LoginData login) throws NotAuthorizedException
    {
        if (login!=null && "brian@hej.dk".equals(login.getEmail()) && "kodeord".equals(login.getPassword())){
            return TokenHandler.generateJwtToken(new User(login.getEmail()));
        }
        throw new NotAuthorizedException("forkert brugernavn/kodeord");
    }

    @POST
    @Path("tokentest")
    public User postToken(String token) throws NotAuthorizedException {
        return TokenHandler.validate(token);
    }

}
