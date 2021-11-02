package Quiz.model;

import User.DBUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "QUIZ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quiz {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable=false)
    @JsonBackReference
    private DBUser createdBy;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<Question> questions;

    public Quiz(String title) {
        this.title = title;
    }

    public Quiz(String title, String category, String description, DBUser createdBy) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.createdBy = createdBy;
    }

    public Quiz() {

    }

    public Quiz addQuestion(Question question) {
        if(this.questions == null) {
            this.questions = new ArrayList<Question>();
        }
        this.questions.add(question);
        question.setQuiz(this);
        return this;
    }

    public int getId() {
        return id;
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

    public DBUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(DBUser createdBy) {
        this.createdBy = createdBy;
    }

    public Collection<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }
}