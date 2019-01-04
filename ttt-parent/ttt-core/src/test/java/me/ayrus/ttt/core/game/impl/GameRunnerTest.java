package me.ayrus.ttt.core.game.impl;

import static java.util.Arrays.asList;
import static me.ayrus.ttt.core.mark.impl.Marks.O;
import static me.ayrus.ttt.core.mark.impl.Marks.X;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import me.ayrus.ttt.core.game.IGame;
import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.game.IGameRunner;
import me.ayrus.ttt.core.player.impl.RandomAI;

public class GameRunnerTest {
    
    @Test
    public void testGameRunner() {
        IGameRunner  runner = new GameRunner();
        
        IGame game = new DefaultGame(
                             new RandomAI(X), 
                             new RandomAI(O),
                             new DefaultGamePolicy()
                     );
        
        IGameResult result = runner.run(game);
        
        assertTrue(result.isGameOver());
    }

}
