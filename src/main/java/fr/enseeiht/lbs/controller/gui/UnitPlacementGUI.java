package main.java.fr.enseeiht.lbs.controller.gui;

import main.java.fr.enseeiht.lbs.controller.content.UnitPlacement;
import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.model.battleSimulator.Extermination;
import main.java.fr.enseeiht.lbs.model.world.World;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UnitPlacementGUI extends JFrame {
    public UnitPlacementGUI(World world) {
        this.setLayout(new BorderLayout());
        this.add(new UnitPlacement(world), BorderLayout.CENTER);
        this.pack();
        //this.setSize(new Dimension(500, 500));
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Battle battle = Battle.getInstance();
        World world = new World(20, 20, 35, 10, 5, 50);
        battle.init(new Extermination(), new ArrayList<>());
        UnitPlacementGUI controler = new UnitPlacementGUI(world);
    }
}
