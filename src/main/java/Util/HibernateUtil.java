package Util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class HibernateUtil {

    private static SessionFactory factory = null;

    static {
        try {
            Configuration cfg = new Configuration().configure();
            String databaseUrl = System.getenv("DATABASE_URL") != null ? System.getenv("DATABASE_URL") : "localhost:5432/hibernatedb";
            String connectionUrl = "jdbc:postgresql://" + databaseUrl;
            cfg.setProperty("hibernate.connection.url", connectionUrl);
            factory = cfg.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {

        Session session = null;
        try {
            session = factory.openSession();
        }catch(Throwable t){
            System.err.println("Exception while getting session.. ");
            t.printStackTrace();
        }
        if(session == null) {
            System.err.println("session is discovered null");
        }

        return session;
    }

    public static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }
}
