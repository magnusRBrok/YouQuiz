package Quiz.model;

import User.DBUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "QUIZ")
@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class Quiz {
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
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable=false)
    @JsonBackReference
    private DBUser createdBy;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Collection<Question> questions;

    public Quiz(String title) {
        this.title = title;
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