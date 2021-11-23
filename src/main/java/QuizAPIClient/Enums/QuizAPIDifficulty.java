package QuizAPIClient.Enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum QuizAPIDifficulty {
    easy("easy"),
    medium("medium"),
    hard("hard"),
    random("");

    private String difficulty;

    QuizAPIDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @JsonValue
    public String getDifficulty() {
        return difficulty;
    }
}
