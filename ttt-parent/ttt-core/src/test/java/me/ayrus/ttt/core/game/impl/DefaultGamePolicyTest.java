package me.ayrus.ttt.core.game.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IBoardFactory;
import me.ayrus.ttt.core.game.IGamePolicy;
import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.impl.BoardFactory;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.mark.IMarkFactory;
import me.ayrus.ttt.core.mark.impl.MarkFactory;

public class DefaultGamePolicyTest {
    
    IGamePolicy   policy  = new DefaultGamePolicy();
    IBoardFactory factory = new BoardFactory();
    IMarkFactory  marks   = new MarkFactory();
    
    @Test
    public void testRowVictory() {
        IBoard board = factory.createNewBoard();
        set(0, 0, marks.X(), board);
        set(0, 1, marks.X(), board);
        set(0, 2, marks.X(), board);
        set(1, 0, marks.O(), board);
        set(1, 1, marks.O(), board);
        
        IGameResult result = policy.calculate(board);
        
        assertTrue  (result.isGameOver());
        assertEquals(marks.X(), result.getWinner());
    }
    
    @Test
    public void testColumnVictory() {
        IBoard board = factory.createNewBoard();
        set(0, 1, marks.X(), board);
        set(1, 1, marks.X(), board);
        set(2, 1, marks.X(), board);
        set(1, 0, marks.O(), board);
        set(1, 2, marks.O(), board);
        
        IGameResult result = policy.calculate(board);
        
        assertTrue  (result.isGameOver());
        assertEquals(marks.X(), result.getWinner());
    }
    
    @Test
    public void testPrimaryDiagonalVictory() {
        IBoard board = factory.createNewBoard();
        set(0, 0, marks.X(), board);
        set(1, 1, marks.X(), board);
        set(2, 2, marks.X(), board);
        set(1, 0, marks.O(), board);
        set(1, 2, marks.O(), board);
        
        IGameResult result = policy.calculate(board);
        
        assertTrue  (result.isGameOver());
        assertEquals(marks.X(), result.getWinner());
    }
    
    @Test
    public void testSecondarDiagonalVictory() {
        IBoard board = factory.createNewBoard();
        set(0, 2, marks.X(), board);
        set(1, 1, marks.X(), board);
        set(2, 0, marks.X(), board);
        set(1, 0, marks.O(), board);
        set(1, 2, marks.O(), board);
        
        IGameResult result = policy.calculate(board);
        
        assertTrue  (result.isGameOver());
        assertEquals(marks.X(), result.getWinner());
    }
    
    @Test
    public void testInProgress() {
        IBoard board = factory.createNewBoard();
        set(0, 1, marks.X(), board);
        set(1, 1, marks.X(), board);
        set(2, 2, marks.X(), board);
        set(1, 0, marks.O(), board);
        set(1, 2, marks.O(), board);
        
        IGameResult result = policy.calculate(board);
        
        assertFalse(result.isGameOver());
    }
    
    @Test
    public void testMatchDrawn() {
        IBoard board = factory.createNewBoard();
        set(0, 0, marks.X(), board);
        set(0, 1, marks.O(), board);
        set(0, 2, marks.X(), board);
        set(1, 0, marks.O(), board);
        set(1, 1, marks.X(), board);
        set(1, 2, marks.X(), board);
        set(2, 0, marks.O(), board);
        set(2, 1, marks.X(), board);
        set(2, 2, marks.O(), board);
        
        IGameResult result = policy.calculate(board);
        
        assertTrue(result.isGameOver());
        assertTrue(result.isGameDrawn());
    }
    
    private void set(int i, int j, IMark x, IBoard board) {
        board.find(i, j).setMark(x);
    }

}
