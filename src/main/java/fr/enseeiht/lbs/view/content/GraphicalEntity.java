package main.java.fr.enseeiht.lbs.view.content;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import main.java.fr.enseeiht.lbs.model.gameObject.Vector2;

@SuppressWarnings("serial")
public class GraphicalEntity extends Component{
	
	private Vector2 position;
	
	public GraphicalEntity(Vector2 position) {
		this.position = new Vector2(position);
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		((Graphics2D) graphics).drawRect(((int)position.x)-5, ((int)position.y)-5, 11, 11);
	}
}
