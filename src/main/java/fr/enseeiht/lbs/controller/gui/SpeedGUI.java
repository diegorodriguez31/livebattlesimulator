package main.java.fr.enseeiht.lbs.controller.gui;

import main.java.fr.enseeiht.lbs.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.controller.content.SpeedController;

import javax.swing.*;
import java.awt.*;

public class SpeedGUI extends JFrame {
    public SpeedGUI(Battle model) throws HeadlessException {
        this.add(new SpeedController(model));
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
