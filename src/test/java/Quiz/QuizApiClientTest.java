package Quiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
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

        // TODO: find better solution than casting...
        when(responseMock.readEntity((Class<Object>) any())).thenReturn("Hello there");

        quizApiClient = new QuizApiClient();
        quizApiClient.setClient(clientMock);
    }

    @Test
    public void getQuizTest() {
        String string = quizApiClient.getQuiz();

        assertEquals("Hello there", string);

    }
  
}