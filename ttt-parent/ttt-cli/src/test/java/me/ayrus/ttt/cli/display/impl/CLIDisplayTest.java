package me.ayrus.ttt.cli.display.impl;

import org.junit.Test;

import me.ayrus.ttt.cli.display.IDisplay;
import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.impl.DefaultBoard;
import me.ayrus.ttt.core.mark.impl.Marks;

public class CLIDisplayTest {

    @Test
    public void testCLIDisplay_succeeds() {
        IDisplay display = new CLIDisplay("CLI");
        IBoard board = new DefaultBoard();
        display.show(board);
        
        board.find(0, 0).setMark(Marks.X);
        display.show(board);
        
        board.find(0, 1).setMark(Marks.X);
        display.show(board);
        
        board.find(0, 2).setMark(Marks.O);
        display.show(board);
        
        board.find(1, 0).setMark(Marks.X);
        display.show(board);
    }
}
