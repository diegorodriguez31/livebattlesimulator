package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.controller.HomePageButtonController;
import main.java.fr.enseeiht.lbs.controller.UnitEditorController;
import main.java.fr.enseeiht.lbs.controller.UnitListController;
import main.java.fr.enseeiht.lbs.view.gui.GuiComponent;

import javax.swing.*;
import java.awt.*;

/**
 * Vue de l'éditeur d'unités.
 */
public class UnitEditorView extends JPanel implements GuiComponent {

    private static UnitEditorView instance;

    public static UnitEditorView getInstance() {
        if (instance == null) {
            instance = new UnitEditorView();
        }
        return instance;
    }
    public UnitEditorView() {
        // Components
        UnitListController list = new UnitListController();
        UnitEditorController editor = new UnitEditorController();

        // Linking components
        list.addSelectActionListener(actionEvent -> editor.setEditedUnit(actionEvent.getActionCommand()));

        // Home button
        JButton homeButton = new HomePageButtonController();

        // Layout
        list.setPreferredSize(new Dimension(200, 500));
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(homeButton);
        add(list, BorderLayout.WEST);
        add(editor, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
    }

    @Override
    public void reset() {

    }

    @Override
    public void init() {

    }
}
