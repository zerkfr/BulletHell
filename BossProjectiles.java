package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossProjectiles extends GameObject {

    private Handler handler;
    private double angle;

    public BossProjectiles(int x, int y, ID id, Handler handler, double angle) {
        super(x, y, id);
        this.handler = handler;
    
        velX = (int) (5 * Math.cos(angle)); // Speed of 5
        velY = (int) (5 * Math.sin(angle)); // Speed of 5
    }

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 10, 10);
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
			
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 20, 20);
	}

}
