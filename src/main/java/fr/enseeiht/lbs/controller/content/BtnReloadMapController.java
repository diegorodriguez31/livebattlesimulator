package main.java.fr.enseeiht.lbs.controller.content;

import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.model.world.WorldElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;
import java.security.PublicKey;

public class BtnReloadMapController extends JPanel {
    World model;
    private PropertyChangeSupport propertyChangeSupport;


    public BtnReloadMapController(World model){
        this.model = model;
        var reloadButton = new JButton("Reload");
        this.add(reloadButton);
        reloadButton.addActionListener(actionEvent ->{
            updateMap();

        });
    }

    public void updateMap() {

        }
    }





