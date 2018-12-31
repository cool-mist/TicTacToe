package me.ayrus.ttt.core.game.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.mark.IMarkFactory;
import me.ayrus.ttt.core.mark.impl.MarkFactory;
import me.ayrus.ttt.core.player.IPlayer;

public class ValidationUtilsTest {

    IMarkFactory marks = new MarkFactory();
    
    @Test
    public void testOK() {
        List<IPlayer> players = create2Players(marks.X(), marks.O());
        ValidationUtils.validatePlayers(players);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDuplicates() {
        List<IPlayer> players = create2Players(marks.X(), marks.X());
        ValidationUtils.validatePlayers(players);
    }
    
    private List<IPlayer> create2Players(IMark x, IMark o) {
        return Arrays.asList(mockPlayer(x), mockPlayer(o));
    }

    private IPlayer mockPlayer(IMark x) {
        IPlayer player = Mockito.mock(IPlayer.class);
        Mockito.when(player.getMark()).thenReturn(x);
        
        return player;
    }
}
