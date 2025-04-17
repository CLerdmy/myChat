package dev.clerdmy.mychat.view;

import dev.clerdmy.mychat.session.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MainPanel extends JPanel {

    private final CardLayout cardLayout;
    private final JPanel panel;
    private final SidebarPanel sidebarPanel;

    public MainPanel(MainFrame frame) throws IOException {

        setLayout(new BorderLayout());
        setBackground(GUIConstants.GRAY);

        RoundedButton menuButton = new RoundedButton("≡");
        menuButton.setForeground(GUIConstants.WHITE);
        menuButton.setBackground(GUIConstants.GRAY);
        menuButton.setHoverColor(GUIConstants.LIGHTER_BLACK);
        menuButton.setFont(GUIConstants.MAIN_FONT_BOLD_LARGE);
        menuButton.setBounds(5, 5, GUIConstants.SQUARE, GUIConstants.SQUARE);
        add(menuButton);

        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);
        panel.setOpaque(false);

        FeedPanel feedPanel = new FeedPanel();
        PostsPanel postsPanel = new PostsPanel();
        SubscribesPanel subscribesPanel = new SubscribesPanel();
        ProfilePanel profilePanel = new ProfilePanel();

        panel.add(feedPanel, "FeedPanel");
        panel.add(postsPanel, "PostsPanel");
        panel.add(subscribesPanel, "SubscribesPanel");
        panel.add(profilePanel, "ProfilePanel");

        RoundedButton scrollButton = new RoundedButton("↑");
        scrollButton.setForeground(GUIConstants.WHITE);
        scrollButton.setBackground(GUIConstants.GRAY);
        scrollButton.setHoverColor(GUIConstants.LIGHTER_BLACK);
        scrollButton.setFont(GUIConstants.MAIN_FONT_BOLD_LARGE);

        scrollButton.addActionListener(_ -> {

            for (Component component : panel.getComponents()) {
                JScrollPane scrollPane = null;
                if (component instanceof PostsPanel) {
                    scrollPane = ((PostsPanel) component).getScrollPane();
                } else if (component instanceof FeedPanel) {
                    scrollPane = ((FeedPanel) component).getScrollPane();
                } else if (component instanceof SubscribesPanel) {
                    scrollPane = ((SubscribesPanel) component).getScrollPane();
                }
                if (scrollPane != null) {
                    smoothScrollToTop(scrollPane);
                }
            }
        });

        add(scrollButton);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int panelWidth = getWidth();
                int buttonSize = GUIConstants.SQUARE;
                int x = panelWidth - buttonSize - 5;
                int y = 5;
                scrollButton.setBounds(x, y, buttonSize, buttonSize);
            }
        });

        add(panel);
        cardLayout.show(panel, "FeedPanel");

        sidebarPanel = new SidebarPanel();

        RoundedButton feedButton = new RoundedButton("Feed");
        feedButton.setForeground(GUIConstants.WHITE);
        feedButton.setBackground(GUIConstants.ANOTHER_BLACK);
        feedButton.setHoverColor(GUIConstants.LIGHTER_BLACK);
        feedButton.setFont(GUIConstants.MAIN_FONT_LARGE);
        feedButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, GUIConstants.SQUARE));
        feedButton.addActionListener(_ -> cardLayout.show(panel, "FeedPanel"));
        sidebarPanel.add(feedButton);

        RoundedButton postsButton = new RoundedButton("Posts");
        postsButton.setForeground(GUIConstants.WHITE);
        postsButton.setBackground(GUIConstants.ANOTHER_BLACK);
        postsButton.setHoverColor(GUIConstants.LIGHTER_BLACK);
        postsButton.setFont(GUIConstants.MAIN_FONT_LARGE);
        postsButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, GUIConstants.SQUARE));
        postsButton.addActionListener(_ -> cardLayout.show(panel, "PostsPanel"));
        sidebarPanel.add(postsButton);

        RoundedButton friendsButton = new RoundedButton("Subscribes");
        friendsButton.setForeground(GUIConstants.WHITE);
        friendsButton.setBackground(GUIConstants.ANOTHER_BLACK);
        friendsButton.setHoverColor(GUIConstants.LIGHTER_BLACK);
        friendsButton.setFont(GUIConstants.MAIN_FONT_LARGE);
        friendsButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, GUIConstants.SQUARE));
        friendsButton.addActionListener(_ -> cardLayout.show(panel, "SubscribesPanel"));
        sidebarPanel.add(friendsButton);

        RoundedButton accountButton = new RoundedButton("Profile");
        accountButton.setForeground(GUIConstants.WHITE);
        accountButton.setBackground(GUIConstants.ANOTHER_BLACK);
        accountButton.setHoverColor(GUIConstants.LIGHTER_BLACK);
        accountButton.setFont(GUIConstants.MAIN_FONT_LARGE);
        accountButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, GUIConstants.SQUARE));
        accountButton.addActionListener(_ -> cardLayout.show(panel, "ProfilePanel"));
        sidebarPanel.add(accountButton);

        sidebarPanel.add(Box.createVerticalGlue());
        RoundedButton logout = new RoundedButton("Logout");
        logout.setForeground(GUIConstants.WHITE);
        logout.setBackground(GUIConstants.ANOTHER_BLACK);
        logout.setHoverColor(GUIConstants.RED);
        logout.setFont(GUIConstants.MAIN_FONT_LARGE);
        logout.setMaximumSize(new Dimension(Integer.MAX_VALUE, GUIConstants.SQUARE));

        logout.addActionListener(_ -> {
            try {
                SessionManager.logout();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            cardLayout.show(panel, "FeedPanel");
            sidebarPanel.setVisible(false);
            menuButton.setBackground(GUIConstants.GRAY);
            frame.showScreen("SignIn");
        });

        sidebarPanel.add(logout);

        sidebarPanel.setVisible(false);

        menuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sidebarPanel.setVisible(!sidebarPanel.isVisible());
                if (sidebarPanel.isVisible()) {
                    add(sidebarPanel, BorderLayout.WEST);
                    menuButton.setBackground(GUIConstants.ANOTHER_BLACK);
                } else {
                    remove(sidebarPanel);
                    menuButton.setBackground(GUIConstants.GRAY);
                }
                revalidate();
                repaint();
            }
        });

    }

    private void smoothScrollToTop(JScrollPane scrollPane) {
        Timer timer = new Timer(5, null);
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();

        timer.addActionListener(_ -> {
            int current = verticalBar.getValue();
            int step = Math.max((int)((current / 10.0) * 0.6), 1);
            verticalBar.setValue(current - step);
            if (verticalBar.getValue() == 0) {
                timer.stop();
            }
        });

        timer.start();
    }

}