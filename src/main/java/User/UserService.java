package User;

import Quiz.*;
import Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.HashSet;
import java.util.Set;

@Path("test")
public class UserService {

    //TODO: Implement

    @GET
    public String getTest(){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        int userIdSaved = -1;
        try {
            tx = session.beginTransaction();
            DBUser u = new DBUser("test user");
            userIdSaved = (int) session.save(u);
            tx.commit();
            System.out.println("Den er saved!!!");
        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return "Hello World. Id: " + userIdSaved;
    }


    @GET()
    @Path("test2")
    public String getTest2(){
        Session session = HibernateUtil.getSession();
        DBUser user = null;
        user = session.get(DBUser.class, 2);
        session.close();

        if (user != null)
            return user.getFirst_name();
        return "failed";
    }

    @GET()
    @Path("test3")
    public String getTest3(){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            DBUser user = null;
            user = session.get(DBUser.class, 2);

            Quiz quiz = new Quiz("test quiz", "test description", "test category", user)
                    .addQuestion(new Question("What is bla bla?").addOption(new QuestionOption("wrong", false)).addOption(new QuestionOption("right", true)))
                    .addQuestion(new Question("Once again, what is bla bla?").addOption(new QuestionOption("wrong again", false)).addOption(new QuestionOption("right again", true)));

            user.addQuiz(quiz);

            session.save(user);
            session.save(quiz);

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
