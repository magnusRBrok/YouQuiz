package Quiz.model;

import User.DBUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "QUIZ")
@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class Quiz {
    @Id
    //@GeneratedValue
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DATA")
    @SequenceGenerator(sequenceName = "my_seq", allocationSize = 1, name = "SEQ_DATA")
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable=true)
    @JsonBackReference
    @ToString.Exclude
    private DBUser createdBy;

    @Column(name = "user_id_only")
    private int createdById;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Collection<Question> questions = new ArrayList<>();

    public Quiz(String title) {
        this.title = title;
    }

    public Quiz(String title, String category, String description) {
        this.title = title;
        this.category = category;
        this.description = description;
    }

    public Quiz(String title, String category, String description, DBUser createdBy) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.createdBy = createdBy;
    }

    public Quiz() {

    }
}