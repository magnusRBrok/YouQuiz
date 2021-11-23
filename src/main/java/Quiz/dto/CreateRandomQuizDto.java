package Quiz.dto;

import QuizAPIClient.Enums.QuizAPICategory;
import QuizAPIClient.Enums.QuizAPIDifficulty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class CreateRandomQuizDto {
    private String title;
    private String description;
    private int questionLimit;
    private QuizAPICategory category;
    private QuizAPIDifficulty difficulty;
}
