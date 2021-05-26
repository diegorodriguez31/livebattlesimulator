package main.java.fr.enseeiht.lbs.view.content;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import main.java.fr.enseeiht.lbs.gameObject.Entity;

@SuppressWarnings("serial")
public class BattleView extends JPanel implements PropertyChangeListener{

	private List<GraphicalEntity> graphicalEntities;

	public BattleView() {
		this.setVisible(true);
		this.graphicalEntities = new LinkedList<>();
	}

	@Override
	public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
		if (propertyChangeEvent.getPropertyName().equals("gameObjects"))
			modifiedGameObjectTreatement(propertyChangeEvent);

		this.repaint();
        Toolkit.getDefaultToolkit().sync();
	}

	private void modifiedGameObjectTreatement(PropertyChangeEvent propertyChangeEvent) {
		graphicalEntities.clear();
		for (Object gameObject : (List<?>) propertyChangeEvent.getNewValue()) {
			if (gameObject instanceof Entity) {
				Entity entity = (Entity) gameObject;
				this.graphicalEntities.add(new GraphicalEntity(entity.getPosition()));
			}
		}
	}

	@Override
	public void paint(Graphics graphics) {	
		super.paint(graphics);
		
		for (GraphicalEntity entityGraphic : this.graphicalEntities) {
			entityGraphic.paint(graphics);
		}
	}
}
