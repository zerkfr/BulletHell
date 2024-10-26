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
    private long lastAttackTime = 0; // Track the last attack time
    private final long attackCooldown = 600; // Cooldown in milliseconds (3 seconds)
    private int projectilesSpawned = 0; // Track how many projectiles have been spawned
    private final int totalProjectiles = 12; // Total projectiles for the spiral attack
    private final long spawnDelay = 50; // Delay between each projectile spawn (300 ms)

    public Boss(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }


	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 100, 100);
	}
	
	@Override
    public void tick() {

        // Check if the cooldown has passed
        if (System.currentTimeMillis() - lastAttackTime > attackCooldown) {
            lastAttackTime = System.currentTimeMillis(); // Reset last attack time
            projectilesSpawned = 0; // Reset the projectile counter for the new attack phase
        }

        // Spawn projectiles in a delayed spiral effect
        if (projectilesSpawned < totalProjectiles) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastAttackTime >= projectilesSpawned * spawnDelay) {
                spawnSpiralProjectile(projectilesSpawned);
                projectilesSpawned++; // Increment the counter after spawning
            }
        }
    }

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 100, 100);
	}

    public void attack() {
        attackAmount++;
        // Center angle in radians (pointing straight down)
        double centerAngle = Math.PI / 2; // 90 degrees
        // if(attackCycle<3){ add "phases" maybe
            if(attackAmount>1){
                projectileCount = 40; // Number of projectiles
                attackAmount=0;
                attackCycle++;
            }
            else{
                projectileCount = 20; // Number of projectiles
            }
        
        // Sweep angle range (in radians)
        double angleRange = Math.PI; // 
        double startAngle = centerAngle - angleRange; // 90° - 45° = 45°
        double endAngle = centerAngle + angleRange; // 90° + 45° = 135°

        // Calculate the angle step
        double angleStep = (endAngle - startAngle) / (projectileCount - 1);

        for (int i = 0; i < projectileCount; i++) {
            double angle = startAngle + i * angleStep; // Current angle
            handler.addObject(new BossProjectiles((int)x+40, (int)y+35, ID.BossProjectiles, handler, angle)); // Spawn the projectile
        }
    }

 private void spawnSpiralProjectile(int index) {
        double angle = 2 * Math.PI * index / totalProjectiles; // Calculate the angle for each projectile
        double radius = 50 + index * 10; // Increase radius for a spiral effect
        int projX = (int) (x + radius * Math.cos(angle)); // X position based on angle
        int projY = (int) (y + radius * Math.sin(angle)); // Y position based on angle

        handler.addObject(new BossProjectiles((int)x+40, (int)y+20, ID.BossProjectiles, handler, angle)); // Spawn the projectile
    }

}

