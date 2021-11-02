package Quiz;

import javax.persistence.*;

@Entity
@Table(name = "QUESTION_OPTION")
public class QuestionOption {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="question_id", referencedColumnName = "id", nullable=false)
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

    public int getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}