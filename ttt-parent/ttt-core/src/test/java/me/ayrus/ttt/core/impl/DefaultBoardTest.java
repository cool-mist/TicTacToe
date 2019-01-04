package me.ayrus.ttt.core.impl;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.ISquare;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.mark.impl.Marks;

public class DefaultBoardTest {

    protected IBoard createNewBoard() {
        return new DefaultBoard();
    }

    @Test
    public void testNumSquares() {
        IBoard board = createNewBoard();

        assertEquals(9, board.getSquares().size());
    }

    @Test
    public void testRowsAndColumns() {
        IBoard             board            = createNewBoard();
        Map<IPos, ISquare> initialState     = board.getSquares();
        Set<IPos>          initialPositions = initialState.keySet();

        for(int i = 0; i < 3; ++i)
            for(int j = 0; j < 3; ++j)
                removePosition(i, j, initialPositions);

        assertEquals(0, initialPositions.size());
    }

    @Test
    public void testValidPositions() {
        IBoard             board            = createNewBoard();
        Map<IPos, ISquare> initialState     = board.getSquares();

        initialState.forEach((pos, square) -> {
            checkValidPosition(square, pos);
        });
    }

    @Test
    public void testAllSquaresAreEmpty() {
        IBoard             board            = createNewBoard();
        Map<IPos, ISquare> initialState     = board.getSquares();

        initialState.forEach((pos, square) -> {
            checkEmptySquare(square, pos);
        });
    }
    
    @Test
    public void testFind() {
        IBoard board   = createNewBoard();
        ISquare square = board.find(0, 1);
        IPos    pos    = square.getPos();
        
        assertEquals(0, pos.getRow());
        assertEquals(1, pos.getColumn());
    }

    private void removePosition(int i, int j, Set<IPos> initialPositions) {
        IPos pos = null;
        for(IPos p : initialPositions) {
            if(p.getRow() == i && p.getColumn() == j) {
                pos = p;
                break;
            }
        }

        if(pos == null)
            throw new IllegalStateException(String.format("Position %d:%d not found", i, j));

        initialPositions.remove(pos);
    }

    private void checkValidPosition(ISquare square, IPos pos) {
        assertEquals(format("at %d:%d ", pos.getRow(), pos.getColumn()), pos, square.getPos());
    }

    private void checkEmptySquare(ISquare square, IPos pos) {
        IMark mark = square.getMark();

        assertNotNull(format("at %d:%d ", pos.getRow(), pos.getColumn()),          mark);
        assertEquals (format("at %d:%d ", pos.getRow(), pos.getColumn()),Marks.E , mark);
    }

}
