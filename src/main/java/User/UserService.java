package User;

import User.dao.IUserDAO;
import User.dao.UserDAOImpl;
import User.dto.DBUserDto;
import User.dto.DBUserQuizzesDto;
import Util.DTOUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("user")
public class UserService {

    IUserDAO userDAO = new UserDAOImpl();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id){
        DBUserQuizzesDto user = userDAO.getUser(id);
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(){
        Collection<DBUserQuizzesDto> users = userDAO.getAllUsers();
        return Response.status(Response.Status.OK).entity(users).build();
    }

    @POST
    @Consumes("application/json")
    public Response createUser(DBUserDto dto){
        DBUser user = DTOUtil.convert(dto, new TypeReference<DBUser>(){});
        int id = userDAO.addUser(user);
        return Response.status(Response.Status.CREATED).entity(id).build();
    }

    @PUT
    @Consumes("application/json")
    public Response updateUser(@QueryParam("id") int id, DBUserDto dto) {
        DBUser newUser = DTOUtil.convert(dto, new TypeReference<DBUser>(){});
        userDAO.updateUser(id, newUser);
        return Response.status(Response.Status.OK).entity("User updated").build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int id) {
        userDAO.deleteUser(id);
        return Response.status(Response.Status.OK).build();
    }
}
