package dev.clerdmy.mychat.view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel panel;

    public MainFrame() throws IOException {

        setUndecorated(false);
        setSize(GUIConstants.DEFAULT_WIDTH, GUIConstants.DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(GUIConstants.MIN_WIDTH, GUIConstants.MIN_HEIGHT));

        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        SignInPanel signIn = new SignInPanel(this);
        SignUpPanel signUp = new SignUpPanel(this);
        MainPanel mainPanel = new MainPanel(this);

        panel.add(signIn, "SignIn");
        panel.add(signUp, "SignUp");
        panel.add(mainPanel, "MainPanel");

        add(panel);
        cardLayout.show(panel, "SignIn");

        setVisible(true);

    }

    public void showScreen(String name) {
        cardLayout.show(panel, name);
    }

}