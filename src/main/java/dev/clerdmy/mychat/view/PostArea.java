package dev.clerdmy.mychat.view;

import dev.clerdmy.mychat.model.Post;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;
import java.time.format.DateTimeFormatter;

public class PostArea extends JPanel {

    private final JTextArea textArea;

    public PostArea(Post post) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setOpaque(false);

        JPanel userDataWrapper = new JPanel();
        userDataWrapper.setLayout(new BoxLayout(userDataWrapper, BoxLayout.X_AXIS));
        userDataWrapper.setOpaque(false);
        userDataWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, GUIConstants.SQUARE / 2));

        JLabel userName = new JLabel(post.getOwnerName());
        userName.setForeground(GUIConstants.WHITE);
        userName.setFont(GUIConstants.MAIN_FONT);

        JLabel date = new JLabel(post.getDateTime().format(DateTimeFormatter.ofPattern("yyyy.dd.MM   HH:mm")));
        date.setForeground(GUIConstants.WHITE);
        date.setFont(GUIConstants.MAIN_FONT);

        userDataWrapper.add(userName);
        userDataWrapper.add(Box.createHorizontalGlue());
        userDataWrapper.add(date);
        add(userDataWrapper);

        RoundedPanel textWrapper = new RoundedPanel();
        textWrapper.setLayout(new BorderLayout());
        textWrapper.setBackground(GUIConstants.WHITE);
        textWrapper.setOpaque(false);

        textArea = new JTextArea(post.getContent());
        textArea.setFont(GUIConstants.MAIN_FONT);
        textArea.setForeground(GUIConstants.BLACK);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setMargin(new Insets(GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS));

        textWrapper.add(textArea, BorderLayout.CENTER);
        add(textWrapper);

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

        int availableWidth = getParent().getWidth() - getInsets().left - getInsets().right;

        textArea.setSize(availableWidth, Short.MAX_VALUE);
        Dimension textPreferred = textArea.getPreferredSize();

        int totalHeight = textPreferred.height + GUIConstants.SQUARE / 2;

        setMaximumSize(new Dimension(Integer.MAX_VALUE, totalHeight));
        setPreferredSize(new Dimension(getWidth(), totalHeight));

        revalidate();
        repaint();
    }
}