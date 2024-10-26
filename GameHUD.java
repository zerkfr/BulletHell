package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game.STATE;

public class GameHUD {

    private Game game;
    private Levels level;


    public GameHUD(Levels level, Game game){
        this.level = level;
        this.game = game;
    }

       public void render(Graphics g){
       if(game.gameState == STATE.Game){
           g.setFont(new Font("AHDHfSDHGKDHSGKLJSDHG", 1, 50));
           g.setColor(Color.white); 
           g.drawString("Score: " + level.getScore(), 20, 50);
          
       }
   }


    
}
