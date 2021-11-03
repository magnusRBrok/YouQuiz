package Quiz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "QUESTION_OPTION")
@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class QuestionOption {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="question_id", referencedColumnName = "id", nullable=false)
    @JsonBackReference
    private Question question;

    @Column(name = "text")
    private String text;

    @Column(name = "is_correct")
    private boolean isCorrect;

    public QuestionOption() {

    }

    public QuestionOption(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }
}