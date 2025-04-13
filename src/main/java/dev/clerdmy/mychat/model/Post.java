package dev.clerdmy.mychat.model;

import java.time.LocalDateTime;
import java.util.List;

public class Post {

    private int ID;
    private String content;
    private User user;
    private LocalDateTime dateTime;
    private List<Comment> comments;
    private List<User> likes;

    public Post() {}

    public int getID() {
        return ID;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<User> getLikes() {
        return likes;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

}
