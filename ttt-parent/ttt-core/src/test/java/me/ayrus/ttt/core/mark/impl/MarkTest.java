package me.ayrus.ttt.core.mark.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.mark.impl.Mark;

public class MarkTest {
    
    @Test
    public void testSymbolAndId() {
        IMark mark = createMark("X", 1);
        
        assertEquals("X", mark.getSymbol());
        assertEquals( 1 , mark.getId());
    }

    private IMark createMark(String symbol, int i) {
        return new Mark(symbol, i);
    }
}
