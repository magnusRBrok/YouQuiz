package User;

import Quiz.QuestionOption;
import Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.*;

@Path("quiz")
public class QuizService {

    @DELETE
    @Path("option/{id}")
    public String deleteOption(@PathParam("id") int id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            QuestionOption option = session.load(QuestionOption.class, id);
            session.delete(option);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return "Question option deleted";
    }
}
