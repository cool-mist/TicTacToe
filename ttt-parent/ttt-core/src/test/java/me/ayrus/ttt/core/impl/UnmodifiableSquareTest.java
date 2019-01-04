package me.ayrus.ttt.core.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.ISquare;
import me.ayrus.ttt.core.mark.impl.Marks;

public class UnmodifiableSquareTest {
    
    @Test
    public void testGetPos() {
        IPos    pos    = new Pos(0, 0);
        ISquare square = new UnmodifiableSquare(new Square(pos));
        
        assertEquals(pos, square.getPos());
    }
    
    @Test
    public void testEmptySquare() {
        ISquare square = new UnmodifiableSquare(new Square(new Pos(0, 0)));
        
        assertEquals(Marks.E, square.getMark());
    }
    
    @Test(expected = IllegalAccessError.class)
    public void testUnmodifiable() {
        ISquare square = new UnmodifiableSquare(new Square(new Pos(0, 0)));
        
        square.setMark(Marks.X);
    }

}
