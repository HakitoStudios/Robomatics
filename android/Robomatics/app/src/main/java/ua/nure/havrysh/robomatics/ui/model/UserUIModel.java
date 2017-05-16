package ua.nure.havrysh.robomatics.ui.model;

import java.util.List;

public class UserUIModel {
    private String name;

    private String id;

    private String avatar;

    private List<String> sketches;

    public UserUIModel(String name, String id, String avatar, List<String> sketches) {
        this.name = name;
        this.id = id;
        this.avatar = avatar;
        this.sketches = sketches;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getSketches() {
        return sketches;
    }

    public void setSketches(List<String> sketches) {
        this.sketches = sketches;
    }
}
