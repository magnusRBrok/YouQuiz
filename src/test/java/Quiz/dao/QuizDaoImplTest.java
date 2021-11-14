package Quiz.dao;

import Quiz.dto.QuizIdDto;
import Quiz.model.Quiz;
import User.DBUser;
import User.dao.IUserDAO;
import User.dao.UserDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class QuizDaoImplTest {

    private IQuizDAO quizDAO = new QuizDaoImpl();
    private IUserDAO userDAO = new UserDAOImpl();

    @BeforeEach
    void setUp() {
        //TODO: It's probably very wrong to use the DAO classes here to seed the data before testing the DAO class.
        //But for now, it seems to solve the issues of dealing with auto incrementing id's between tests.
        userDAO.addUser(new DBUser("test user 1"));
        userDAO.addUser(new DBUser("test user 2"));
        quizDAO.addQuiz(new Quiz("title 1", "category 1", "description 1"), 1);
        quizDAO.addQuiz(new Quiz("title 2", "category 2", "description 2"), 2);
        quizDAO.addQuiz(new Quiz("title 3", "category 3", "description 3"), 1);
    }

    @AfterEach
    public void clearDataFromDatabase() {
        quizDAO.executeQuery("TRUNCATE SCHEMA PUBLIC AND COMMIT");
        quizDAO.executeQuery("ALTER SEQUENCE my_seq RESTART WITH 1");
    }

    @Test
    void getQuiz() {
        QuizIdDto quiz = quizDAO.getQuiz(3);
        assertEquals("title 1", quiz.getTitle());
        assertEquals("description 1", quiz.getDescription());
        assertEquals("category 1", quiz.getCategory());
        //assertEquals(1, quiz.getCreatedBy().getId());

        assertThrows(NotFoundException.class, () -> {
            quizDAO.getQuiz(1000);
        });
    }

    @Test
    void addQuiz() {
        int id = quizDAO.addQuiz(new Quiz("title 3", "category 3", "description 3"), 1);
        assertEquals(6, id);

        //Test that id auto increments
        id = quizDAO.addQuiz(new Quiz("title 4", "category 4", "description 4"), 1);
        assertEquals(7, id);
    }

    @Test
    void addAndGetQuiz() {
        int id = quizDAO.addQuiz(new Quiz("title 3", "category 3", "description 3"), 1);
        System.out.println("ID ER: " + id);
        QuizIdDto quiz = quizDAO.getQuiz(id);
        assertEquals("title 3", quiz.getTitle());
        assertEquals("description 3", quiz.getDescription());
        assertEquals("category 3", quiz.getCategory());
    }

    @Test
    void updateQuiz() {
        quizDAO.updateQuiz(5, new Quiz("new title", "new category", "new description"));
        QuizIdDto quiz = quizDAO.getQuiz(5);
        assertEquals("new title", quiz.getTitle());
        assertEquals("new description", quiz.getDescription());
        assertEquals("new category", quiz.getCategory());
    }

    @Test
    void deleteQuiz() {
        quizDAO.deleteQuiz(5);
        assertThrows(NotFoundException.class, () -> {
            quizDAO.getQuiz(5);
        });
    }
}