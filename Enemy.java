package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject{

	private Handler handler;
	
	public Enemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		int a = 5;
		int b = -5;
		int randomOfTwoInts = new Random().nextBoolean() ? a : b;
		int randomOfTwoInts2 = new Random().nextBoolean() ? a : b;
		
		velX = randomOfTwoInts;
		velY = randomOfTwoInts2;
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 62) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 40) velX *= -1;
			
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect((int)x, (int)y, 32, 32);
	}

}
