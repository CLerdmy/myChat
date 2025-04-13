package dev.clerdmy.mychat.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel panel;

    public MainFrame() {

        setUndecorated(false);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
