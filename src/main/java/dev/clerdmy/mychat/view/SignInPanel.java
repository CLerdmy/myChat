package dev.clerdmy.mychat.view;

import dev.clerdmy.mychat.session.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class SignInPanel extends JPanel {

    public SignInPanel(MainFrame frame) {

        setLayout((new GridBagLayout()));
        setBackground(GUIConstants.GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(GUIConstants.TEXT_INSETS * 3, 0, GUIConstants.TEXT_INSETS * 3, 0);

        Dimension fieldSize = new Dimension(GUIConstants.TEXT_FIELD_FRAME_WIDTH, GUIConstants.SQUARE);

        JLabel myChat = new JLabel("myChat");

        myChat.setForeground(GUIConstants.WHITE);
        myChat.setFont(GUIConstants.TEST_FONT);
        myChat.setHorizontalAlignment(SwingConstants.CENTER);

        add(myChat, gbc);

        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(GUIConstants.TEXT_INSETS / 2, 0, GUIConstants.TEXT_INSETS / 2, 0);

        JLabel hint = new JLabel("");

        hint.setForeground(GUIConstants.WHITE);
        hint.setPreferredSize(new Dimension(GUIConstants.TEXT_FRAME_WIDTH, GUIConstants.SQUARE / 2));
        hint.setFont(GUIConstants.MAIN_FONT);
        hint.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy++;
        add(hint, gbc);

        RoundedTextField loginField = new RoundedTextField("Login");

        loginField.setForeground(GUIConstants.BLACK);
        loginField.setBackground(GUIConstants.WHITE);
        loginField.setPlaceholderColor(GUIConstants.LIGHTER_GRAY);
        loginField.setBorder(BorderFactory.createEmptyBorder(GUIConstants.TEXT_INSETS / 2, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS / 2, GUIConstants.TEXT_INSETS));
        loginField.setPreferredSize(fieldSize);
        loginField.setFont(GUIConstants.MAIN_FONT);
        loginField.setAlignmentX(Component.CENTER_ALIGNMENT);
        gbc.gridy++;
        add(loginField, gbc);

        RoundedPasswordField passwordField = new RoundedPasswordField("Password");

        passwordField.setForeground(GUIConstants.BLACK);
        passwordField.setBackground(GUIConstants.WHITE);
        passwordField.setPlaceholderColor(GUIConstants.LIGHTER_GRAY);
        passwordField.setBorder(BorderFactory.createEmptyBorder(GUIConstants.TEXT_INSETS / 2, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS / 2, GUIConstants.TEXT_INSETS));
        passwordField.setPreferredSize(fieldSize);
        passwordField.setFont(GUIConstants.MAIN_FONT);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setEchoChar('*');
        gbc.gridy++;
        add(passwordField, gbc);

        RoundedPasswordField confirmPasswordField = new RoundedPasswordField("Confirm Password");

        confirmPasswordField.setForeground(GUIConstants.BLACK);
        confirmPasswordField.setBackground(GUIConstants.WHITE);
        confirmPasswordField.setPlaceholderColor(GUIConstants.LIGHTER_GRAY);
        confirmPasswordField.setBorder(BorderFactory.createEmptyBorder(GUIConstants.TEXT_INSETS / 2, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS / 2, GUIConstants.TEXT_INSETS));
        confirmPasswordField.setPreferredSize(fieldSize);
        confirmPasswordField.setFont(GUIConstants.MAIN_FONT);
        confirmPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setEchoChar('*');
        gbc.gridy++;
        add(confirmPasswordField, gbc);

        RoundedButton roundedButton = new RoundedButton("Next");

        roundedButton.setForeground(GUIConstants.WHITE);
        roundedButton.setBackground(GUIConstants.BLACK);
        roundedButton.setHoverColor(GUIConstants.LIGHTER_BLACK);
        roundedButton.setPreferredSize(fieldSize);
        roundedButton.setFont(GUIConstants.MAIN_FONT_BOLD);
        roundedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        roundedButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (loginField.getText().isEmpty()) {
                    hint.setText("Enter your login.");
                } else if (passwordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty()) {
                    hint.setText("Enter your password.");
                } else if (!passwordField.getText().equals(confirmPasswordField.getText())) {
                    hint.setText("Passwords do not match.");
                } else {
                    try {
                        if (SessionManager.login(loginField.getText(), passwordField.getText())) {
                            hint.setText("");
                            loginField.setText("");
                            passwordField.setText("");
                            confirmPasswordField.setText("");
                            frame.showScreen("MainPanel");
                        } else {
                            hint.setText("Invalid login or password.");
                        }
                    } catch (IOException ex) {
                        hint.setText(ex.toString());
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        gbc.gridy++;
        add(roundedButton, gbc);

        JLabel hyperLink = new JLabel("Don't have an account? Sign Up");

        hyperLink.setForeground(GUIConstants.WHITE);
        hyperLink.setPreferredSize(new Dimension(GUIConstants.TEXT_FRAME_WIDTH, GUIConstants.SQUARE / 2));
        hyperLink.setFont(GUIConstants.MAIN_FONT);
        hyperLink.setHorizontalAlignment(SwingConstants.CENTER);
        hyperLink.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                frame.showScreen("SignUp");
            }
            @Override public void mouseEntered(MouseEvent e) { hyperLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); }
            @Override public void mouseExited(MouseEvent e) {
                hyperLink.setCursor(Cursor.getDefaultCursor());
            }
        });
        gbc.gridy++;
        add(hyperLink, gbc);

    }

}