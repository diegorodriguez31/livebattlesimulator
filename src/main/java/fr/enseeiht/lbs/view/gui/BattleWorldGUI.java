package main.java.fr.enseeiht.lbs.view.gui;

import main.java.fr.enseeiht.lbs.controller.SpeedController;
import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.view.content.BattleWorldView;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class BattleWorldGUI extends JFrame {

	public BattleWorldGUI(World world) {
		BattleWorldView battleWorldView = new BattleWorldView(world);

		this.setLayout(new BorderLayout());
		this.add(battleWorldView, BorderLayout.CENTER);
		this.add(new SpeedController(), BorderLayout.SOUTH);
		
		this.pack();
        this.setSize(new Dimension(500,500));
        this.setVisible(true);
	}
	
}
