package main.java.fr.enseeiht.lbs.controller;


import javax.swing.*;
import java.awt.*;

public class BtnReloadMapGUI extends JPanel {

    public BtnReloadMapGUI() {
        this.setPreferredSize(new Dimension(80, 60));
        this.add(new ReloadMapButtonController());
        this.setVisible(true);
    }
}
