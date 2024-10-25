package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game.STATE;

public class MenuScreen extends MouseAdapter{
    
    private Game game;
	private Handler handler;

    public MenuScreen(Game game, Handler handler){
        this.game = game;
        this.handler = handler;

    }

public void mousePressed(MouseEvent event){
    int mouseX = event.getX();
    int mouseY = event.getY(); 

    }


    public void tick(){

    }

    public void render(Graphics g){
        if(game.gameState == STATE.Menu){
            
            g.setFont(new Font("arial", 1, 50));
            g.setColor(Color.white);
            g.drawString("Start", game.WIDTH/2-70, 200);
            


        }
    }



}
