package Quiz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class QuestionOptionIdDto {
    private int id;
    private String text;
    private boolean isCorrect;
}
