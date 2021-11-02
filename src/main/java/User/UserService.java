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

            Quiz quiz = new Quiz("test quiz");
            quiz.setDescription("test description");
            quiz.setCategory("test category");
            quiz.setCreatedBy(user);

            Question q1 = new Question();
            q1.setDescription("What is bla bla bla?");
            q1.setQuiz(quiz);

            QuestionOption answer1 = new QuestionOption();
            answer1.setText("wrong answer");
            answer1.setCorrect(false);
            answer1.setQuestion(q1);

            QuestionOption answer2 = new QuestionOption();
            answer2.setText("right answer");
            answer2.setCorrect(true);
            answer2.setQuestion(q1);

            Set<QuestionOption> options = new HashSet<QuestionOption>();
            options.add(answer1);
            options.add(answer2);
            q1.setOptions(options);

            Set<Question> questions = new HashSet<Question>();
            questions.add(q1);
            quiz.setQuestions(questions);

            session.save(user);
            session.save(quiz);
            session.save(q1);
            session.save(answer1);
            session.save(answer2);

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
