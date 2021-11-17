package QuizAPIClient.Enums;

public enum QuizAPIDifficulty {
    easy("easy"),
    medium("medium"),
    hard("hard");

    private String difficulty;

    QuizAPIDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
