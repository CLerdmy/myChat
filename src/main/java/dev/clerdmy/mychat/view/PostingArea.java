package dev.clerdmy.mychat.view;

import dev.clerdmy.mychat.session.SessionManager;

import java.awt.*;
import java.io.IOException;

public class PostingArea extends RoundedScalableTextAreaWithButton {

    public PostingArea() {

        getTextArea().setFont(GUIConstants.MAIN_FONT);
        getTextArea().setForeground(GUIConstants.BLACK);
        getTextArea().setWrapStyleWord(true);
        getTextArea().setLineWrap(true);
        getTextArea().setEditable(true);
        getTextArea().setMargin(new Insets(GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS));

        getButton().setText("+");
        getButton().setForeground(GUIConstants.WHITE);
        getButton().setBackground(GUIConstants.ANOTHER_BLACK);
        getButton().setHoverColor(GUIConstants.LIGHTER_BLACK);
        getButton().setFont(GUIConstants.MAIN_FONT_BOLD_LARGE);

        getButton().addActionListener(_ -> {
            if (!getTextArea().getText().isEmpty()) {
                try {
                    SessionManager.registerPost(getTextArea().getText());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getTextArea().setText("");
            }
        });

    }

}