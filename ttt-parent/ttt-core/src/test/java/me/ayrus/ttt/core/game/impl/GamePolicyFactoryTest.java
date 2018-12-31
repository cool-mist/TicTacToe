package me.ayrus.ttt.core.game.impl;

import org.junit.Assert;
import org.junit.Test;

import me.ayrus.ttt.core.game.IGamePolicyFactory;

public class GamePolicyFactoryTest {

    @Test
    public void testInstance() {
        IGamePolicyFactory factory = new GamePolicyFactory();
        Assert.assertTrue( factory.defaultPolicy() instanceof DefaultGamePolicy);
    }
}
