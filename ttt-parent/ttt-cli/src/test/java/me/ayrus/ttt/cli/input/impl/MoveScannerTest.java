package me.ayrus.ttt.cli.input.impl;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;
import org.mockito.Mockito;

import me.ayrus.ttt.cli.input.IMoveScanner;
import me.ayrus.ttt.core.IPos;

public class MoveScannerTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNextMove_NotInt() {
        IMoveScanner moveScanner = createMoveScanner("abc\n32");
        
        moveScanner.scanNextMove();
    }
    
    @Test
    public void testNextMove() {
        IMoveScanner moveScanner = createMoveScanner("12\n98\n1\n01");
        
        checkNextMove(moveScanner, 1, 2);
        checkNextMove(moveScanner, 9, 8);
        checkNextMove(moveScanner, 0, 1);
        checkNextMove(moveScanner, 0, 1);
    }
    
    private void checkNextMove(IMoveScanner moveScanner, int i, int j) {
        IPos pos = moveScanner.scanNextMove();
        assertEquals(i, pos.getRow());
        assertEquals(j, pos.getColumn());
    }

    private IMoveScanner createMoveScanner(String str) {
        InputStream is = new ByteArrayInputStream( str.getBytes() );
        
        return new MoveScanner(is, Mockito.mock(OutputStream.class));
    }
}
