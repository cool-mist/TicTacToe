package me.ayrus.ttt.core.game.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.game.IGamePolicy;
import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.impl.DefaultBoard;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.mark.impl.Marks;

public class DefaultGamePolicyTest {
    
    IGamePolicy policy = new DefaultGamePolicy();
    
    @Test
    public void testNewBoard() {
        IBoard      board  = new DefaultBoard();
        IGameResult result = policy.calculate(board);
        
        assertFalse(result.isGameOver());
    }
    
    @Test
    public void testRowVictory() {
        IBoard board = new DefaultBoard();
        set(0, 0, Marks.X, board);
        set(0, 1, Marks.X, board);
        set(0, 2, Marks.X, board);
        set(1, 0, Marks.O, board);
        set(1, 1, Marks.O, board);
        
        IGameResult result = policy.calculate(board);
        
        assertTrue  (result.isGameOver());
        assertEquals(Marks.X, result.getWinner());
    }
    
    @Test
    public void testColumnVictory() {
        IBoard board = new DefaultBoard();
        set(0, 1, Marks.X, board);
        set(1, 1, Marks.X, board);
        set(2, 1, Marks.X, board);
        set(1, 0, Marks.O, board);
        set(1, 2, Marks.O, board);
        
        IGameResult result = policy.calculate(board);
        
        assertTrue  (result.isGameOver());
        assertEquals(Marks.X, result.getWinner());
    }
    
    @Test
    public void testPrimaryDiagonalVictory() {
        IBoard board = new DefaultBoard();
        set(0, 0, Marks.X, board);
        set(1, 1, Marks.X, board);
        set(2, 2, Marks.X, board);
        set(1, 0, Marks.O, board);
        set(1, 2, Marks.O, board);
        
        IGameResult result = policy.calculate(board);
        
        assertTrue  (result.isGameOver());
        assertEquals(Marks.X, result.getWinner());
    }
    
    @Test
    public void testSecondarDiagonalVictory() {
        IBoard board = new DefaultBoard();
        set(0, 2, Marks.X, board);
        set(1, 1, Marks.X, board);
        set(2, 0, Marks.X, board);
        set(1, 0, Marks.O, board);
        set(1, 2, Marks.O, board);
        
        IGameResult result = policy.calculate(board);
        
        assertTrue  (result.isGameOver());
        assertEquals(Marks.X, result.getWinner());
    }
    
    @Test
    public void testInProgress() {
        IBoard board = new DefaultBoard();
        set(0, 1, Marks.X, board);
        set(1, 1, Marks.X, board);
        set(2, 2, Marks.X, board);
        set(1, 0, Marks.O, board);
        set(1, 2, Marks.O, board);
        
        IGameResult result = policy.calculate(board);
        
        assertFalse(result.isGameOver());
    }
    
    @Test
    public void testMatchDrawn() {
        IBoard board = new DefaultBoard();
        set(0, 0, Marks.X, board);
        set(0, 1, Marks.O, board);
        set(0, 2, Marks.X, board);
        set(1, 0, Marks.O, board);
        set(1, 1, Marks.X, board);
        set(1, 2, Marks.X, board);
        set(2, 0, Marks.O, board);
        set(2, 1, Marks.X, board);
        set(2, 2, Marks.O, board);
        
        IGameResult result = policy.calculate(board);
        
        assertTrue(result.isGameOver());
        assertTrue(result.isGameDrawn());
    }
    
    private void set(int i, int j, IMark x, IBoard board) {
        board.find(i, j).setMark(x);
    }

}
