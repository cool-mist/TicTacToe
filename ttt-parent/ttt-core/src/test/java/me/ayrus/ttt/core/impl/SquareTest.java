package me.ayrus.ttt.core.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.ISquare;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.mark.impl.Marks;

public class SquareTest {
    
    @Test
    public void testGetPos() {
        IPos    pos    = new Pos(0, 0);
        ISquare square = new Square(pos);
        
        assertEquals(pos, square.getPos());
    }
    
    @Test
    public void testEmptySquare() {
        ISquare square = new Square(new Pos(0, 0));
        
        assertEquals(Marks.E, square.getMark());
    }
    
    @Test
    public void testOccupy() {
        ISquare square = new Square(new Pos(0, 0));
        IMark   xMark  = Marks.X;
        
        square.setMark(xMark);
        
        assertEquals(xMark, square.getMark());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testAlreadyOccupied() {
        ISquare square = new Square(new Pos(0, 0));
        IMark   xMark  = Marks.X;
        
        square.setMark(xMark);
        square.setMark(null);
    }

}
