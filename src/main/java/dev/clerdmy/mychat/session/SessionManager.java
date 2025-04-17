package dev.clerdmy.mychat.session;

import dev.clerdmy.mychat.model.Post;
import dev.clerdmy.mychat.model.User;
import dev.clerdmy.mychat.storage.PostRequest;
import dev.clerdmy.mychat.storage.UserRequest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SessionManager {

    private static User currentUser = null;
    private static final List<SessionListener> listeners = new ArrayList<>();

    private static void notifyListeners() throws IOException {
        for (SessionListener listener : listeners) {
            listener.sessionChanged(currentUser);
        }
    }

    public static boolean login(String email, String password) throws IOException {
        currentUser = UserRequest.correct(email, password);
        notifyListeners();
        return currentUser != null;
    }

    public static boolean register(String name, String email, String password) throws IOException {
        if (UserRequest.exists(name, email)) {
            return false;
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setID(UserRequest.getNextUserId());

        UserRequest.save(user);
        currentUser = user;
        notifyListeners();

        return true;
    }

    public static void logout() throws IOException {
        currentUser = null;
        notifyListeners();
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static void registerPost(String content) throws IOException {
        Post post = new Post();
        post.setContent(content);
        post.setOwnerId(getCurrentUser().getID());
        post.setOwnerName(getCurrentUser().getName());
        post.setDateTime(LocalDateTime.now());
        post.setID(PostRequest.getNextPostId());
        PostRequest.save(post);
        notifyListeners();
    }

    public static void addSessionListener(SessionListener listener) {
        listeners.add(listener);
    }

    public static void notifySubscribesChanged(User user) throws IOException {
        for (SessionListener listener : listeners) {
            listener.subscribesChanged(user);
        }
    }

}
