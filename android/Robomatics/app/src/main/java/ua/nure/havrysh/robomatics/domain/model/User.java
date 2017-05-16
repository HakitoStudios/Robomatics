package ua.nure.havrysh.robomatics.domain.model;

import java.util.List;

public class User {
    private String name;

    private String login;

    private List<String> sketches;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<String> getSketches() {
        return sketches;
    }

    public void setSketches(List<String> sketches) {
        this.sketches = sketches;
    }
}
