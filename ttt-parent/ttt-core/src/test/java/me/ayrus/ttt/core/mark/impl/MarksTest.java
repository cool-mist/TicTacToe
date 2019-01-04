package me.ayrus.ttt.core.mark.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import me.ayrus.ttt.core.mark.IMark;

public class MarksTest {
    
    @Test
    public void testX() {
        verifyMark(Marks.X, "X", 1);
    }
    
    @Test
    public void testO() {
        verifyMark(Marks.O, "O", 2);
    }
    
    @Test
    public void testEmpty() {
        verifyMark(Marks.E, " ", 0);
    }
    
    private void verifyMark(IMark x, String symbol, int id) {
        assertTrue(x.getSymbol().equalsIgnoreCase(symbol));
        assertTrue(x.getId() == id);
    }

}
