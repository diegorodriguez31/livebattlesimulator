package main.java.fr.enseeiht.lbs.controller.gui;



import main.java.fr.enseeiht.lbs.controller.content.BtnReloadMapController;
import main.java.fr.enseeiht.lbs.model.world.World;

import javax.swing.*;
import java.awt.*;

public class BtnReloadMapGUI extends JPanel {
    public BtnReloadMapGUI(World model) throws HeadlessException {
        this.setPreferredSize(new Dimension(80,60));
        this.add(new BtnReloadMapController(model));
        this.setVisible(true);

    }
}
