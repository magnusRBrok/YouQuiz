package Quiz.dao;

import Quiz.model.Quiz;
import Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;

public class QuizDaoImpl implements IQuizDAO {
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
    public int addQuiz(Quiz quiz) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            int id = (int) session.save(quiz);
            tx.commit();
            return id;
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
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
            Quiz quiz = session.load(Quiz.class, id);

            if (quiz == null)
                throw new NotFoundException("Quiz not found. Id: " + id);

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
