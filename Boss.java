package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss extends GameObject {

    private Handler handler;
    private int attackAmount = 0;
    private int attackCycle = 0;
    private int projectileCount = 0;
    private long lastAttackTime = 0; 
    private final long attackCooldown = 600;
    private int projectilesSpawned = 0; 
    private final int totalProjectiles = 20;
    private final long spawnDelay = 30;

    public Boss(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }


	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 100, 100);
	}
	
	@Override
    public void tick() {

        if (System.currentTimeMillis() - lastAttackTime > attackCooldown) {
            lastAttackTime = System.currentTimeMillis(); // Reset last attack time
            projectilesSpawned = 0; 
        }

        // spawn projectiles in a spiral
        if (projectilesSpawned < totalProjectiles) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastAttackTime >= projectilesSpawned * spawnDelay) {
                spawnSpiralProjectile(projectilesSpawned);
                projectilesSpawned++; // increment the counter after spawning
            }
        }
    }

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 100, 100);
	}

    private void spawnSpiralProjectile(int index) {
        double angle = 2 * Math.PI * index / totalProjectiles; // Calculate the angle for each projectile
        double radius = 50 + index * 10; // Increase radius for a spiral effect
        int projX = (int) (x + radius * Math.cos(angle)); // X position based on angle
        int projY = (int) (y + radius * Math.sin(angle)); // Y position based on angle

        handler.addObject(new BossProjectiles(projX+40, projY+50, ID.BossProjectiles, handler, angle)); // Spawn the projectile
    }

}

