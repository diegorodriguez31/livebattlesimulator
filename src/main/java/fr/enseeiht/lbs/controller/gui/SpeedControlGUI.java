package main.java.fr.enseeiht.lbs.controller.gui;

import main.java.fr.enseeiht.lbs.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.controller.SpeedControlPanel;

import javax.swing.*;
import java.awt.*;

public class SpeedControlGUI extends JFrame {
    public SpeedControlGUI(Battle model) throws HeadlessException {
        this.add(new SpeedControlPanel(model));
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
