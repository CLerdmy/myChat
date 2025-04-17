package dev.clerdmy.mychat.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.clerdmy.mychat.model.Post;
import dev.clerdmy.mychat.model.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PostRequest extends AbstractRequest<Post> {

    private static final File FILE = new File(DataConstants.POSTS_PATH);
    private static final TypeReference<List<Post>> TYPE_REFERENCE = new TypeReference<>() {};
    private static final PostRequest INSTANCE = new PostRequest();

    private PostRequest() {}

    @Override
    protected File getFile() {
        return FILE;
    }

    @Override
    protected TypeReference<List<Post>> getTypeReference() {
        return TYPE_REFERENCE;
    }

    public static List<Post> findUserPosts(User user) throws IOException {
        if (user == null) return new ArrayList<>();
        List<Post> allPosts = INSTANCE.loadAll();
        List<Post> result = allPosts.stream().filter(post -> post.getOwnerId() == user.getID()).sorted(Comparator.comparing(Post::getDateTime).reversed()).collect(Collectors.toList());

        return result;
    }

    public static List<Post> findUserAndSubscribesPosts(User user) throws IOException {
        if (user == null) return new ArrayList<>();
        List<Post> allPosts = INSTANCE.loadAll();
        List<Integer> friends = user.getSubscribes();
        List<Post> result = allPosts.stream().filter(post -> post.getOwnerId() == user.getID() || friends.contains(post.getOwnerId())).sorted(Comparator.comparing(Post::getDateTime).reversed()).collect(Collectors.toList());

        return result;
    }

    public static int getNextPostId() throws IOException {
        return INSTANCE.loadAll().size() + 1;
    }

    public static void save(Post post) throws IOException {
        INSTANCE.saveOne(post);
    }

}
