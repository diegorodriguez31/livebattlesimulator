package main.java.fr.enseeiht.lbs.controller.content;

import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;

@SuppressWarnings("serial")
public class SpeedController extends JPanel{

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#0.00");
	private static final float LOW_INCREMENT = 0.15f;
	private static final float HIGH_INCREMENT = 0.75f;
	
	private float deltaTimeMultiplier;
	
	Battle model;
	JButton slowerButton;
	JButton playButton;
	JButton fasterButton;

	public SpeedController() {
		this.model = Battle.getInstance();
		this.deltaTimeMultiplier = Battle.DEFAULT_DELTA_TIME_MULTIPLIER;

		slowerButton = new JButton("⏪");
		slowerButton.setEnabled(false);
		playButton = new JButton("▶");
		fasterButton = new JButton("⏩");
		fasterButton.setEnabled(false);

		this.setLayout(new GridLayout(1,  3));
		this.add(slowerButton);
		this.add(playButton);
		this.add(fasterButton);


		slowerButton.addActionListener(actionEvent -> {
			//Change local attribute value
			deltaTimeMultiplier = model.getDeltaTimeMultiplier();
			if (deltaTimeMultiplier <= Battle.DEFAULT_DELTA_TIME_MULTIPLIER){
				if (deltaTimeMultiplier - LOW_INCREMENT >= Battle.MIN_DELTA_TIME_MULTIPLIER )
					deltaTimeMultiplier -= LOW_INCREMENT;
			}else {
				deltaTimeMultiplier -= HIGH_INCREMENT;
			}
			//Update view and model
			model.setDeltaTimeMultiplier(deltaTimeMultiplier);
			updateButtons();
		});

		playButton.addActionListener(actionEvent -> {
			if (model.getDeltaTimeMultiplier() < Battle.MIN_DELTA_TIME_MULTIPLIER){
				model.setDeltaTimeMultiplier(deltaTimeMultiplier);
			}else {
				this.deltaTimeMultiplier = model.getDeltaTimeMultiplier();
				model.setDeltaTimeMultiplier(0.0f);
			}
			updateButtons();
		});

		fasterButton.addActionListener(actionEvent -> {
			//Change local attribute value
			deltaTimeMultiplier = model.getDeltaTimeMultiplier();
			if (deltaTimeMultiplier < Battle.DEFAULT_DELTA_TIME_MULTIPLIER){
				deltaTimeMultiplier += LOW_INCREMENT;
			}else {
				if (deltaTimeMultiplier + HIGH_INCREMENT <= Battle.MAX_DELTA_TIME_MULTIPLIER )
					deltaTimeMultiplier += HIGH_INCREMENT;
			}
			//Update view and model
			model.setDeltaTimeMultiplier(deltaTimeMultiplier);
			updateButtons();
		});
	}

	private void updateButtons() {
		float localDeltaTimeMultiplier = model.getDeltaTimeMultiplier();
		
		//If time is stopped
		if ( localDeltaTimeMultiplier < Battle.MIN_DELTA_TIME_MULTIPLIER ) {
			//update slower button
			slowerButton.setEnabled(false);
			//update play button
			playButton.setText("▶"); 			
			//update faster button
			fasterButton.setEnabled(false);
		}
		//if time is not stopped
		else {
			//update play button
			playButton.setText("⏸ (x"+ DECIMAL_FORMAT.format(deltaTimeMultiplier) + ")"); 
			//update slower button
			if (localDeltaTimeMultiplier - LOW_INCREMENT >= Battle.MIN_DELTA_TIME_MULTIPLIER) {	
				slowerButton.setEnabled(true);
			}else {
				slowerButton.setEnabled(false);				
			}
			//update faster button
			if (localDeltaTimeMultiplier + HIGH_INCREMENT <= Battle.MAX_DELTA_TIME_MULTIPLIER) {
				fasterButton.setEnabled(true);
			}else {
				fasterButton.setEnabled(false);
			}
		}
	}
}
