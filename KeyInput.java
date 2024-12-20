package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.Game.STATE;

public class KeyInput extends KeyAdapter{

	private Handler handler;
	private GameObject player;
	private boolean[] keyDown = new boolean[4];

	Game game;
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
		
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				player = handler.object.get(i);
				//key events p1ayer
				
			if(player.y > 0) { 
				if(key == KeyEvent.VK_W) {tempObject.setVelY(-5); keyDown[0] = true; } }
			
			if(player.y < Game.HEIGHT) {
				if(key == KeyEvent.VK_S) { tempObject.setVelY(5); keyDown[1] = true; } }
			
			if(player.x < Game.WIDTH) {
				if(key == KeyEvent.VK_D) { tempObject.setVelX(5); keyDown[2] = true; } }
				
			if(player.x > 0) {
				if(key == KeyEvent.VK_A) { tempObject.setVelX(-5); keyDown[3] = true; } }
			}
		}
		//pause
		if(key == KeyEvent.VK_P) {
			if(game.gameState == STATE.Game) {
				if(Game.paused) Game.paused = false;
				else Game.paused = true;
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE)System.exit(1); 
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//key events p1
			if(key == KeyEvent.VK_W) keyDown[0] = false; //tempObject.setVelY(0);
			if(key == KeyEvent.VK_S) keyDown[1] = false; //tempObject.setVelY(0);
			if(key == KeyEvent.VK_D) keyDown[2] = false; //tempObject.setVelX(0);
			if(key == KeyEvent.VK_A) keyDown[3] = false; //tempObject.setVelX(0);
			
			//vertical movement
			if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
			//horizontal movement
			if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
			
			
			}
		}	
	}
}
