package me.ayrus.ttt.core.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.ISquare;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.mark.IMarkFactory;
import me.ayrus.ttt.core.mark.impl.MarkFactory;

public class SquareTest {
    
    IMarkFactory factory = new MarkFactory();
    
    @Test
    public void testGetPos() {
        IPos    pos    = new Pos(0, 0);
        ISquare square = new Square(pos);
        
        assertEquals(pos, square.getPos());
    }
    
    @Test
    public void testEmptySquare() {
        ISquare square = new Square(new Pos(0, 0));
        
        assertEquals("$", square.getMark().getSymbol());
        assertEquals( 0 , square.getMark().getId());
    }
    
    @Test
    public void testOccupy() {
        ISquare square = new Square(new Pos(0, 0));
        IMark   xMark  = factory.create("X");
        
        square.setMark(xMark);
        
        assertEquals(xMark, square.getMark());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testAlreadyOccupied() {
        ISquare square = new Square(new Pos(0, 0));
        IMark   xMark  = factory.create("X");
        
        square.setMark(xMark);
        square.setMark(null);
    }

}
