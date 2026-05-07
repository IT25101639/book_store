package com.source.model;

public class Author {
    private int id;
    private String authorName;
    private String email;

    public Author() {}

    public Author(int id, String authorName, String email) {
        this.id = id;
        this.authorName = authorName;
        this.email = email;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
