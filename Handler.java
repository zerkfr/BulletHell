package main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();

	public void tick() {
		for(int i = 0; i < object.size(); i++) {

			GameObject tempObject = object.get(i); 

			tempObject.tick();
			
		}
	}
	public void render(Graphics g) {
		
		for(int i = 0; i < object.size(); i++) {
				GameObject tempObject = object.get(i);
				tempObject.render(g);
		}
	}
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	public void clearEntities() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			object.clear();
			if(Game.gameState != Game.STATE.End)
			addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
			
		}
	}
}
