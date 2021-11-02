import javax.persistence.*;

@Entity
@Table(name = "DBUSER") //WATCH out  USER is a reserved name!
public class DBUser {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String first_name;

    public DBUser(String name) {
        this.first_name = name;
    }

    public DBUser() {

    }

    public String getFirst_name() {
        return first_name;
    }
}