package main.java.fr.enseeiht.lbs.view.gui;

import main.java.fr.enseeiht.lbs.view.content.WorldView;

import javax.swing.*;

@SuppressWarnings("serial")
public class WorldGUI extends JFrame {


    public WorldGUI() {

        WorldView mapView = new WorldView();
        this.add(mapView);

        this.setLocation(100, 200);
        this.setSize(600, 600);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }


}