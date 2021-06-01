package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;
import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.model.world.WorldElement;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;

@SuppressWarnings("serial")
public class BattleWorldView extends BattleView implements PropertyChangeListener {

    private static final int WORLD_TO_PIXEL = 11;

    public BattleWorldView(World world) {
        super();
        this.setLayout(new GridLayout(world.getSizeX(), world.getSizeY()));//construit une grille de la même taille que le tableau de char
        this.setVisible(true);
        for (int x = 0; x < world.getSizeX(); x++) {
            for (int y = 0; y < world.getSizeY(); y++) {
                JLabel worldCase = new JLabel();
                WorldElement worldElement = world.getTile(x, y); //c prend le character du tableau qui est à sa place
                worldCase.setBackground(WorldView.getCorrespondingColor(worldElement)); // la case est remplie de la couleur correspondante
                worldCase.setOpaque(true);
                this.add(worldCase);
            }
        }
    }

}
