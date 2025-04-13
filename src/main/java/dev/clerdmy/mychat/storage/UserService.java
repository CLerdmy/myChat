package dev.clerdmy.mychat.storage;

import dev.clerdmy.mychat.model.User;
import dev.clerdmy.mychat.utils.JacksonUtil;

import java.io.IOException;
import java.util.List;

public class UserService {

    private static User currentUser = null;
    private Data data;

    public UserService() throws IOException {
        this.data = JacksonUtil.loadData();
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public boolean login(String login, String password) throws IOException {
        for (User user : data.getUsers()) {
            if (user.getEmail().equals(login) && user.getPassword().equals(password)) {
                setCurrentUser(user);
                return true;
            }
        }
        return false;
    }

    public boolean register(String name, String email, String password) throws IOException {
        for (User user : data.getUsers()) {
            if (user.getEmail().equals(email) || user.getName().equals(name)) {
                return false;
            }
        }

        int newId = data.getUsers().size() + 1;

        User newUser = new User();
        newUser.setID(newId);
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);

        data.getUsers().add(newUser);

        JacksonUtil.saveData(data);

        return true;
    }

    public List<User> getAllUsers() {
        return data.getUsers();
    }

}
