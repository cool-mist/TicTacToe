package me.ayrus.ttt;

import me.ayrus.ttt.core.IGame;

public class Main {
    
    public static void main(String[] args) {
        IGame game = null;
        
        while(!game.isOver()) {
            game.toNextTurn();
        }
        
        if(game.isDrawn()) {
            
        }else {
            game.getWinner();
        }
    }

}
