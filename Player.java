package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import main.Game.STATE;

public class Player extends GameObject{
	
	private int health = 10;
	private final int defaultX = Game.WIDTH/2-30;
	private final int defaultY = Game.HEIGHT/2+60;
	private boolean resetPosition = false;
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
			if(tempObject.getId() == ID.Enemy || tempObject.getId() == ID.TrackingEnemy || tempObject.getId() == ID.SpeedyEnemy || tempObject.getId() == ID.Boss
			|| tempObject.getId() == ID.BossProjectiles) {
				if(getBounds().intersects(tempObject.getBounds())) {  //If intersects a hitbox 
					health--; 
					if(health == 0){
						handler.removeObject(this);
						Game.gameState = STATE.Dead;
						handler.clearEntities();
					}
					System.out.println("COLLISION!");
					
				}
			}
		}
	}

	public void resetPosition() {
		resetPosition = true;
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player){
				System.out.println("resetting");
				tempObject.setX(defaultX);
				tempObject.setY(defaultY);
				resetPosition = false;
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 48, 48);
	}
}
