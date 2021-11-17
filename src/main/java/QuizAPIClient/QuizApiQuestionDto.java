package QuizAPIClient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class QuizApiQuestionDto {
    private int id;
    private String question;
    private String description;
    private Map<String, String> answers;
    private boolean multiple_correct_answers;
    private Map<String, Boolean> correct_answers;
    private String correct_answer;
    private String explanation;
    private String tip;
    private String category;
    private String difficulty;
}
