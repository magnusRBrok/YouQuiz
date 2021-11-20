package QuizAPIClient.Enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum QuizAPICategory {
    linux("linux"),
    bash("bash"),
    php("php"),
    docker("docker"),
    html("html"),
    mysql("mysql"),
    wordpress("wordpress"),
    laravel("laravel"),
    kubernetes("kubernetes"),
    javascript("javascript"),
    devops("devops"),
    random("");

    private String category;

    QuizAPICategory(String category) {
        this.category = category;
    }

    @JsonValue
    public String getCategory() {
        return category;
    }
}
