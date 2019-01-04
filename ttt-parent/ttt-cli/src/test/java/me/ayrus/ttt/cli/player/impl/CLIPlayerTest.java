package me.ayrus.ttt.cli.player.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import me.ayrus.ttt.cli.input.IMoveScanner;
import me.ayrus.ttt.cli.player.impl.CLIPlayer;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.impl.Boards;
import me.ayrus.ttt.core.impl.DefaultBoard;
import me.ayrus.ttt.core.mark.impl.Marks;
import me.ayrus.ttt.core.player.IPlayer;

public class CLIPlayerTest {
    
    IPos mockPos = Mockito.mock(IPos.class);
    
    @Test
    public void testNextMove_useScanner() {
        IMoveScanner scanner   = createMockScanner();
        IPlayer      cliPlayer = new CLIPlayer(Marks.X, scanner);
        
        cliPlayer.setBoard(Boards.createUnmodifiableBoard(new DefaultBoard(), DefaultBoard::new));
        
        IPos pos = cliPlayer.nextMove();
        
        Mockito.verify(scanner, Mockito.times(1)).scanNextMove();
        assertEquals(mockPos, pos);
    }

    private IMoveScanner createMockScanner() {
        IMoveScanner scanner = Mockito.mock(IMoveScanner.class);
        Mockito.when(scanner.scanNextMove()).thenReturn(mockPos);
        return scanner;
    }

}
