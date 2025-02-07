package Quiz;

import Quiz.dao.IQuizDAO;
import Quiz.dao.QuizDaoImpl;
import Quiz.dto.QuizDto;
import Quiz.dto.QuizIdDto;
import Quiz.model.Question;
import Quiz.model.Quiz;
import User.DBUser;
import User.dao.IUserDAO;
import User.dao.UserDAOImpl;
import Util.DTOUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Hibernate;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("quiz")
public class QuizService {

    private IQuizDAO quizDAO = new QuizDaoImpl();
    private IUserDAO userDAO = new UserDAOImpl();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuiz(@PathParam("id") int id){
        QuizIdDto dto = quizDAO.getQuiz(id);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllQuizes(){
        Collection<QuizIdDto> quizzes = quizDAO.getAllQuizzes();
        return Response.status(Response.Status.OK).entity(quizzes).build();
    }


    @POST
    @Consumes("application/json")
    public Response createQuiz(QuizDto dto){
        Quiz quiz = DTOUtil.convert(dto, new TypeReference<Quiz>(){});

        //TODO: Modify so that it fetches the user that made the request. Id from token claims.
        //DBUser creator = userDAO.getUser(3);
        //quiz.setCreatedBy(creator);

        int id = quizDAO.addQuiz(quiz, -1);

        return Response.status(Response.Status.CREATED).entity(id).build();
    }

    @PUT
    @Consumes("application/json")
    public Response updateQuiz(@QueryParam("id") int id, QuizIdDto dto) {
        Quiz newQuiz = DTOUtil.convert(dto, new TypeReference<Quiz>() {});
        quizDAO.updateQuiz(id, newQuiz);
        return Response.status(Response.Status.OK).entity("Quiz updated").build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteQuiz(@PathParam("id") int id) {
        quizDAO.deleteQuiz(id);
        return Response.status(Response.Status.OK).entity("Quiz deleted").build();
    }
}
