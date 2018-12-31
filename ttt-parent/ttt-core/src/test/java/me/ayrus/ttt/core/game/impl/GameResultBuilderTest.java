package me.ayrus.ttt.core.game.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.game.IGameResultBuilder;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.mark.IMarkFactory;
import me.ayrus.ttt.core.mark.impl.MarkFactory;

public class GameResultBuilderTest {

    IGameResultBuilder builder = new GameResultBuilder();
    IMarkFactory       factory = new MarkFactory();

    @Test
    public void testGameDrawn() {
        IGameResult result = builder.drawn();

        assertTrue(result.isGameOver());
        assertTrue(result.isGameDrawn());
    }

    @Test
    public void testGameInProgress() {
        IGameResult result = builder.inProgress();

        assertFalse(result.isGameOver());
    }

    @Test
    public void testGameOver_X() {
        verifyGameOver(factory.X());
    }

    @Test
    public void testGameOver_O() {
        verifyGameOver(factory.O());
    }
    
    private void verifyGameOver(IMark m) {
        IGameResult result = builder.withWinner(m);

        assertTrue(result.isGameOver());
        assertEquals(m, result.getWinner());
    }
}
