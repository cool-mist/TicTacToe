package me.ayrus.ttt.core.game.impl;

import static me.ayrus.ttt.core.mark.impl.Marks.O;
import static me.ayrus.ttt.core.mark.impl.Marks.X;
import static org.junit.Assert.fail;

import org.junit.Test;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.ISquare;
import me.ayrus.ttt.core.game.IGame;
import me.ayrus.ttt.core.player.impl.RandomAI;

public class GameTest {
    
    @Test
    public void testGame() {
        createAndRunDefaultGame(1000);
    }
    
    @Test
    public void testGetBoard_unmodifiable() {
        IGame  game  = createNewDefaultGame();
        IBoard board = game.getBoard();
        
        verifyBoardUnmodifiable(board);
    }

    private void verifyBoardUnmodifiable(IBoard board) {
        for(ISquare square : board.getSquares().values())
            verifySquareIsUnmodifiable(square);
    }

    private void verifySquareIsUnmodifiable(ISquare square) {
        try {
            square.setMark(X);
        }catch(IllegalAccessError iae) {
            return;
        }
        
        fail();
    }

    private void createAndRunDefaultGame(int iter) {
        for(int i = 0; i < iter; ++i) {
            
            IGame game = createNewDefaultGame();
            
            runGame(game);
        }
    }

    private IGame createNewDefaultGame() {
        return new DefaultGame(
                new RandomAI(X), 
                new RandomAI(O),
                new DefaultGamePolicy()
        );
    }

    private void runGame(IGame game) {
        int numberOfTurns = 0;
        
        while(!game.getResult().isGameOver()) {
            game.doNextTurn();
            numberOfTurns++;
            
            if(numberOfTurns > 10)
                throw new IllegalStateException("Game did not end!!");
        }
        
        if(numberOfTurns < 1) 
            throw new IllegalStateException("Game did not start!");
    }
}
