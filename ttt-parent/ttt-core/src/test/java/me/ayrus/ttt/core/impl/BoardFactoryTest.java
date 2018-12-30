package me.ayrus.ttt.core.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import me.ayrus.ttt.core.IBoardFactory;

public class BoardFactoryTest {
    
    IBoardFactory factory = new BoardFactory();
    
    @Test
    public void testInstance() {
        assertTrue(factory.createNewBoard() instanceof Board);
    }

}
