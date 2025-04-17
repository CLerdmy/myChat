package dev.clerdmy.mychat.view;

import dev.clerdmy.mychat.model.User;
import dev.clerdmy.mychat.session.SessionListener;
import dev.clerdmy.mychat.session.SessionManager;
import dev.clerdmy.mychat.storage.UserRequest;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class SubscribesPanel extends ScrollPanel implements SessionListener {

    public SubscribesPanel() throws IOException {

        fillContentPanel(getContentPanel());
        SessionManager.addSessionListener(this);

    }

    @Override
    public void fillContentPanel(JPanel contentPanel) throws IOException {

        if (contentPanel.getComponentCount() == 0) {
            SearchArea searchArea = new SearchArea(contentPanel);
            contentPanel.add(searchArea);
            contentPanel.add(Box.createVerticalStrut(GUIConstants.ANGLE / 2));
        }

        User user = SessionManager.getCurrentUser();
        if (user != null) {
            List<User> subscribes = UserRequest.findSubscribes(user);
            if (subscribes != null) {
                for (User subscribe : subscribes) {
                    contentPanel.add(new SubscribeArea(subscribe));
                    contentPanel.add(Box.createVerticalStrut(GUIConstants.ANGLE / 2));
                }
            }
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