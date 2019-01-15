package me.ayrus.ttt.ai.player.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import me.ayrus.ttt.core.game.IGame;
import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.game.IGameRunner;
import me.ayrus.ttt.core.game.impl.DefaultGame;
import me.ayrus.ttt.core.game.impl.DefaultGamePolicy;
import me.ayrus.ttt.core.game.impl.DefaultGameRunner;
import me.ayrus.ttt.core.mark.impl.Marks;

public class MiniMaxPlayerTest {
    
    // TODO: Should be able to run a large number of games.
    @Test
    public void testMiniMaxGamesEndInDraw() {
        runGames(1);
    }

    private void runGames(int numberOfRuns) {
        for(int i = 0; i < numberOfRuns; ++i) {
            IGameRunner runner = new DefaultGameRunner();
            IGameResult result = runner.run(createAIGame());
            
            assertTrue(result.isGameDrawn());
        }
    }

    private IGame createAIGame() {
        return new DefaultGame(new MiniMaxPlayer(Marks.X), new MiniMaxPlayer(Marks.O), new DefaultGamePolicy());
    }
}
