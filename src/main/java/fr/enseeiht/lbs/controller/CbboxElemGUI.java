package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.model.world.World;

import javax.swing.*;
import java.awt.*;

public class CbboxElemGUI extends JPanel {
    public CbboxElemGUI() throws HeadlessException {
        this.setOpaque(false); //content panes must be opaque

        this.add(new ChoixMapButtonController(World.getInstance()));
        this.setPreferredSize(new Dimension(80, 60));

        this.setVisible(true);
    }
}
