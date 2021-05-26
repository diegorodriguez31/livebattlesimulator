package main.java.fr.enseeiht.lbs.field;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Map {
    private JFrame window;
    public static final int Width = 600;
    public static final int Height = 600;


    public Map(World world){
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(Width,Height);
        window.setVisible(true);
        window.setLocation(100,200);
        Container contenu = this.window.getContentPane();
        contenu.setLayout(new GridLayout(world.getSizeX(), world.getSizeY()));//construit une grille de la même taille que le tableau de char

        for (int i = 0; i < world.getTab().length; i++) {
            for (int j = 0; j < world.getTab().length; j++) {
                JLabel lb = new JLabel();
                char c = world.getCase(world.getTab(), i,j); //c prend le character du tableau qui est à sa place
                WorldElem p = WorldElem.getValueChar(c);// p prend comme key 'c'
                Floor f = new Floor(p);//f nouvelle case
                lb.setBackground(f.getColor()); // la case est remplie de la couleur correspondante
                lb.setOpaque(true);
                contenu.add(lb);//on ajoute la case à la fenêtre
            }
        }

}

    public static void main(String[] args) {
        System.out.println("new world 1");
        World world1 = new World(5, 5, 40, 10, 0, 50);
        System.out.println("new world 2");
        World world2 = new World(10, 10, 55, 10, 5, 30);
        System.out.println("new world 3");
        World world3 =new World(20, 20, 10, 8, 2, 80);
        System.out.println("new world 4");
        World world4 =new World(50, 50, 10, 8, 2, 90);

        new Map(world4);
    }


}