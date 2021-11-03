package User;

import Quiz.model.Quiz;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "DBUSER") //WATCH out  USER is a reserved name!
public class DBUser {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String first_name;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Collection<Quiz> quizzes;

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

    public Collection<Quiz> getQuizzes() {
        return quizzes;
    }

    @Override
    public String toString() {
        return "DBUser{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", quizzes=" + quizzes +
                '}';
    }
}