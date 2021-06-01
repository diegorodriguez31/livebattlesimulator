package main.java.fr.enseeiht.lbs.controller.gui;

import main.java.fr.enseeiht.lbs.controller.content.BtnReloadMapController;
import main.java.fr.enseeiht.lbs.controller.content.CBbox_Elem1Controller;
import main.java.fr.enseeiht.lbs.model.world.World;

import javax.swing.*;
import java.awt.*;

public class CBbox_ElemGUI extends JPanel {
    public CBbox_ElemGUI(World model) throws HeadlessException {
        this.setOpaque(false); //content panes must be opaque

        this.add(new CBbox_Elem1Controller(model));
        this.setPreferredSize(new Dimension(80, 60));

        this.setVisible(true);
    }
}
