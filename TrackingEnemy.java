package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class TrackingEnemy extends GameObject{

	private Handler handler;
	private GameObject player;
	private float accelerationFactor = 0.01f;  // Lower acceleration factor for smoother movement
	private float maxSpeed = 2.5f;            // Cap max speed to avoid overshooting
	private float minSpeed = 1.0f;            // Ensure the enemy keeps moving at least a little
	private float dampingFactor = 0.9f;       // Damping factor to smooth velocity changes

	
	public TrackingEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
		}
		
		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 20, 20);
	}
	
	@Override
	public void tick() {
	    float diffX = x - player.getX() - 8;
	    float diffY = y - player.getY() - 8;
	    float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + 
	                                       (y - player.getY()) * (y - player.getY()));

	    // direction towards the player
	    float directionX = (float) (-diffX / distance);
	    float directionY = (float) (-diffY / distance);

	    // Increase speed proportionally to distance but with controlled acceleration
	    float targetVelX = directionX * Math.min(distance * accelerationFactor, maxSpeed);
	    float targetVelY = directionY * Math.min(distance * accelerationFactor, maxSpeed);
	    
	    // Gradually adjust velocity using damping to avoid abrupt changes
	    velX = velX * dampingFactor + targetVelX * (1 - dampingFactor);
	    velY = velY * dampingFactor + targetVelY * (1 - dampingFactor);

	    // Cap the overall speed (just to ensure smooth movement and no extreme speeds)
	    float currentSpeed = (float) Math.sqrt(velX * velX + velY * velY);
	    if (currentSpeed > maxSpeed) {
	        velX = (velX / currentSpeed) * maxSpeed;
	        velY = (velY / currentSpeed) * maxSpeed;
	    } else if (currentSpeed < minSpeed && distance > 0) {
	        velX = (velX / currentSpeed) * minSpeed;
	        velY = (velY / currentSpeed) * minSpeed;
	    }

	    // Update position
	    x += velX;
	    y += velY;

	    // Reverse velocity if it hits the game boundaries
	    if (y <= 0 || y >= Game.HEIGHT - 60) velY *= -1;
	    if (x <= 0 || x >= Game.WIDTH - 40) velX *= -1;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect((int)x, (int)y, 20, 20);
	}

}
