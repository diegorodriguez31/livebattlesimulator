package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.view.content.WorldView;

import javax.swing.*;

public class ReloadMapButtonController extends JPanel {

    public ReloadMapButtonController() {
        World model = World.getInstance();
        var reloadButton = new JButton("Reload");
        this.add(reloadButton);
        reloadButton.addActionListener(actionEvent -> {
            addPropertyChangeListener(World.PROPERTY_RELOAD_MAP, new WorldView());
            int choix = 0;
            int percentdesert, percentwater, percentrock, percentplain, percentforest;
            choix = ChoixMapPresetController.getInstance().getActiveChoice();
            if(choix ==2) {
                percentdesert = 0;
                percentwater = 30;
                percentforest = 30;
                percentplain = 35;
                percentrock = 5;
            }else if(choix ==1){
                        percentdesert = 44;
                        percentwater = 5;
                        percentforest = 5;
                        percentrock = 10;
                        percentplain = 36;
                }else{
                    percentdesert = 10;
                    percentwater = 20;
                    percentforest = 25;
                    percentplain = 40;
                    percentrock = 5;

            }
            //si rock est appuyé
            if (ChoixMapPresetController.getInstance().hasRock()){
                percentdesert = percentdesert -3;
                percentwater = percentwater -3;
                percentforest = percentforest - 3;
                percentplain =percentplain -3;
                percentrock = percentrock + 12;
                System.out.println("plus de roches");
            }else{
                System.out.println("pas plus de roches");
            }


            World.getInstance().generateWorld(percentdesert, percentwater, percentrock, percentforest, percentplain);
        });
    }

}





