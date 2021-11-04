package Quiz;

import lombok.Data;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Data
public class QuizApiClient {

    private static final String URL = "https://quizapi.io/api/v1/questions";
    private static final String API_KEY = "X-Api-Key";
    private static final String API_KEY_VALUE = "9AnuuVYnBmY5hSQ5AO4PLVTXBse8PYaxC0Rz9W0f";

    private Client client = ClientBuilder.newClient();

    /**
     * HTTP GET - Gets a random quiz
     *
     * @return String
     */
    public String getQuiz() {
        return client
                .target(URL)
                .request(MediaType.APPLICATION_JSON)
                .header(API_KEY, API_KEY_VALUE)
                .get()
                .readEntity(String.class);

        // TODO: Do entity mapping
        // TODO: Error handling
    }
}
