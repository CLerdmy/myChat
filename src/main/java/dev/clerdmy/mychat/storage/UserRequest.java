package dev.clerdmy.mychat.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.clerdmy.mychat.model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class UserRequest extends AbstractRequest<User> {

    private static final File FILE = new File(DataConstants.USERS_PATH);
    private static final TypeReference<List<User>> TYPE_REFERENCE = new TypeReference<>() {};
    private static final UserRequest INSTANCE = new UserRequest();

    private UserRequest() {}

    @Override
    protected File getFile() {
        return FILE;
    }

    @Override
    protected TypeReference<List<User>> getTypeReference() {
        return TYPE_REFERENCE;
    }

    public static User findByEmail(String email) throws IOException {
        return INSTANCE.loadAll().stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
    }

    public static User findByName(String name) throws IOException {
        return INSTANCE.loadAll().stream().filter(u -> u.getName().equals(name)).findFirst().orElse(null);
    }

    public static User findById(int id) throws IOException {
        return INSTANCE.loadAll().stream().filter(u -> u.getID() == id).findFirst().orElse(null);
    }

    public static List<User> findByRegex(String regex) throws IOException {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return INSTANCE.loadAll().stream().filter(u -> pattern.matcher(u.getName()).find()).collect(Collectors.toList());
    }

    public static boolean exists(String name, String email) throws IOException {
        return INSTANCE.loadAll().stream().anyMatch(u -> u.getName().equals(name) && u.getEmail().equals(email));
    }

    public static User correct(String email, String password) throws IOException {
        return INSTANCE.loadAll().stream().filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password)).findFirst().orElse(null);
    }

    public static List<User> findSubscribes(User user) throws IOException {
        return INSTANCE.loadAll().stream().filter(u -> user.getSubscribes().contains(u.getID())).toList();
    }

    public static void save(User user) throws IOException {
        INSTANCE.saveOne(user);
    }

    public static List<User> findAll() throws IOException {
        return INSTANCE.loadAll();
    }

    public static int getNextUserId() throws IOException {
        return INSTANCE.loadAll().size() + 1;
    }

    public static void update(User updatedUser) throws IOException {
        List<User> users = INSTANCE.loadAll();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getID() == updatedUser.getID()) {
                users.set(i, updatedUser);
                INSTANCE.saveAll(users);
                return;
            }
        }
    }

}
