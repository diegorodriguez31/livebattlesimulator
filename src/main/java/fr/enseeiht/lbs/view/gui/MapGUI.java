package main.java.fr.enseeiht.lbs.view.gui;

import javax.swing.JFrame;

import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.view.content.MapView;

@SuppressWarnings("serial")
public class MapGUI extends JFrame{


    public MapGUI(World world){
    	
        MapView mapView = new MapView(world);
        this.add(mapView);
        
        this.setLocation(100,200);
        this.setSize(600,600);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setVisible(true);
}


}