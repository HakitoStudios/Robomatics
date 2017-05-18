package ua.nure.havrysh.robomatics.domain.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {

    private int stub;
    private String name;
    private String login;
    private HashMap<String, Object> sketches;

    public int isStub() {
        return stub;
    }

    public void setStub(int stub) {
        this.stub = stub;
    }

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
        return new ArrayList<>(sketches.keySet());
    }

    public void setSketches(HashMap<String, Object> sketches) {
        this.sketches = sketches;
    }

    public void addSketch(String id) {
        sketches.put(id, new Object());
    }
}
