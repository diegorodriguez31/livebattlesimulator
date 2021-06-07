package main.java.fr.enseeiht.lbs.view.gui;

import main.java.fr.enseeiht.lbs.controller.UnitListController;

import javax.swing.*;
import java.awt.*;

public class UnitTypeListGUI extends JFrame {
    public UnitTypeListGUI() throws HeadlessException {
        super("test");
        var list = new UnitListController();
        list.setPreferredSize(new Dimension(100, 300));
        add(list);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new UnitTypeListGUI();
    }
}
