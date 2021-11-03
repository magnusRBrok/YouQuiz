package User.dao;

import User.DBUser;
import Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;

public class UserDAOImpl implements IUserDAO {
    @Override
    public DBUser getUser(int id) {
        try (Session session = HibernateUtil.getSession()) {
            DBUser user = session.get(DBUser.class, id);

            if (user == null)
                throw new NotFoundException("User not found. Id: " + id);

            return user;
        }
    }

    @Override
    public int addUser(DBUser user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            int userIdSaved = (int) session.save(user);
            tx.commit();
            return userIdSaved;
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            throw new InternalServerErrorException();
        }
    }

    @Override
    public void deleteUser(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            DBUser user = session.load(DBUser.class, id);

            if (user == null)
                throw new NotFoundException("User not found. Id: " + id);

            session.delete(user);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            ex.printStackTrace();
            throw new InternalServerErrorException();
        }
    }
}
