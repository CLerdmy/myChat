package dev.clerdmy.mychat.view;

import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {

    public SidebarPanel() {

        setPreferredSize(new Dimension(GUIConstants.SQUARE * 4, 0));
        setBackground(GUIConstants.ANOTHER_BLACK);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(GUIConstants.DEFAULT_WIDTH / 12, 0, 0, 0));

    }
}