package me.ayrus.ttt.core.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import me.ayrus.ttt.core.IPos;

public class PosFormatterTest {

    
    @Test
    public void testFormatting() {
        verifyConversion("a1", 2, 0);
        verifyConversion("a2", 1, 0);
        verifyConversion("a3", 0, 0);
        verifyConversion("b1", 2, 1);
        verifyConversion("b2", 1, 1);
        verifyConversion("b3", 0, 1);
        verifyConversion("c1", 2, 2);
        verifyConversion("c2", 1, 2);
        verifyConversion("c3", 0, 2);
    }

    private void verifyConversion(String expected, int row, int column) {
        IPos   pos = createPos(row, column);
        String res = PosFormatter.formatPosition(pos);
        
        assertEquals(expected, res);
    }

    private IPos createPos(int row, int col) {
        return new Pos(row, col);
    }
}
