package me.ayrus.ttt.cli.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

import me.ayrus.ttt.cli.game.impl.DefaultCLIGameRunner;
import me.ayrus.ttt.cli.input.IMoveScanner;
import me.ayrus.ttt.cli.input.impl.MoveScanner;
import me.ayrus.ttt.cli.player.impl.CLIPlayer;
import me.ayrus.ttt.core.game.IGame;
import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.game.IGameRunner;
import me.ayrus.ttt.core.game.impl.DefaultGame;
import me.ayrus.ttt.core.game.impl.DefaultGamePolicy;
import me.ayrus.ttt.core.mark.impl.Marks;

public class DefaultCLIGameRunnerTest {

    @Test
    public void testCLIGame() {
        IMoveScanner scanner = createMoveScanner("a1\nb1\na2\nb2\na3");
        IGameRunner  runner  = new DefaultCLIGameRunner((board) -> {});
        
        IGame game = new DefaultGame(new CLIPlayer(Marks.X, scanner), 
                              new CLIPlayer(Marks.O, scanner), 
                              new DefaultGamePolicy());
        
        IGameResult result = runner.run(game);
        
        assertTrue(result.isGameOver());
        assertFalse(result.isGameDrawn());
        assertEquals(Marks.X, result.getWinner());
    }
    
    private IMoveScanner createMoveScanner(String str) {
        InputStream is = new ByteArrayInputStream( str.getBytes() );
        
        return new MoveScanner("abc", is);
    }
}
