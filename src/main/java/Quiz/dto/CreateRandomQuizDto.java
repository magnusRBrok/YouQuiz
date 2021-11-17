package Quiz.dto;

import QuizAPIClient.Enums.QuizAPICategory;
import QuizAPIClient.Enums.QuizAPIDifficulty;
import lombok.Data;

public @Data class CreateRandomQuizDto {
    private String title;
    private String description;
    private int questionLimit;
    private QuizAPICategory category;
    private QuizAPIDifficulty difficulty;
}
