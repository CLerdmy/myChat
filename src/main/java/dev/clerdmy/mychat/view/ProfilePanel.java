package dev.clerdmy.mychat.view;

import dev.clerdmy.mychat.model.User;
import dev.clerdmy.mychat.session.SessionListener;
import dev.clerdmy.mychat.session.SessionManager;
import dev.clerdmy.mychat.storage.UserRequest;
import dev.clerdmy.mychat.utils.ValidationUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ProfilePanel extends ScrollPanel implements SessionListener {

    public ProfilePanel() throws IOException {

        fillContentPanel(getContentPanel());
        SessionManager.addSessionListener(this);

    }

    @Override
    public void fillContentPanel(JPanel contentPanel) {

        if (SessionManager.getCurrentUser() != null) {

            JPanel nameWrapper = new JPanel();
            nameWrapper.setLayout(new BoxLayout(nameWrapper, BoxLayout.X_AXIS));
            nameWrapper.setOpaque(false);
            nameWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, GUIConstants.SQUARE / 2));
            JLabel name = new JLabel("Name");
            name.setForeground(GUIConstants.WHITE);
            name.setFont(GUIConstants.MAIN_FONT);

            RoundedTextField userName = new RoundedTextField(SessionManager.getCurrentUser().getName());
            userName.setForeground(GUIConstants.BLACK);
            userName.setBackground(GUIConstants.WHITE);
            userName.setPlaceholderColor(GUIConstants.LIGHTER_GRAY);
            userName.setBorder(BorderFactory.createEmptyBorder(GUIConstants.TEXT_INSETS / 2, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS/ 2, GUIConstants.TEXT_INSETS));
            userName.setPreferredSize(new Dimension(GUIConstants.MIN_WIDTH, GUIConstants.SQUARE));
            userName.setMaximumSize(new Dimension(GUIConstants.MIN_WIDTH, GUIConstants.SQUARE));
            userName.setFont(GUIConstants.MAIN_FONT);
            userName.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel emailWrapper = new JPanel();
            emailWrapper.setLayout(new BoxLayout(emailWrapper, BoxLayout.X_AXIS));
            emailWrapper.setOpaque(false);
            emailWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, GUIConstants.SQUARE / 2));
            JLabel email = new JLabel("Email");
            email.setForeground(GUIConstants.WHITE);
            email.setFont(GUIConstants.MAIN_FONT);

            RoundedTextField userEmail = new RoundedTextField(SessionManager.getCurrentUser().getEmail());
            userEmail.setForeground(GUIConstants.BLACK);
            userEmail.setBackground(GUIConstants.WHITE);
            userEmail.setPlaceholderColor(GUIConstants.LIGHTER_GRAY);
            userEmail.setBorder(BorderFactory.createEmptyBorder(GUIConstants.TEXT_INSETS / 2, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS/ 2, GUIConstants.TEXT_INSETS));
            userEmail.setPreferredSize(new Dimension(GUIConstants.MIN_WIDTH, GUIConstants.SQUARE));
            userEmail.setMaximumSize(new Dimension(GUIConstants.MIN_WIDTH, GUIConstants.SQUARE));
            userEmail.setFont(GUIConstants.MAIN_FONT);
            userEmail.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel newPasswordWrapper = new JPanel();
            newPasswordWrapper.setLayout(new BoxLayout(newPasswordWrapper, BoxLayout.X_AXIS));
            newPasswordWrapper.setOpaque(false);
            newPasswordWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, GUIConstants.SQUARE / 2));
            JLabel newPassword = new JLabel("New Password");
            newPassword.setForeground(GUIConstants.WHITE);
            newPassword.setFont(GUIConstants.MAIN_FONT);

            RoundedTextField userNewPassword = new RoundedTextField("Enter a new password if you want to change it.");
            userNewPassword.setForeground(GUIConstants.BLACK);
            userNewPassword.setBackground(GUIConstants.WHITE);
            userNewPassword.setPlaceholderColor(GUIConstants.LIGHTER_GRAY);
            userNewPassword.setBorder(BorderFactory.createEmptyBorder(GUIConstants.TEXT_INSETS / 2, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS/ 2, GUIConstants.TEXT_INSETS));
            userNewPassword.setPreferredSize(new Dimension(GUIConstants.MIN_WIDTH, GUIConstants.SQUARE));
            userNewPassword.setMaximumSize(new Dimension(GUIConstants.MIN_WIDTH, GUIConstants.SQUARE));
            userNewPassword.setFont(GUIConstants.MAIN_FONT);
            userNewPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel passwordWrapper = new JPanel();
            passwordWrapper.setLayout(new BoxLayout(passwordWrapper, BoxLayout.X_AXIS));
            passwordWrapper.setOpaque(false);
            passwordWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, GUIConstants.SQUARE / 2));
            JLabel password = new JLabel("Password");
            password.setForeground(GUIConstants.WHITE);
            password.setFont(GUIConstants.MAIN_FONT);

            RoundedPasswordField userPassword = new RoundedPasswordField("To confirm the changes, enter your current password.");
            userPassword.setForeground(GUIConstants.BLACK);
            userPassword.setBackground(GUIConstants.WHITE);
            userPassword.setPlaceholderColor(GUIConstants.LIGHTER_GRAY);
            userPassword.setBorder(BorderFactory.createEmptyBorder(GUIConstants.TEXT_INSETS / 2, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS/ 2, GUIConstants.TEXT_INSETS));
            userPassword.setPreferredSize(new Dimension(GUIConstants.MIN_WIDTH, GUIConstants.SQUARE));
            userPassword.setMaximumSize(new Dimension(GUIConstants.MIN_WIDTH, GUIConstants.SQUARE));
            userPassword.setFont(GUIConstants.MAIN_FONT);
            userPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel hintWrapper = new JPanel();
            hintWrapper.setLayout(new BoxLayout(hintWrapper, BoxLayout.X_AXIS));
            hintWrapper.setOpaque(false);
            hintWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, GUIConstants.SQUARE / 2));
            JLabel hint = new JLabel("");
            hint.setForeground(GUIConstants.WHITE);
            hint.setFont(GUIConstants.MAIN_FONT);

            RoundedButton confirmButton = new RoundedButton("Confirm");
            confirmButton.setForeground(GUIConstants.WHITE);
            confirmButton.setBackground(GUIConstants.BLACK);
            confirmButton.setHoverColor(GUIConstants.LIGHTER_BLACK);
            confirmButton.setPreferredSize(new Dimension(GUIConstants.MIN_WIDTH, GUIConstants.SQUARE));
            confirmButton.setMaximumSize(new Dimension(GUIConstants.MIN_WIDTH, GUIConstants.SQUARE));
            confirmButton.setFont(GUIConstants.MAIN_FONT_BOLD_LARGE);
            confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            confirmButton.addActionListener(_ -> {
                if (userName.getText().isEmpty() && userEmail.getText().isEmpty() && userNewPassword.getText().isEmpty()) {
                    hint.setText("");
                } else if (userPassword.getText().isEmpty()) {
                    hint.setText("Enter your current password.");
                } else if (!userPassword.getText().equals(SessionManager.getCurrentUser().getPassword())) {
                    hint.setText("Passwords do not match.");
                } else if (!userEmail.getText().isEmpty() && !ValidationUtils.isValidEmail(userEmail.getText())) {
                    hint.setText("Invalid email format.");
                } else {
                    try {
                        User user = SessionManager.getCurrentUser();
                        user.setName(userName.getText().isEmpty() ? user.getName() : userName.getText());
                        user.setEmail(userEmail.getText().isEmpty() ? user.getEmail() : userEmail.getText());
                        user.setPassword(userNewPassword.getText().isEmpty() ? user.getPassword() : userNewPassword.getText());

                        UserRequest.update(user);
                        hint.setText("Successful!");

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            nameWrapper.add(Box.createHorizontalGlue());
            nameWrapper.add(name);
            nameWrapper.add(Box.createHorizontalGlue());
            emailWrapper.add(Box.createHorizontalGlue());
            emailWrapper.add(email);
            emailWrapper.add(Box.createHorizontalGlue());
            newPasswordWrapper.add(Box.createHorizontalGlue());
            newPasswordWrapper.add(newPassword);
            newPasswordWrapper.add(Box.createHorizontalGlue());
            passwordWrapper.add(Box.createHorizontalGlue());
            passwordWrapper.add(password);
            passwordWrapper.add(Box.createHorizontalGlue());
            hintWrapper.add(Box.createHorizontalGlue());
            hintWrapper.add(hint);
            hintWrapper.add(Box.createHorizontalGlue());

            contentPanel.add(nameWrapper);
            contentPanel.add(userName);
            contentPanel.add(Box.createVerticalStrut(GUIConstants.ANGLE / 2));
            contentPanel.add(emailWrapper);
            contentPanel.add(userEmail);
            contentPanel.add(Box.createVerticalStrut(GUIConstants.ANGLE / 2));
            contentPanel.add(newPasswordWrapper);
            contentPanel.add(userNewPassword);
            contentPanel.add(Box.createVerticalStrut(GUIConstants.ANGLE / 2));
            contentPanel.add(passwordWrapper);
            contentPanel.add(userPassword);
            contentPanel.add(Box.createVerticalGlue());
            contentPanel.add(hintWrapper);
            contentPanel.add(confirmButton);
        }

    }

    @Override
    public void sessionChanged(User newUser) {
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