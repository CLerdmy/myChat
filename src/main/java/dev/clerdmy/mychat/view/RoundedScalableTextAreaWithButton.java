package dev.clerdmy.mychat.view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;
import java.awt.geom.RoundRectangle2D;

public class RoundedScalableTextAreaWithButton extends JPanel {

    private final JTextArea textArea;
    private final RoundedButton button;

    public RoundedScalableTextAreaWithButton() {

        setLayout(new BorderLayout());
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setBackground(GUIConstants.WHITE);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

        textArea = new JTextArea("");
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setMargin(new Insets(GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS));

        button = new RoundedButton("B");
        int buttonSize = GUIConstants.SQUARE;
        button.setPreferredSize(new Dimension(buttonSize, buttonSize));
        button.setMinimumSize(new Dimension(buttonSize, buttonSize));
        button.setMaximumSize(new Dimension(buttonSize, buttonSize));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.add(button, BorderLayout.SOUTH);

        add(textArea, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);

        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { updatePanelSize(); }
            @Override public void removeUpdate(DocumentEvent e) { updatePanelSize(); }
            @Override public void changedUpdate(DocumentEvent e) { updatePanelSize(); }
        });

        addHierarchyBoundsListener(new HierarchyBoundsAdapter() {
            @Override
            public void ancestorResized(HierarchyEvent e) {
                updatePanelSize();
            }
        });

        addHierarchyListener(e -> updatePanelSize());
    }

    private void updatePanelSize() {
        if (getParent() == null) return;

        int availableWidth = getParent().getWidth() - getInsets().left - getInsets().right - GUIConstants.SQUARE;

        textArea.setSize(availableWidth, Short.MAX_VALUE);
        Dimension textPreferred = textArea.getPreferredSize();

        int totalHeight = textPreferred.height + getInsets().top + getInsets().bottom;

        setMaximumSize(new Dimension(Integer.MAX_VALUE, totalHeight));
        setPreferredSize(new Dimension(getWidth(), totalHeight));

        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth();
        int h = getHeight();

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, w - 1, h - 1, GUIConstants.ANGLE, GUIConstants.ANGLE);
        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        return new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), GUIConstants.ANGLE, GUIConstants.ANGLE).contains(x, y);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public RoundedButton getButton() {
        return button;
    }

}