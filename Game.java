package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import main.Game.STATE;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 6691247796639148462L;
	
	public static final int WIDTH = 800, HEIGHT = WIDTH / 12 * 9;
	private boolean running = false;
	private Thread thread;
	
	public static boolean paused = false;
    private BufferedImage backgroundImage;
	private Handler handler;
	private Random r;
	private Image image;

	public Game(){
		image = new Image();
		backgroundImage = image.getImage("pexels-instawally-176851.jpg");
		
		r = new Random();
		
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler, this));

		handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler)); 
		handler.addObject(new Enemy(r.nextInt(WIDTH-50), r.nextInt(HEIGHT-50), ID.BasicEnemy, handler)); 
		handler.addObject(new Enemy(r.nextInt(WIDTH-50), r.nextInt(HEIGHT-50), ID.BasicEnemy, handler)); 

		handler.addObject(new Enemy(r.nextInt(WIDTH-50), r.nextInt(HEIGHT-50), ID.BasicEnemy, handler)); 

		

		
		new Window(WIDTH, HEIGHT, "Wave", this);

	}
	
	public enum STATE { //gamestates
		Menu,
		Help,
		Game,
		End
	};
	
	public static STATE gameState = STATE.Menu;
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus(); // dont need to click for kboard input
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) 
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT, this);
        }
		
		handler.render(g);

		
		g.dispose();
		bs.show();
	}
	
	
	public static void main(String[] args) {
		new Game() ;
	}

}
