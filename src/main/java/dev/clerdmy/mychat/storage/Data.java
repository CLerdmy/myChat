package dev.clerdmy.mychat.storage;

import dev.clerdmy.mychat.model.Comment;
import dev.clerdmy.mychat.model.Post;
import dev.clerdmy.mychat.model.User;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private List<User> users;
    private List<Post> posts;
    private List<Comment> comments;

    public Data() {
        this.users = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
