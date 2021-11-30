package QuizAPIClient;

import QuizAPIClient.Enums.QuizAPICategory;
import QuizAPIClient.Enums.QuizAPIDifficulty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class QuizAPIClientTest {

    private QuizAPIClient quizApiClient;

    @Mock
    private Client clientMock;

    @Mock
    private WebTarget webTargetMock;

    @Mock
    private Invocation.Builder invocationBuilderMock;

    @Mock
    private Response responseMock;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

        when(clientMock.target(anyString())).thenReturn(webTargetMock);
        when(webTargetMock.queryParam(anyString(), any())).thenReturn(webTargetMock);
        when(webTargetMock.request(anyString())).thenReturn(invocationBuilderMock);
        when(invocationBuilderMock.header(anyString(), anyString())).thenReturn(invocationBuilderMock);
        when(invocationBuilderMock.get()).thenReturn(responseMock);

        List<QuizApiQuestionDto> questions = new ArrayList<>();
        QuizApiQuestionDto dto = new QuizApiQuestionDto();
        dto.setId(1);
        dto.setQuestion("test");
        dto.setAnswers(new HashMap<>());
        dto.getAnswers().put("answer_a", "test");
        dto.setCorrect_answers(new HashMap<>());
        dto.getCorrect_answers().put("answer_a_correct", true);
        dto.setCategory("devops");
        dto.setDifficulty("easy");
        dto.setDescription("test");
        questions.add(dto);

        when(responseMock.readEntity(new GenericType<List<QuizApiQuestionDto>>(){})).thenReturn(questions);

        quizApiClient = new QuizAPIClient();
        quizApiClient.setClient(clientMock);
    }

    @Test
    public void getQuizParamsTest() {
        quizApiClient.getQuiz(10, QuizAPICategory.devops, QuizAPIDifficulty.easy);

        verify(clientMock, times(1)).target("https://quizapi.io/api/v1/questions");
        verify(webTargetMock, times(1)).queryParam(QuizAPIClient.API_KEY, QuizAPIClient.API_KEY_VALUE);
        verify(webTargetMock, times(1)).request(MediaType.APPLICATION_JSON);
        verify(invocationBuilderMock, times(1)).get();
        verify(responseMock, times(1)).readEntity(new GenericType<List<QuizApiQuestionDto>>(){});
    }

    @Test
    public void getQuizTest() {
        List<QuizApiQuestionDto> result = quizApiClient.getQuiz(10, QuizAPICategory.devops, QuizAPIDifficulty.easy);

        assertEquals(1, result.size());
        result.forEach(dto -> {
            assertEquals(1, dto.getId());
            assertEquals("test", dto.getQuestion());
            assertEquals("test", dto.getAnswers().get("answer_a"));
            assertTrue(dto.getCorrect_answers().get("answer_a_correct"));
            assertEquals("devops", dto.getCategory());
            assertEquals("easy", dto.getDifficulty());
            assertEquals("test", dto.getDescription());
        });
    }
}