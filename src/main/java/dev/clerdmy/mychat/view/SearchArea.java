package dev.clerdmy.mychat.view;

import dev.clerdmy.mychat.model.User;
import dev.clerdmy.mychat.session.SessionManager;
import dev.clerdmy.mychat.storage.UserRequest;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class SearchArea extends RoundedScalableTextAreaWithButton {

    public SearchArea(JPanel contentPanel) {

        getTextArea().setFont(GUIConstants.MAIN_FONT);
        getTextArea().setForeground(GUIConstants.BLACK);
        getTextArea().setWrapStyleWord(true);
        getTextArea().setLineWrap(true);
        getTextArea().setEditable(true);
        getTextArea().setMargin(new Insets(GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS));

        getButton().setText("OK");
        getButton().setForeground(GUIConstants.WHITE);
        getButton().setBackground(GUIConstants.ANOTHER_BLACK);
        getButton().setHoverColor(GUIConstants.LIGHTER_BLACK);
        getButton().setFont(GUIConstants.MAIN_FONT_BOLD);

        getButton().addActionListener(_ -> {

            contentPanel.removeAll();

            contentPanel.add(new SearchArea(contentPanel));
            contentPanel.add(Box.createVerticalStrut(GUIConstants.ANGLE / 2));

            if (SessionManager.getCurrentUser() == null) {
                contentPanel.revalidate();
                contentPanel.repaint();
                return;
            }

            if (getTextArea().getText().isEmpty()) {
                User user = SessionManager.getCurrentUser();
                if (user != null) {
                    List<User> subscribes = null;
                    try {
                        subscribes = UserRequest.findSubscribes(user);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (subscribes != null) {
                        for (User subscribe : subscribes) {
                            contentPanel.add(new SubscribeArea(subscribe));
                            contentPanel.add(Box.createVerticalStrut(GUIConstants.ANGLE / 2));
                        }
                    }
                }
            } else {
                String query = getTextArea().getText();
                try {
                    for (User u : UserRequest.findByRegex(query)) {
                        contentPanel.add(new SubscribeArea(u));
                        contentPanel.add(Box.createVerticalStrut(GUIConstants.ANGLE / 2));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            contentPanel.revalidate();
            contentPanel.repaint();

        });

    }

}