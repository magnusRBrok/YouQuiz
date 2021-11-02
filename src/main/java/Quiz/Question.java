package Quiz;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "QUESTION")
public class Question {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="quiz_id", referencedColumnName = "id", nullable=false)
    @JsonBackReference
    private Quiz quiz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<QuestionOption> options;


    public Question addOption(QuestionOption option) {
        if(this.options == null) {
            this.options = new ArrayList<QuestionOption>();
        }
        this.options.add(option);
        option.setQuestion(this);
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Collection<QuestionOption> getOptions() {
        return options;
    }

    public void setOptions(Collection<QuestionOption> options) {
        this.options = options;
    }

    public Question() {
    }

    public Question(String description) {
        this.description = description;
    }
}