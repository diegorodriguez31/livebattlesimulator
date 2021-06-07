package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.controller.ChoixMapButtonsController;
import main.java.fr.enseeiht.lbs.controller.ChoixMapPresetController;

import javax.swing.*;
import java.awt.*;

public class WorldChoiceView extends JPanel {

    public WorldChoiceView() {
        this.setLocation(100, 200);
        this.setSize(1200, 800);
        this.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));

        //Panneaux de choix à gauche
        ChoixMapPresetController choixMapPresetController = ChoixMapPresetController.getInstance();
        choixMapPresetController.init();
        choixMapPresetController.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        //Boutons en bas de page
        //Le bouton reload est cliqué afin d'initialiser un nouveau World !
        ChoixMapButtonsController navigation = new ChoixMapButtonsController();
        choixMapPresetController.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        navigation.setPreferredSize(new Dimension(1200, 50));

        //Panneau central (la carte)
        //Doit être créé après l'initialisation du world
        WorldView mapView = new WorldView();
        mapView.setPreferredSize(new Dimension(700, 700));
        mapView.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        this.setLayout(new BorderLayout());
        this.add(mapView, BorderLayout.CENTER);
        this.add(choixMapPresetController, BorderLayout.WEST);
        this.add(navigation, BorderLayout.SOUTH);

        this.setVisible(true);
    }
}