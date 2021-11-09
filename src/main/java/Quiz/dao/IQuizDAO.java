package Quiz.dao;

import Quiz.model.Quiz;

public interface IQuizDAO {

    Quiz getQuiz(int id);
    int addQuiz(Quiz quiz, int userId);
    void updateQuiz(int id, Quiz newQuiz);
    void deleteQuiz(int id);
    void executeQuery(String query);

}
