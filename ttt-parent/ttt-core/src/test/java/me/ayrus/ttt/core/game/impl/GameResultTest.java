package me.ayrus.ttt.core.game.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.mark.impl.MarkFactory;

public class GameResultTest {
    
    IMark O = new MarkFactory().O();
    IMark X = new MarkFactory().X();

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
        createGameResult(false, false, O).getWinner();
    }
    
    @Test(expected = IllegalAccessError.class)
    public void testGameWinner_gameDrawn() {
        createGameResult(true, true, O).getWinner();
    }
    
    @Test
    public void testGameWinner() {
        assertEquals(O, createGameResult(true, false, O).getWinner());
        assertEquals(X, createGameResult(true, false, X).getWinner());
    }
    
    private IGameResult createGameResult(boolean b, boolean c, IMark object) {
        return new GameResult(b, c, object);
    }
}
