package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random();
	Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 48, 48);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		collision();
		
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Enemy || tempObject.getId() == ID.Enemy || tempObject.getId() == ID.TrackingEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {  //If we intersect with enemy rectangle bounds, do this 
					handler.removeObject(this);
					System.out.println("COLLISION!");
					
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 48, 48);
	}
}
