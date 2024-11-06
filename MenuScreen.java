package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import main.Game.STATE;
public class MenuScreen extends MouseAdapter{
  
	private Player player;
    private Game game;
	private Handler handler;
	private Levels level;
    public MenuScreen(Game game, Handler handler, Levels level){
        this.game = game;
        this.handler = handler;
        this.level = level;
    }
public void mouseClicked(MouseEvent event){
   int mouseX = event.getX();
   int mouseY = event.getY();
   if(game.gameState == STATE.Menu) {
       game.gameState = STATE.Game;
   	player = new Player(game.WIDTH/2-30, game.HEIGHT/2+60, ID.Player, handler);
   	handler.addObject(player);
   }
   }
 
   public void render(Graphics g){
       if(game.gameState == STATE.Menu){
          
           g.setFont(new Font("AHDHfSDHGKDHSGKLJSDHG", 1, 50));
           g.setColor(Color.white);
           g.drawString("click anywhere", game.WIDTH/2-185, 300);
           g.drawString("to begin", game.WIDTH/2-110, 350);
           
           
           g.setColor(Color.red);
           g.drawString("dodge enemies to", game.WIDTH/2-230, 60);
           g.drawString("increase score", game.WIDTH/2-185, 110);


           
           g.setColor(Color.white);
           g.drawString("use WASD keys to move", game.WIDTH/2-305, 660);
       }
       
       
       if(game.gameState == STATE.Dead) {
           g.setFont(new Font("AHDHfSDHGKDHSGKLJSDHG", 1, 50));
    	   g.setColor(Color.red);
           g.drawString("YOU DIED HAHAHAHAHHAHAH", game.WIDTH/2-305, 200);
       }
   }
}
