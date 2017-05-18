package ua.nure.havrysh.robomatics.domain.model;

public class Sketch {
    private String id;

    private String title;

    private String code;

    private String sketchOwner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSketchOwner() {
        return sketchOwner;
    }

    public void setSketchOwner(String sketchOwner) {
        this.sketchOwner = sketchOwner;
    }
}
