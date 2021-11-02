package Quiz.dto;

import java.util.Collection;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class QuizIdDto {
    private int id;
    private String title;
    private String category;
    private String description;
    private Collection<QuestionIdDto> questions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<QuestionIdDto> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<QuestionIdDto> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "QuizDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", questions=" + questions +
                '}';
    }
}
