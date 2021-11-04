package Quiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

class QuizApiClientTest {

    private QuizApiClient quizApiClient;

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
        when(webTargetMock.request(anyString())).thenReturn(invocationBuilderMock);
        when(invocationBuilderMock.header(anyString(), anyString())).thenReturn(invocationBuilderMock);
        when(invocationBuilderMock.get()).thenReturn(responseMock);

        quizApiClient = new QuizApiClient();
        quizApiClient.setClient(clientMock);
    }

    @Test
    public void getQuizParamsTest() {
        quizApiClient.getQuiz();

        verify(clientMock, times(1)).target("https://quizapi.io/api/v1/questions");
        verify(webTargetMock, times(1)).request(MediaType.APPLICATION_JSON);
        verify(invocationBuilderMock,times(1)).header(eq("X-Api-Key"), anyString());
        verify(invocationBuilderMock, times(1)).get();
        verify(responseMock, times(1)).readEntity(String.class);
    }

    @Test
    public void getQuizTest() {
        // TODO: find better solution than casting...
        when(responseMock.readEntity((Class<Object>) any())).thenReturn("Hello there");

        String string = quizApiClient.getQuiz();

        assertEquals("Hello there", string);
    }
  
}