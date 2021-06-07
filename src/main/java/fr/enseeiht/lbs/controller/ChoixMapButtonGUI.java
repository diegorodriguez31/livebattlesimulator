package main.java.fr.enseeiht.lbs.controller;

import javax.swing.*;
import java.awt.*;

public class ChoixMapButtonGUI extends JPanel {
    public ChoixMapButtonGUI() throws HeadlessException {
        this.setOpaque(false); //content panes must be opaque

        this.add(new ChoixMapPresetController());
        this.setPreferredSize(new Dimension(80, 60));

        this.setVisible(true);
    }
}
