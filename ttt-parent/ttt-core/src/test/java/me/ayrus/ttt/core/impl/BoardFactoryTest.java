package me.ayrus.ttt.core.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IBoardFactory;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.ISquare;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.mark.IMarkFactory;
import me.ayrus.ttt.core.mark.impl.MarkFactory;

public class BoardFactoryTest {
    
    IBoardFactory factory   = new BoardFactory();
    IMarkFactory  markFctry = new MarkFactory();
    
    @Test
    public void testInstance() {
        assertTrue(factory.createNewBoard() instanceof DefaultBoard);
        assertTrue(factory.createUnmodifiableBoard(factory.createNewBoard())  instanceof DefaultBoard);
    }
    
    @Test
    public void testUnmodifiableBoard() {
        IBoard board = factory.createNewBoard();
        IBoard unmod = factory.createUnmodifiableBoard(board);
        
        unmod.getSquares().forEach((p, s) -> {
            checkSquareIsUnmodifiable(s);
        });
    }
    
    @Test
    public void testUnmodifiableBoardIsACopy() {
        IBoard board = factory.createNewBoard();
        IBoard unmod = factory.createUnmodifiableBoard(board);
        
        checkEqualBoard(board, unmod);
        
        IMark mark = markFctry.create("X");
        
        for(IPos pos : board.getSquares().keySet()) {
            board.getSquares().get(pos).setMark(mark);
        }
        
        checkEqualBoard(board, unmod);
    }

    private void checkEqualBoard(IBoard board, IBoard unmod) {
        assertEquals(board.getSquares().size(), unmod.getSquares().size());
        
        for(IPos pos : board.getSquares().keySet()) {
            IPos uPos = findCorrespondingPos(pos, unmod);
            checkSquaresAreEqual(board.getSquares().get(pos), unmod.getSquares().get(uPos));
        }
    }

    private void checkSquaresAreEqual(ISquare square, ISquare uSquare) {
        Assert.assertEquals(square.getMark(), uSquare.getMark());
    }

    private IPos findCorrespondingPos(IPos pos, IBoard unmod) {
        for(IPos uPos : unmod.getSquares().keySet()) {
            if(uPos.getRow() == pos.getRow())
                if(uPos.getColumn() == pos.getColumn())
                    return uPos;
        }
        throw new IllegalStateException(String.format("Position %d:%d not found", pos.getRow(), pos.getColumn()));
    }

    private void checkSquareIsUnmodifiable(ISquare s) {
        try {
            s.setMark(markFctry.create("O"));
        }catch(IllegalAccessError error) {
            // Fine!
            return;
        }
        IPos pos = s.getPos();
        fail(String.format("Square at %d:%d is accesible", pos.getRow(), pos.getColumn()));
    }

}
