package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.controller.UnitEditor;
import main.java.fr.enseeiht.lbs.controller.UnitListController;
import main.java.fr.enseeiht.lbs.view.gui.GuiComponent;
import main.java.fr.enseeiht.lbs.view.gui.LiveBattleSimulatorGUI;

import javax.swing.*;
import java.awt.*;

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
        UnitEditor editor = new UnitEditor();

        // Linking components
        list.addSelectActionListener(actionEvent -> editor.setEditedUnit(actionEvent.getActionCommand()));

        // Home button
        JButton homeButton = new JButton("Accueil");
        homeButton.addActionListener(actionEvent -> {
            LiveBattleSimulatorGUI.getInstance().showHomePage();
        });

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
