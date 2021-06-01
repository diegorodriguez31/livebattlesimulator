package main.java.fr.enseeiht.lbs.view.gui;

import main.java.fr.enseeiht.lbs.controller.content.BtnReloadMapController;
import main.java.fr.enseeiht.lbs.controller.content.SpeedController;
import main.java.fr.enseeiht.lbs.controller.gui.BtnReloadMapGUI;
import main.java.fr.enseeiht.lbs.controller.gui.CBbox_ElemGUI;
import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.view.content.Choice_WorldView;
import main.java.fr.enseeiht.lbs.view.content.WorldView;

import javax.swing.*;
import java.awt.*;

public class WorldChoiceGUI extends JFrame {

    public WorldChoiceGUI(World world) {

        this.setLocation(100, 200);
        this.setSize(1200, 800);
        JFrame.setDefaultLookAndFeelDecorated(true);
        JPanel northpanel = new JPanel();
        JPanel southpanel = new JPanel();
        GroupLayout layout = new GroupLayout(southpanel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        southpanel.setLayout(layout);

        WorldView mapView = new WorldView(world);
        Choice_WorldView choices = new Choice_WorldView(world);
        northpanel.setPreferredSize(new Dimension(1200, 50));
        choices.setPreferredSize(new Dimension(300, 700));
        southpanel.setPreferredSize(new Dimension(1200, 50));
        mapView.setPreferredSize(new Dimension(700, 700));
        rootPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mapView.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        this.setLayout(new BorderLayout());
        this.add(northpanel, BorderLayout.NORTH);
        this.add(mapView, BorderLayout.CENTER);
        this.add(choices, BorderLayout.WEST);
        this.add(southpanel, BorderLayout.SOUTH);
        southpanel.setLayout(new BorderLayout());
        choices.setLayout((new BorderLayout()));
        choices.add(new CBbox_ElemGUI(world),BorderLayout.CENTER);


        southpanel.add(new BtnReloadMapGUI(world), BorderLayout.SOUTH);


        this.pack();


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void writetext(String text) {
        JTextArea texte = new JTextArea(text);
        texte.setEditable(false);
        texte.setBorder(BorderFactory.createEmptyBorder(8, 5, 20, 0));
        texte.setColumns(10);
        texte.setBackground(getBackground());
        texte.setLineWrap(true);
        texte.setRows(3);
        texte.setWrapStyleWord(true);
        this.add(texte);


    }
}