package me.ayrus.ttt.core.player.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.player.IPlayer;

public class AbstractPlayerTest {
    
    @Test
    public void testGetMark() {
        IMark   mark   = mock(IMark.class);
        IPlayer player = getAbstractPlayer(mark);

        assertEquals(mark, player.getMark());
    }
    
    @Test(expected = NullPointerException.class)
    public void testNextMove_WithoutBoard() {
        IPlayer player = getAbstractPlayer(mock(IMark.class));
        
        player.nextMove();
    }
    
    @Test
    public void testNextMove() {
        IPlayer player = getAbstractPlayer(mock(IMark.class));
        player.setBoard(mock(IBoard.class));
        
        assertNotNull(player.nextMove());
    }

    private IPlayer getAbstractPlayer(IMark mark) {
        return new AbstractPlayer(mark) {
            
            @Override
            protected IPos doNextMove(IBoard board, IMark mark) {
                return mock(IPos.class);
            }
        };
    }

}
