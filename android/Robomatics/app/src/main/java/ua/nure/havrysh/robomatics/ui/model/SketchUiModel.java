package ua.nure.havrysh.robomatics.ui.model;

public class SketchUiModel {
    private String id;
    private String name;
    private String code;
    private String authorName;
    private int linesCount;
    private String ownerId;

    public SketchUiModel(String id, String name, String code, String authorName, int linesCount, String ownerId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.authorName = authorName;
        this.linesCount = linesCount;
        this.ownerId = ownerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getLinesCount() {
        return linesCount;
    }

    public void setLinesCount(int linesCount) {
        this.linesCount = linesCount;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
