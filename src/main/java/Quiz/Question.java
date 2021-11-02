package Quiz;

import javax.persistence.*;
import java.util.Set;

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
    @JoinColumn(name="id", nullable=false)
    private Question question;

    @OneToMany(mappedBy = "question")
    private Set<QuestionOption> options;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<QuestionOption> getOptions() {
        return options;
    }

    public void setOptions(Set<QuestionOption> options) {
        this.options = options;
    }

    public Question() {

    }
}