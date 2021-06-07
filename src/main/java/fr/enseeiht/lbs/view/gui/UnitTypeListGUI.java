package main.java.fr.enseeiht.lbs.view.gui;

import main.java.fr.enseeiht.lbs.controller.UnitEditor;
import main.java.fr.enseeiht.lbs.controller.UnitListController;

import javax.swing.*;
import java.awt.*;

public class UnitTypeListGUI extends JFrame {
    public UnitTypeListGUI() throws HeadlessException {
        super("test");

        JLabel comp = new JLabel();
        comp.setLayout(new BorderLayout());
        var list = new UnitListController();
        var editor = new UnitEditor();
        list.setPreferredSize(new Dimension(200, 500));
        list.addSelectActionListener(actionEvent -> editor.setEditedUnit(actionEvent.getActionCommand()));
        editor.setPreferredSize(new Dimension(500, 500));
        comp.add(list, BorderLayout.WEST);
        comp.add(editor, BorderLayout.CENTER);
        add(comp);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new UnitTypeListGUI();
    }
}
