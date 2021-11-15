package User.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import Quiz.dto.QuizIdDto;
import lombok.Data;

import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class DBUserQuizzesDto {

    private int id;
    private String first_name;
    private Collection<QuizIdDto> quizzes;
}