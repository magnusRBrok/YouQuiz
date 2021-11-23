package login;

import lombok.Data;

public @Data class User {
    String email;

    public User() {

    }

    public User(String email) {
        this.email = email;
    }
}
