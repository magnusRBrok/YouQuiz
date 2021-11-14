package Quiz.dto;

import User.dto.DBUserDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class QuizIdDto {
    private int id;
    private String title;
    private String category;
    private String description;
    private Collection<QuestionIdDto> questions;
    private DBUserDto createdBy;
    private int createdById;
}
