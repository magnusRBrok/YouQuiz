package User;

import Quiz.model.Question;
import Quiz.model.QuestionOption;
import Quiz.model.Quiz;
import Util.HibernateUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserService {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id){
        Session session = HibernateUtil.getSession();
        DBUser user = session.get(DBUser.class, id);

        if (user == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        DBUserDto dto = new ObjectMapper().convertValue(user, new TypeReference<DBUserDto>(){});
        session.close();
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @POST
    @Consumes("application/json")
    public Response createUser(DBUserDto dto){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        int userIdSaved = -1;
        try {
            tx = session.beginTransaction();
            DBUser u = new DBUser();
            u.setFirst_name(dto.getFirst_name());
            userIdSaved = (int) session.save(u);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            session.close();
        }

        return Response.status(Response.Status.CREATED).entity(userIdSaved).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            DBUser user = session.load(DBUser.class, id);

            if (user == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            session.delete(user);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            session.close();
        }

        return Response.status(Response.Status.OK).build();
    }

    //TODO: This should me removed. Only temporary for testing
    @GET()
    @Path("test3")
    public String getTest3(){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            DBUser user = null;
            user = session.get(DBUser.class, 42);

            Quiz quiz = new Quiz("test quiz", "test description", "test category", user)
                    .addQuestion(new Question("What is bla bla?").addOption(new QuestionOption("wrong", false)).addOption(new QuestionOption("right", true)))
                    .addQuestion(new Question("Once again, what is bla bla?").addOption(new QuestionOption("wrong again", false)).addOption(new QuestionOption("right again", true)));

            user.addQuiz(quiz);

            session.save(user);

            tx.commit();

            System.out.println(user);

        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return "succes?";
    }

}
