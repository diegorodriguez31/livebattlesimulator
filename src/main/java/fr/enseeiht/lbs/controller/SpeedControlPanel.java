package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.battleSimulator.Battle;

import javax.swing.*;

public class SpeedControlPanel extends JPanel{
    Battle model;

    public SpeedControlPanel(Battle model) {
        this.model = model;
        var playButton = new JButton("⏸");
        this.add(playButton);
        playButton.addActionListener(actionEvent -> {
            if (model.getSpeedMut()==0.0f){
                model.setSpeedMut(1.0f);
                playButton.setText("⏸");
            }else {
                model.setSpeedMut(0.0f);
                playButton.setText("▶");
            }
        });
    }


}
