package Quiz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class QuizDto {
    private String title;
    private String category;
    private String description;
    private Collection<QuestionDto> questions;
}
