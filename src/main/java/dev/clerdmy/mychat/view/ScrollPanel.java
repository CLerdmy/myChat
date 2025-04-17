package dev.clerdmy.mychat.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

public class ScrollPanel extends JPanel {

    private final JPanel contentPanel;
    private final JScrollPane scrollPane;

    public ScrollPanel() throws IOException {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        setBounds(0, 0, GUIConstants.DEFAULT_WIDTH / 2, GUIConstants.DEFAULT_HEIGHT / 2);

        contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(GUIConstants.BLACK);

        fillContentPanel(contentPanel);

        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(GUIConstants.SCROLL_BORDER, GUIConstants.SCROLL_BORDER, GUIConstants.SCROLL_BORDER, GUIConstants.SCROLL_BORDER));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getVerticalScrollBar().setBlockIncrement(50);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setMaximumSize(new Dimension(GUIConstants.SCROLL_SIZE, GUIConstants.SCROLL_SIZE));

        scrollPane.getViewport().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = scrollPane.getViewport().getWidth();
                contentPanel.setPreferredSize(new Dimension(width, contentPanel.getPreferredSize().height));
                contentPanel.revalidate();
            }
        });

        add(scrollPane);

    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void fillContentPanel(JPanel contentPanel) throws IOException {};

}