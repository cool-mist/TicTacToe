package me.ayrus.ttt.core.player.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import me.ayrus.ttt.core.mark.IMarkFactory;
import me.ayrus.ttt.core.mark.impl.MarkFactory;
import me.ayrus.ttt.core.player.IPlayer;
import me.ayrus.ttt.core.player.IPlayerFactory;

public class PlayerFactoryTest {
    
    IMarkFactory markFactory = new MarkFactory();

    @Test
    public void testInstances() {
        IPlayerFactory factory = new PlayerFactory();
        
        IPlayer aiRandom = factory.createAIRandom(markFactory.O());
        
        assertTrue(aiRandom instanceof RandomAI);
    }
}
