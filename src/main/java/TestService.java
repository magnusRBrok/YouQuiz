import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("test")
public class TestService {

    @GET
    public String getTest(){

        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;
        int userIdSaved;
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

        return "Hello World";
    }


    @GET()
    @Path("test2")
    public String getTest2(){

        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        DBUser user = null;
        user = session.get(DBUser.class, 2);
        session.close();

        if (user != null)
            return user.getFirst_name();
        return "failed";
    }
}