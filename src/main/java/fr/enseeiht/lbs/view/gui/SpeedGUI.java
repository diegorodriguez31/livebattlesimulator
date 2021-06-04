package main.java.fr.enseeiht.lbs.view.gui;

import main.java.fr.enseeiht.lbs.controller.SpeedController;

import javax.swing.*;
import java.awt.*;

public class SpeedGUI extends JFrame {
    public SpeedGUI() throws HeadlessException {
        this.add(new SpeedController());
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
