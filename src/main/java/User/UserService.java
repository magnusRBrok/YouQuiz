package User;

import User.dao.IUserDAO;
import User.dao.UserDAOImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserService {

    IUserDAO userDAO = new UserDAOImpl();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id){
        DBUser user = userDAO.getUser(id);
        DBUserDto dto = new ObjectMapper().convertValue(user, new TypeReference<DBUserDto>(){});
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @POST
    @Consumes("application/json")
    public Response createUser(DBUserDto dto){
        DBUser user = new ObjectMapper().convertValue(dto, new TypeReference<DBUser>(){});
        int id = userDAO.addUser(user);
        return Response.status(Response.Status.CREATED).entity(id).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int id) {
        userDAO.deleteUser(id);
        return Response.status(Response.Status.OK).build();
    }
}
