package Quiz;

import Quiz.dto.QuizDto;
import Quiz.dto.QuizIdDto;
import Quiz.model.Quiz;
import User.DBUser;
import Util.HibernateUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("quiz")
public class QuizService {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuiz(@PathParam("id") int id){
        Session session = HibernateUtil.getSession();
        Quiz quiz = session.get(Quiz.class, id);

        if (quiz == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        QuizIdDto dto = new ObjectMapper().convertValue(quiz, new TypeReference<QuizIdDto>(){});
        session.close();
        return Response.status(Response.Status.OK).entity(dto).build();
    }


    @POST
    @Consumes("application/json")
    public Response createQuiz(QuizDto dto){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        int quizIdSaved = -1;
        try {
            tx = session.beginTransaction();
            Quiz quiz = new ObjectMapper().convertValue(dto, new TypeReference<Quiz>(){});

            //TODO: Modify to get user by the id in claim of token in the request
            DBUser creator = session.get(DBUser.class, 3);
            quiz.setCreatedBy(creator);

            quizIdSaved = (int) session.save(quiz);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            session.close();
        }

        return Response.status(Response.Status.CREATED).entity(quizIdSaved).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteQuiz(@PathParam("id") int id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Quiz quiz = session.load(Quiz.class, id);

            if (quiz == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            session.delete(quiz);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return Response.status(Response.Status.OK).entity("Quiz deleted").build();
    }
}
