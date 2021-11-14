package User.dao;

import User.DBUser;
import User.dto.DBUserQuizzesDto;
import Util.DAObase;
import Util.DTOUtil;
import Util.HibernateUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDAOImpl extends DAObase implements IUserDAO {
    @Override
    public DBUserQuizzesDto getUser(int id) {
        try (Session session = HibernateUtil.getSession()) {
            DBUser user = session.get(DBUser.class, id);

            if (user == null)
                throw new NotFoundException("User not found. Id: " + id);

            return DTOUtil.convert(user, new TypeReference<DBUserQuizzesDto>(){});
        }
    }

    @Override
    public Collection<DBUserQuizzesDto> getAllUsers() {
        try (Session session = HibernateUtil.getSession()) {
            List<DBUserQuizzesDto> users = new ArrayList<>();
            HibernateUtil.loadAllData(DBUser.class, session).forEach(user -> {
                users.add(DTOUtil.convert(user, new TypeReference<DBUserQuizzesDto>(){}));
            });
            return users;
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new InternalServerErrorException("An exception was thrown when fetching users");
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
    public void updateUser(int id, DBUser newUser) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            DBUser entity = session.get(DBUser.class, id);
            if (entity == null)
                throw new NotFoundException("User not found. Id: " + id);

            entity.setFirst_name(newUser.getFirst_name());

            entity.getQuizzes().clear();
            entity.getQuizzes().addAll(newUser.getQuizzes());

            session.merge(entity);

            tx.commit();
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
