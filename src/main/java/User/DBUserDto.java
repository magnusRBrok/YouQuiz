package User;

import Quiz.Quiz;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import Quiz.QuizDto;
import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DBUserDto {

    private int id;
    private String first_name;
    private Collection<QuizDto> quizzes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Collection<QuizDto> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(Collection<QuizDto> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public String toString() {
        return "DBUserDto{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", quizzes=" + quizzes +
                '}';
    }
}