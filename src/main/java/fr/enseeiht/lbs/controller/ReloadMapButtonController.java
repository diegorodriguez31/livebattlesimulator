package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.view.content.WorldView;

import javax.swing.*;
import java.beans.PropertyChangeSupport;

public class ReloadMapButtonController extends JPanel {
    World model;
    private final PropertyChangeSupport propertyChangeSupport;
    //public static final String PROPERTY_RELOAD_MAP = "Reload";


    public ReloadMapButtonController(World model) {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.model = model;
        var reloadButton = new JButton("Reload");
        this.add(reloadButton);
        reloadButton.addActionListener(actionEvent -> {
            addPropertyChangeListener(World.PROPERTY_RELOAD_MAP, new WorldView());
            System.out.println("reload");
            int choix = 0;
            int percentdesert, percentwater, percentrock, percentplain, percentforest;
            this.propertyChangeSupport.firePropertyChange(World.PROPERTY_RELOAD_MAP, null, this.propertyChangeSupport);
            //CBbox_Elem1Controller btn = new CBbox_Elem1Controller(model);
            choix = CBboxElem1Controller.getAct();
            switch (choix) {
                case 1:
                    System.out.println(choix + "1");
                    percentdesert = 0;
                    percentwater = 30;
                    percentforest = 30;
                    percentplain = 35;
                    percentrock = 5;
                case 2:
                    System.out.println(choix + "2");
                    percentdesert = 39;
                    percentwater = 5;
                    percentforest = 5;
                    percentrock = 5;
                    percentplain = 46;
                case 0:
                    System.out.println(choix + "0");
                    percentdesert = 10;
                    percentwater = 20;
                    percentforest = 25;
                    percentplain = 40;
                    percentrock = 5;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choix);
            }


            World.getInstance().generateWorld(percentdesert, percentwater, percentrock, percentforest, percentplain);
        });
    }

}





