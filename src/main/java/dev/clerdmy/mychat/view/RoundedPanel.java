package dev.clerdmy.mychat.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedPanel extends JPanel {

    public RoundedPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, GUIConstants.ANGLE, GUIConstants.ANGLE);
        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        return new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), GUIConstants.ANGLE, GUIConstants.ANGLE).contains(x, y);
    }
}