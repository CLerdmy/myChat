package dev.clerdmy.mychat.view;

import dev.clerdmy.mychat.model.User;
import dev.clerdmy.mychat.session.SessionManager;
import dev.clerdmy.mychat.storage.UserRequest;

import java.awt.*;
import java.io.IOException;

public class SubscribeArea extends RoundedScalableTextAreaWithButton {

    private final User user;
    private boolean isSubscribe = false;

    public SubscribeArea(User user) {

        this.user = user;
        for (Integer id : SessionManager.getCurrentUser().getSubscribes()) {
            if (user.getID() == id) {
                this.isSubscribe = true;
                break;
            }
        }

        getTextArea().setText(user.getName());
        getTextArea().setFont(GUIConstants.MAIN_FONT);
        getTextArea().setForeground(GUIConstants.BLACK);
        getTextArea().setWrapStyleWord(true);
        getTextArea().setLineWrap(true);
        getTextArea().setEditable(false);
        getTextArea().setMargin(new Insets(GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS));

        getButton().setText(isSubscribe ? "-" : "+");
        getButton().setForeground(GUIConstants.WHITE);
        getButton().setBackground(GUIConstants.ANOTHER_BLACK);
        getButton().setHoverColor(isSubscribe ? GUIConstants.RED : GUIConstants.GREEN);
        getButton().setFont(GUIConstants.MAIN_FONT_BOLD_LARGE);

        getButton().addActionListener(_ -> {
            if (isSubscribe) {
                this.isSubscribe = false;
                getButton().setText("+");
                getButton().setHoverColor(GUIConstants.GREEN);
                for (int i = 0; i < SessionManager.getCurrentUser().getSubscribes().size(); i++) {
                    if (SessionManager.getCurrentUser().getSubscribes().get(i) == user.getID()) {
                        SessionManager.getCurrentUser().getSubscribes().remove(i);
                        break;
                    }
                }
                try {
                    UserRequest.update(SessionManager.getCurrentUser());
                    SessionManager.notifySubscribesChanged(SessionManager.getCurrentUser());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (!isSubscribe) {
                this.isSubscribe = true;
                getButton().setText("-");
                getButton().setHoverColor(GUIConstants.RED);
                SessionManager.getCurrentUser().getSubscribes().add(user.getID());
                try {
                    UserRequest.update(SessionManager.getCurrentUser());
                    SessionManager.notifySubscribesChanged(SessionManager.getCurrentUser());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

}