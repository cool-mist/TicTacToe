package me.ayrus.ttt.core.game.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.mark.impl.Marks;

public class GameResultTest {
    
    @Test
    public void testGameOver() {
        assertFalse(createGameResult(false, false, null).isGameOver());
        assertTrue (createGameResult(true, false, null).isGameOver());
    }
    
    @Test(expected = IllegalAccessError.class)
    public void testGameDrawn_gameNotOver() {
        createGameResult(false, false, null).isGameDrawn();
    }
    
    @Test
    public void testGameDrawn() {
        assertFalse(createGameResult(true, false, null).isGameDrawn());
        assertTrue (createGameResult(true, true, null).isGameDrawn());
    }
    
    @Test(expected = IllegalAccessError.class)
    public void testGameWinner_gameNotOver() {
        createGameResult(false, false, Marks.O).getWinner();
    }
    
    @Test(expected = IllegalAccessError.class)
    public void testGameWinner_gameDrawn() {
        createGameResult(true, true, Marks.O).getWinner();
    }
    
    @Test
    public void testGameWinner() {
        assertEquals(Marks.O, createGameResult(true, false, Marks.O).getWinner());
        assertEquals(Marks.X, createGameResult(true, false, Marks.X).getWinner());
    }
    
    private IGameResult createGameResult(boolean b, boolean c, IMark object) {
        return new GameResult(b, c, object);
    }
}
