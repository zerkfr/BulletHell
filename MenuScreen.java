package main;

import java.awt.event.MouseAdapter;

import main.Game.STATE;

public class MenuScreen extends MouseAdapter{
    
    private Game game;
	private Handler handler;

    public MenuScreen(Handler handler, Game game){
        this.game = game;
        this.handler = handler;

    }

    public void tick(){

    }

    public void render(){
        if(game.gameState == STATE.Menu){
            
            


        }
    }



}
