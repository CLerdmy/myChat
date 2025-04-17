package dev.clerdmy.mychat.view;

import dev.clerdmy.mychat.model.Post;
import dev.clerdmy.mychat.model.User;
import dev.clerdmy.mychat.session.SessionListener;
import dev.clerdmy.mychat.session.SessionManager;
import dev.clerdmy.mychat.storage.PostRequest;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class PostsPanel extends ScrollPanel implements SessionListener {

    public PostsPanel() throws IOException {

        fillContentPanel(getContentPanel());
        SessionManager.addSessionListener(this);

    }

    @Override
    public void fillContentPanel(JPanel contentPanel) throws IOException {

        User currentUser = SessionManager.getCurrentUser();

        if (contentPanel.getComponentCount() == 0) {
            contentPanel.add(new PostingArea());
            contentPanel.add(Box.createVerticalStrut(GUIConstants.ANGLE / 2));
        }

        List<Post> posts = PostRequest.findUserPosts(currentUser);

        for (Post post : posts) {
            contentPanel.add(new PostArea(post));
            contentPanel.add(Box.createVerticalStrut(GUIConstants.ANGLE / 2));
        }

    }

    @Override
    public void sessionChanged(User newUser) throws IOException {
        getContentPanel().removeAll();
        fillContentPanel(getContentPanel());
        getContentPanel().setPreferredSize(null);
        getContentPanel().revalidate();
        getContentPanel().repaint();

        this.revalidate();
        this.repaint();
        SwingUtilities.invokeLater(() -> {
            getScrollPane().getVerticalScrollBar().setValue(0);
        });
    }

}