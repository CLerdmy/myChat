package dev.clerdmy.mychat.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {

    private int ID;
    private String content;
    private int ownerId;
    private String ownerName;
    private LocalDateTime dateTime;
    private List<Integer> likes = new ArrayList<>();

    public Post() {}

    public int getID() {
        return ID;
    }

    public String getContent() {
        return content;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<Integer> getLikes() {
        return likes;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setLikes(List<Integer> likes) {
        this.likes = likes;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

}
