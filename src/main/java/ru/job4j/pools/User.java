package ru.job4j.pools;

import java.util.Objects;

public class User {
    private String userName;
    private String email;

    public User(String userNme, String email) {
        this.userName = userNme;
        this.email = email;
    }

    public String getUserNme() {
        return userName;
    }

    public void setUserNme(String userNme) {
        this.userName = userNme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return getUserNme().equals(user.getUserNme()) && getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserNme(), getEmail());
    }
}
