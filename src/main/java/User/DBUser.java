package User;

import Quiz.Quiz;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "DBUSER") //WATCH out  USER is a reserved name!
public class DBUser {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String first_name;

    @OneToMany(mappedBy = "createdBy")
    private Set<Quiz> quizzes;

    public DBUser(String name) {
        this.first_name = name;
    }

    public DBUser() {

    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Set<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(Set<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}