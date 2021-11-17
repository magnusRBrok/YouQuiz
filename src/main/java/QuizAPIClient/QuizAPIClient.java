package QuizAPIClient;

import QuizAPIClient.Enums.QuizAPICategory;
import QuizAPIClient.Enums.QuizAPIDifficulty;
import lombok.Data;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Data
public class QuizAPIClient implements IQuizAPIClient {

    public static final String URL = "https://quizapi.io/api/v1/questions";
    public static final String API_KEY = "apiKey";
    public static final String API_KEY_VALUE = "v8c9ijoZKXjCa0d8Xep5pp1gi5ULm2lITzO8KfYE";
    public static final String LIMIT = "limit";
    public static final String CATEGORY = "category";
    public static final String DIFFICULTY = "difficulty";

    private Client client = ClientBuilder.newClient();

    /**
     * HTTP GET - Gets a random quiz
     *
     * @return List<QuizApiQuestionDTo>
     */
    public List<QuizApiQuestionDto> getQuiz(int questionLimit, QuizAPICategory category, QuizAPIDifficulty difficulty) throws ClientErrorException {
        return client
                .target(URL)
                .queryParam(API_KEY, API_KEY_VALUE)
                .queryParam(LIMIT, questionLimit)
                .queryParam(CATEGORY, category.getCategory())
                .queryParam(DIFFICULTY, difficulty.getDifficulty())
                .request(MediaType.APPLICATION_JSON)
                .get()
                .readEntity(new GenericType<List<QuizApiQuestionDto>>(){});
    }
}
