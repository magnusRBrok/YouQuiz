package QuizAPIClient;

import QuizAPIClient.Enums.QuizAPICategory;
import QuizAPIClient.Enums.QuizAPIDifficulty;

import javax.ws.rs.ClientErrorException;
import java.util.List;

public interface IQuizAPIClient {
    List<QuizApiQuestionDto> getQuiz(int questionLimit, QuizAPICategory category, QuizAPIDifficulty difficulty) throws ClientErrorException;
}
