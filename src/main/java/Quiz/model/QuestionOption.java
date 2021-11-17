package Quiz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "QUESTION_OPTION")
@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class QuestionOption {
    @Id
    //@GeneratedValue
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DATA")
    @SequenceGenerator(sequenceName = "my_seq", allocationSize = 1, name = "SEQ_DATA")
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="question_id", referencedColumnName = "id", nullable=false)
    @JsonBackReference
    @ToString.Exclude
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