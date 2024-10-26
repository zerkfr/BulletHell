package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss extends GameObject {

    private Handler handler;
    private long lastAttackTime = 0; // To track the last attack time
    private final long attackCooldown = 500; // Cooldown in milliseconds (2 seconds)
    private int attackAmount = 0;
    private int attackCycle = 0;
    private int projectileCount = 0;

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
            attack(); // Call attack method
            lastAttackTime = System.currentTimeMillis(); // Reset last attack time
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

}

