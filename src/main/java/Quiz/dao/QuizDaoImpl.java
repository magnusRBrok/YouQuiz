package Quiz.dao;

import Quiz.dto.QuizIdDto;
import Quiz.model.Quiz;
import User.DBUser;
import User.dto.DBUserDto;
import Util.DAObase;
import Util.DTOUtil;
import Util.HibernateUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QuizDaoImpl extends DAObase implements IQuizDAO {
    @Override
    public QuizIdDto getQuiz(int id) {
        try (Session session = HibernateUtil.getSession()) {
            Quiz quiz = session.get(Quiz.class, id);
            if (quiz == null)
                throw new NotFoundException("Quiz not found. Id: " + id);

            DBUser user = session.get(DBUser.class, quiz.getCreatedById());
            if (user == null)
                throw new NotFoundException("Creator of quiz not found. Quiz id: " + id);

            QuizIdDto dto = DTOUtil.convert(quiz, new TypeReference<QuizIdDto>(){});
            dto.setCreatedBy(DTOUtil.convert(user, new TypeReference<DBUserDto>(){}));

            return dto;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<QuizIdDto> getAllQuizzes() {
        try (Session session = HibernateUtil.getSession()) {
            List<QuizIdDto> quizzes = new ArrayList<>();
            HibernateUtil.loadAllData(Quiz.class, session).forEach(quiz -> {
                DBUser user = session.get(DBUser.class, quiz.getCreatedById());
                if (user == null)
                    throw new NotFoundException("Creator of quiz not found. Quiz Id: " + quiz.getId());
                QuizIdDto dto = DTOUtil.convert(quiz, new TypeReference<QuizIdDto>(){});
                dto.setCreatedBy(DTOUtil.convert(user, new TypeReference<DBUserDto>(){}));
                quizzes.add(dto);
            });
            return quizzes;
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new InternalServerErrorException("An exception was thrown when fetching quizzes.");
        }
    }

    @Override
    public int addQuiz(Quiz quiz, int userId) {
        Transaction tx = null;
        Session session = HibernateUtil.getSession();
        try {
            tx = session.beginTransaction();

            //TODO: Change back to the commented out code
            //DBUser user = session.get(DBUser.class, userId);
            DBUser user = HibernateUtil.loadAllData(DBUser.class, session).get(0);
            if (user == null)
                throw new NotFoundException("User id not found. Id: " + userId);

            quiz.setCreatedById(user.getId());
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
