package Quiz.dao;

import Quiz.model.Quiz;
import User.DBUser;
import Util.DAObase;
import Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import java.util.Collection;

public class QuizDaoImpl extends DAObase implements IQuizDAO {
    @Override
    public Quiz getQuiz(int id) {
        try (Session session = HibernateUtil.getSession()) {
            Quiz quiz = session.get(Quiz.class, id);

            if (quiz == null)
                throw new NotFoundException("Quiz not found. Id: " + id);

            return quiz;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Quiz> getAllQuizzes() {
        try (Session session = HibernateUtil.getSession()) {
            return HibernateUtil.loadAllData(Quiz.class, session);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int addQuiz(Quiz quiz, int userId) {
        Transaction tx = null;
        Session session = HibernateUtil.getSession();
        try {
            tx = session.beginTransaction();

            DBUser user = session.get(DBUser.class, userId);
            if (user == null)
                throw new NotFoundException("User id not found. Id: " + userId);

            quiz.setCreatedBy(user);

            int id = (int) session.save(quiz);
            tx.commit();
            return id;
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return -1;
    }

    @Override
    public void updateQuiz(int id, Quiz newQuiz) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            Quiz entity = session.get(Quiz.class, id);
            if (entity == null)
                throw new NotFoundException("Quiz not found. Id: " + id);

            entity.setTitle(newQuiz.getTitle());
            entity.setCategory(newQuiz.getCategory());
            entity.setDescription(newQuiz.getDescription());

            entity.getQuestions().clear();
            entity.getQuestions().addAll(newQuiz.getQuestions());

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
    public void deleteQuiz(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            Quiz quiz = session.get(Quiz.class, id);

            if (quiz == null)
                throw new NotFoundException("Quiz not found. Id: " + id);

            quiz.getCreatedBy().getQuizzes().remove(quiz);
            quiz.setCreatedBy(null);
            session.delete(quiz);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            throw new InternalServerErrorException();
        }
    }
}
