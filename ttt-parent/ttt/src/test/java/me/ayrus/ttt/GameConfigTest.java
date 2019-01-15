package me.ayrus.ttt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import me.ayrus.ttt.ai.player.impl.MiniMaxPlayer;
import me.ayrus.ttt.cli.game.impl.DefaultCLIGameRunner;
import me.ayrus.ttt.cli.player.impl.CLIPlayer;
import me.ayrus.ttt.core.game.IGameRunner;
import me.ayrus.ttt.core.game.impl.DefaultGameRunner;
import me.ayrus.ttt.core.mark.impl.Marks;
import me.ayrus.ttt.core.player.IPlayer;
import me.ayrus.ttt.core.player.impl.RandomAI;

public class GameConfigTest {
    
    private static final Class<? extends IPlayer>     AI_EZ        = RandomAI.class;
    private static final Class<? extends IPlayer>     AI_HARD      = MiniMaxPlayer.class;
    private static final Class<? extends IPlayer>     HUMAN        = CLIPlayer.class;
    private static final Class<? extends IGameRunner> DEFAULT_GAME = DefaultGameRunner.class;
    private static final Class<? extends IGameRunner> CLI_GAME     = DefaultCLIGameRunner.class;

    @Test
    public void testRandomX_RandomO() {
        verifyGameConfig(new String[] {""}          , AI_EZ, AI_EZ, DEFAULT_GAME);
        verifyGameConfig(new String[] {"-ai", "ez"} , AI_EZ, AI_EZ, DEFAULT_GAME);
    }

    @Test
    public void testPlayerX_RandomO() {
        verifyGameConfig(new String[] {"-x"}              , HUMAN , AI_EZ, CLI_GAME);
        verifyGameConfig(new String[] {"-x", "-ai", "ez"} , HUMAN , AI_EZ, CLI_GAME);
        verifyGameConfig(new String[] {"-ai", "ez", "-x"} , HUMAN , AI_EZ, CLI_GAME);
    }
    
    @Test
    public void testPlayerO_RandomX() {
        verifyGameConfig(new String[] {"-o"}              , AI_EZ , HUMAN, CLI_GAME);
        verifyGameConfig(new String[] {"-ai", "ez", "-o"} , AI_EZ , HUMAN, CLI_GAME);
        verifyGameConfig(new String[] {"-o", "-ai", "ez"} , AI_EZ , HUMAN, CLI_GAME);
    }
    
    @Test
    public void testPlayerX_PlayerO() {
        verifyGameConfig(new String[] {"-xo"}      , HUMAN , HUMAN, CLI_GAME);
        verifyGameConfig(new String[] {"-x", "-o"} , HUMAN , HUMAN, CLI_GAME);
        verifyGameConfig(new String[] {"-o", "-x"} , HUMAN , HUMAN, CLI_GAME);
    }
    
    @Test
    public void testPlayerX_AIO() {
        verifyGameConfig(new String[] {"-x", "-ai", "hard"} , HUMAN , AI_HARD, CLI_GAME);
        verifyGameConfig(new String[] {"-ai", "hard", "-x"} , HUMAN , AI_HARD, CLI_GAME);
    }
    
    @Test
    public void testPlayerO_AIX() {
        verifyGameConfig(new String[] {"-o", "-ai", "hard"} , AI_HARD , HUMAN, CLI_GAME);
        verifyGameConfig(new String[] {"-ai", "hard", "-o"} , AI_HARD , HUMAN, CLI_GAME);
    }
    
    @Test
    public void testAIX_AIO() {
        verifyGameConfig(new String[] {"-ai", "hard"} , AI_HARD, AI_HARD, DEFAULT_GAME);
    }
    
    private void verifyGameConfig(String[] args, Class<? extends IPlayer> p1, Class<? extends IPlayer> p2, Class<? extends IGameRunner> runner) {
        GameConfig config = new GameConfig(new DefaultOptions(), args);
        
        IPlayer px = config.getPlayer1();
        IPlayer po = config.getPlayer2();
        
        assertTrue(runner.isInstance(config.getGameRunner()));
        
        assertNotNull(px);
        assertTrue(p1.isInstance(px));
        assertEquals(Marks.X, px.getMark());
        
        assertNotNull(po);
        assertTrue(p2.isInstance(po));
        assertEquals(Marks.O, po.getMark());
    }
}
