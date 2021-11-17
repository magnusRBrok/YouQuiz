package QuizAPIClient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class QuizAPIQuizDto {
    private Collection<QuizApiQuestionDto> questions;
}
