package me.ayrus.ttt.core.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import me.ayrus.ttt.core.IPos;

public class PosTest {
    
    @Test
    public void testRowAndCol() {
        IPos pos = createNewPos(1, 2);
        
        assertEquals(1, pos.getRow());
        assertEquals(2, pos.getColumn());
    }

    private IPos createNewPos(int i, int j) {
        return new Pos(i, j);
    }

}
