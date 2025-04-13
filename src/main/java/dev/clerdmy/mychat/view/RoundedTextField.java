package dev.clerdmy.mychat.view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

public class RoundedTextField extends JTextField {

    private final String placeholder;

    private Color placeholderColor;
    private Color textColor;
    private boolean showingPlaceholder;

    public RoundedTextField(String placeholder) {

        this.placeholder = placeholder;
        setBorder(BorderFactory.createEmptyBorder()); // frame around
        setOpaque(false);

        showPlaceholder();

        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showingPlaceholder) {
                    setText("");
                    applyForeground(getTextColor());
                    showingPlaceholder = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    showPlaceholder();
                } else {
                    applyForeground(getTextColor());
                }
            }
        });

        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { checkForText(); }
            @Override public void removeUpdate(DocumentEvent e) { checkForText(); }
            @Override public void changedUpdate(DocumentEvent e) { checkForText(); }

            private void checkForText() {
                if (!getText().isEmpty() && showingPlaceholder) {
                    showingPlaceholder = false;
                    applyForeground(getTextColor());
                } else if (getText().isEmpty() && !hasFocus()) {
                    showPlaceholder();
                } else {
                    applyForeground(getTextColor());
                }
            }
        });

    }

    private void applyForeground(Color fg) {
        super.setForeground(fg);
    }

    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
        this.textColor = fg;
    }

    private Color getTextColor() {
        return textColor;
    }

    private void showPlaceholder() {
        setText(placeholder);
        super.setForeground(getPlaceholderColor());
        showingPlaceholder = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, GUIConstants.ANGLE, GUIConstants.ANGLE);

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    public String getText() {
        if (showingPlaceholder) {
            return "";
        }
        return super.getText();
    }

    @Override
    public boolean contains(int x, int y) {
        return new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), GUIConstants.ANGLE, GUIConstants.ANGLE).contains(x, y);
    }

    public Color getPlaceholderColor() {
        return placeholderColor;
    }

    public void setPlaceholderColor(Color placeholderColor) {
        this.placeholderColor = placeholderColor;
        applyForeground(placeholderColor);
    }

}
