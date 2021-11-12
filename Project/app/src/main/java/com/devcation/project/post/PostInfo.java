package com.devcation.project.post;

public class PostInfo {

    String title;
    String contents;
    String path;

    public PostInfo(String title, String contents, String path) {
        this.title = title;
        this.contents = contents;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
