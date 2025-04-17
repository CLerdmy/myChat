package dev.clerdmy.mychat.session;

import dev.clerdmy.mychat.model.User;

import java.io.IOException;

public interface SessionListener {

    void sessionChanged(User newUser) throws IOException;

    default void subscribesChanged(User user) throws IOException {}

}
