package Quiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionDto {
    private int id;
    private String description;
    private Collection<QuestionOptionDto> options;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<QuestionOptionDto> getOptions() {
        return options;
    }

    public void setOptions(Collection<QuestionOptionDto> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "QuestionDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", options=" + options +
                '}';
    }
}
