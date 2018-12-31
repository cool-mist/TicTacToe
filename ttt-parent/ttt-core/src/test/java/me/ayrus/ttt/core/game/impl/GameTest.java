package me.ayrus.ttt.core.game.impl;

import static java.util.Arrays.asList;

import org.junit.Test;

import me.ayrus.ttt.core.game.IGame;
import me.ayrus.ttt.core.game.IGamePolicyFactory;
import me.ayrus.ttt.core.mark.IMarkFactory;
import me.ayrus.ttt.core.mark.impl.MarkFactory;
import me.ayrus.ttt.core.player.IPlayerFactory;
import me.ayrus.ttt.core.player.impl.PlayerFactory;

public class GameTest {
    
    @Test
    public void testGame() {
        createAndRunDefaultGame(1000);
    }

    private void createAndRunDefaultGame(int iter) {
        for(int i = 0; i < iter; ++i) {
            IMarkFactory       marks    = new MarkFactory();
            IPlayerFactory     players  = new PlayerFactory();
            IGamePolicyFactory policies = new GamePolicyFactory();
            
            IGame game = new Game(asList(
                                    players.createAIRandom(marks.X()), 
                                    players.createAIRandom(marks.O()))
                                 , policies.defaultPolicy());
            
            runGame(game);
        }
    }

    private void runGame(IGame game) {
        int numberOfTurns = 0;
        
        while(!game.getResult().isGameOver()) {
            game.doNextTurn();
            numberOfTurns++;
            
            if(numberOfTurns > 10)
                throw new IllegalStateException("Game did not end!!");
        }
    }
}
