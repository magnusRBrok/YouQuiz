package Quiz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionDto {
    private String description;
    private Collection<QuestionOptionDto> options;

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
                ", description='" + description + '\'' +
                ", options=" + options +
                '}';
    }
}
