package Quiz;

import User.DBUser;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "QUIZ")
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
    @JoinColumn(name="id", nullable=false)
    private DBUser createdBy;

    @OneToMany(mappedBy = "quiz")
    private Set<Question> questions;

    public Quiz(String title) {
        this.title = title;
    }

    public Quiz() {

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

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}