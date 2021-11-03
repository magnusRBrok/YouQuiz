package Quiz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class QuestionDto {
    private String description;
    private Collection<QuestionOptionDto> options;
}
