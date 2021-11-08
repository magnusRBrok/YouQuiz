package User;

import Quiz.model.Quiz;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "DBUSER") //WATCH out  USER is a reserved name!
public @Data
class DBUser {
    @Id
    //@GeneratedValue
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DATA")
    @SequenceGenerator(sequenceName = "my_seq", allocationSize = 1, name = "SEQ_DATA")
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
}