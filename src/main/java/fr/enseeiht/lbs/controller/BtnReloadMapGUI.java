package main.java.fr.enseeiht.lbs.controller;


import main.java.fr.enseeiht.lbs.model.world.World;

import javax.swing.*;
import java.awt.*;

public class BtnReloadMapGUI extends JPanel {
    public BtnReloadMapGUI() throws HeadlessException {
        this.setPreferredSize(new Dimension(80, 60));
        this.add(new ReloadMapButtonController(World.getInstance()));
        this.setVisible(true);
    }
}
