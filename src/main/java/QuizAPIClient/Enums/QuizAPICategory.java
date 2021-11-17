package QuizAPIClient.Enums;

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
    devops("devops");

    private String category;

    QuizAPICategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
