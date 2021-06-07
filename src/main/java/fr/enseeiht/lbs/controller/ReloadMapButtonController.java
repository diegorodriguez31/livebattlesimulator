package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.view.content.WorldView;

import javax.swing.*;
import java.beans.PropertyChangeSupport;

public class ReloadMapButtonController extends JPanel {
    World model;
    private final PropertyChangeSupport propertyChangeSupport;

    public ReloadMapButtonController(World model) {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.model = model;
        var reloadButton = new JButton("Reload");
        this.add(reloadButton);
        reloadButton.addActionListener(actionEvent -> {
            addPropertyChangeListener(World.PROPERTY_RELOAD_MAP, new WorldView());
            int choix = 0;
            int percentdesert, percentwater, percentrock, percentplain, percentforest, percentlava, percentsnow;
            this.propertyChangeSupport.firePropertyChange(World.PROPERTY_RELOAD_MAP, null, this.propertyChangeSupport);
            choix = ChoixMapButtonController.getAct();
            if (choix == 2) {//humide
                percentdesert = 3;
                percentwater = 30;
                percentforest = 26;
                percentplain = 35;
                percentrock = 0;
                percentlava = 3;
                percentsnow = 3;
            } else if (choix == 1) {//desertique
                percentdesert = 44;
                percentwater = 5;
                percentforest = 5;
                percentrock = 10;
                percentplain = 30;
                percentlava = 3;
                percentsnow = 3;
            }  else if (choix == 3) {//volcanique
                percentdesert = 20;
                percentwater = 10;
                percentforest = 0;
                percentrock = 20;
                percentplain = 32;
                percentlava = 36;
                percentsnow = 0;
            } else if (choix == 4) {//enneigné
                percentdesert = 0;
                percentwater = 6;
                percentforest = 30;
                percentrock = 10;
                percentplain = 17;
                percentlava = 0;
                percentsnow = 31;
            }else {//classique
                percentdesert = 10;
                percentwater = 20;
                percentforest = 24;
                percentplain = 33;
                percentrock = 5;
                percentlava = 5;
                percentsnow = 3;

            }
            //si rock est appuyé
            if (ChoixMapButtonController.rock) {
                percentdesert = percentdesert - 2;
                percentwater = percentwater - 2;
                percentforest = percentforest - 2;
                percentplain = percentplain - 2;
                percentrock = percentrock + 12;
                percentlava = percentlava -2;
                percentsnow = percentsnow -2;
            }


            World.getInstance().generateWorld(percentdesert, percentwater, percentrock, percentforest, percentplain, percentlava, percentsnow);
        });
    }

}





