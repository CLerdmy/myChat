package dev.clerdmy.mychat.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class RoundedButton extends JButton {

    private Color hoverColor;
    private boolean hoveredActive = false;

    public RoundedButton(String text) {

        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setOpaque(false);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hoveredActive = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hoveredActive = false;
                repaint();
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (hoveredActive) {
            g2.setColor(getHoverColor());
        } else {
            g2.setColor(getBackground());
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), GUIConstants.ANGLE, GUIConstants.ANGLE);

        FontMetrics fontMetrics = g2.getFontMetrics();
        String text = getText();
        int textX = (getWidth() - fontMetrics.stringWidth(text)) / 2;
        int textY = (getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();

        g2.setColor(getForeground());
        g2.drawString(text, textX, textY);

        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        return new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), GUIConstants.ANGLE, GUIConstants.ANGLE).contains(x, y);
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    public Color getHoverColor() {
        return hoverColor;
    }

}
