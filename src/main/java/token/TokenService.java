package token;

import login.LoginData;
import login.User;
import org.mindrot.jbcrypt.BCrypt;

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
        String hashedPass = BCrypt.hashpw(login.getPassword(), BCrypt.gensalt());

        if (login!=null && "brian@hej.dk".equals(login.getEmail()) && BCrypt.checkpw(login.getPassword(), hashedPass)){
            return TokenHandler.generateJwtToken(new User(login.getEmail()));
        }
        throw new NotAuthorizedException("forkert brugernavn/kodeord");
    }

    @POST
    @Path("validate")
    public User postToken(String token) throws NotAuthorizedException {
        return TokenHandler.validate(token);
    }

}
