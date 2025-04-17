package dev.clerdmy.mychat.view;

import dev.clerdmy.mychat.model.Post;
import dev.clerdmy.mychat.model.User;
import dev.clerdmy.mychat.session.SessionListener;
import dev.clerdmy.mychat.session.SessionManager;
import dev.clerdmy.mychat.storage.PostRequest;

import javax.swing.*;
import java.io.IOException;

public class FeedPanel extends ScrollPanel implements SessionListener {

    public FeedPanel() throws IOException {

        fillContentPanel(getContentPanel());
        SessionManager.addSessionListener(this);

    }

    @Override
    public void fillContentPanel(JPanel contentPanel) throws IOException {

        for (Post post : PostRequest.findUserAndSubscribesPosts(SessionManager.getCurrentUser())) {
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

    @Override
    public void subscribesChanged(User user) throws IOException {
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