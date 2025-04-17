package dev.clerdmy.mychat.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int ID;
    private String name;
    private String email;
    private String password;
    private List<Integer> posts = new ArrayList<>();;
    private List<Integer> subscribes = new ArrayList<>();;

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

    public List<Integer> getPosts() {
        return posts;
    }

    public List<Integer> getSubscribes() {
        return subscribes;
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

    public void setPosts(List<Integer> posts) {
        this.posts = posts;
    }

    public void setSubscribes(List<Integer> subscribes) {
        this.subscribes = subscribes;
    }

}
