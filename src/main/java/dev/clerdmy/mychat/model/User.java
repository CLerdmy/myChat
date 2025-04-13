package dev.clerdmy.mychat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int ID;
    private String name;
    private String email;
    private String password;
    private List<Post> posts;
    private List<Comment> comments;
    private List<Post> likes;
    private List<User> friends;

    public User() {}

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Post> getLikes() {
        return likes;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setLikes(List<Post> likes) {
        this.likes = likes;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    @JsonIgnore
    public List<Integer> getFriendsID() {
        List<Integer> IDs = new ArrayList<>();
        for (User friend : friends) {
            IDs.add(friend.getID());
        }
        return IDs;
    }

}
