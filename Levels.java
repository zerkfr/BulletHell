package main;
import java.util.Random;
public class Levels{
	private Handler handler;
	private Game game;
	private int counter = 0;	
	private Random r;
	private int level = 0;
	private Player player;
	
	public Levels(Game game, Handler handler, Player player) {
		
		this.handler = handler;
		this.game = game;
		this.player = player;
		r = new Random();
		
	}
	
	
	public void tick() {
		counter++;
		
		if(counter == 1) {
			level = 1;
			handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
		}
		if(counter == 200) {
			level = 2;
			handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
			handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
		}
		if(counter == 400) {
			level = 3;
			handler.addObject(new TrackingEnemy(r.nextInt(game.WIDTH-50), r.nextInt(game.HEIGHT-50), ID.TrackingEnemy, handler));
		}
		if(counter == 600) {
			level = 4;
			handler.addObject(new SpeedyEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SpeedyEnemy, handler));
		}
		if(counter == 800) {
			level = 5;
			handler.addObject(new SpeedyEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SpeedyEnemy, handler));
		}
		
		
		//BOSS ENCOUNTER 1
		if(counter == 1000) {
			level = 6;
			handler.clearEntities();
			player.resetPosition();
		}
		if(counter == 1050){
			Boss boss = new Boss((Game.WIDTH - 400), (Game.HEIGHT - 650), ID.Boss, handler);
			handler.addObject(boss);
		}
		if(counter == 1100) {
			handler.addObject(new TrackingEnemy(r.nextInt(game.WIDTH-50), r.nextInt(game.HEIGHT-50), ID.TrackingEnemy, handler));
		}
		
		
       if(counter == 1500) {
           handler.clearEntities();
           level = 7;
           handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
			handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
       }
      
       if(counter == 1600) {
           level = 8;
           handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
			handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
       }
      
       if(counter == 1800) {
           level = 9;
           handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
			handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
       }
      
		//BOSS ENCOUNTER 2
		if(counter == 2000) {
			level = 10;
			handler.clearEntities();
			player.resetPosition();
		}
		if(counter == 2050){
			Boss boss = new Boss((Game.WIDTH - 400), (Game.HEIGHT - 650), ID.Boss, handler);
			handler.addObject(boss);
		}
		if(counter == 2100) {
			handler.addObject(new TrackingEnemy(r.nextInt(game.WIDTH-50), r.nextInt(game.HEIGHT-50), ID.TrackingEnemy, handler));
		}
      
      
      
      
	}
	
	public String getLevel() {
		return Integer.toString(level);
	}
   public String getScore() {
       return Integer.toString(counter);
   }
	
	
}

