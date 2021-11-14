package Quiz.dao;

import Quiz.dto.QuizIdDto;
import Quiz.model.Quiz;

import java.util.Collection;

public interface IQuizDAO {

    Quiz getQuiz(int id);
    Collection<QuizIdDto> getAllQuizzes();
    int addQuiz(Quiz quiz, int userId);
    void updateQuiz(int id, Quiz newQuiz);
    void deleteQuiz(int id);
    void executeQuery(String query);

}
