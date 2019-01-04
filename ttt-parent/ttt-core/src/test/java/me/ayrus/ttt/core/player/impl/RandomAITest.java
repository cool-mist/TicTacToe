package me.ayrus.ttt.core.player.impl;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.ISquare;
import me.ayrus.ttt.core.impl.Boards;
import me.ayrus.ttt.core.impl.DefaultBoard;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.mark.impl.Marks;
import me.ayrus.ttt.core.player.IPlayer;

public class RandomAITest {

    @Test
    public void testNextMove_isEmpty() {
        IPlayer player = createRandomAI();
        IBoard  board  = new DefaultBoard();

        player.setBoard(Boards.createUnmodifiableBoard(board, DefaultBoard::new));
        
        List<IPos> boardState       = board.getSquares().entrySet().stream().map(Entry::getValue).map(ISquare::getPos).collect(toList());
        List<IPos> illegalPositions = new ArrayList<>();
        
        checkNextMoves(player, 1000, illegalPositions);
        
        makeMove(board, boardState, illegalPositions,Marks.O, 0, 0);
        checkNextMoves(player, 1000, illegalPositions);
        
        makeMove(board, boardState, illegalPositions, Marks.X, 0, 1);
        checkNextMoves(player, 1000, illegalPositions);
        
        makeMove(board, boardState, illegalPositions, Marks.O, 0, 2);
        checkNextMoves(player, 1000, illegalPositions);
        
        makeMove(board, boardState, illegalPositions, Marks.X, 1, 0);
        checkNextMoves(player, 1000, illegalPositions);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testNextMove_NoMoreMoves() {
        IPlayer player = createRandomAI();
        IBoard  board  = new DefaultBoard();
        List<ISquare> boardState = board.getSquares().entrySet().stream().map(Entry::getValue).collect(toList());
        
        player.setBoard(Boards.createUnmodifiableBoard(board, DefaultBoard::new));
        
        for(ISquare square : boardState)
            square.setMark(Marks.O);
        
        player.nextMove();
    }
    
    private void makeMove(IBoard board, List<IPos> boardState, List<IPos> illegalPositions, IMark mark, int row, int col) {
        IPos pos = findEquivalentPos(boardState, row, col);
        board.getSquares().get(pos).setMark(mark);
        illegalPositions.add(pos);
    }

    private void checkNextMoves(IPlayer player, int numberOfIterations, List<IPos> illegalPositions) {
        for(int i = 0; i < numberOfIterations; ++i) {
            IPos pos = player.nextMove();
            verifyNotContains(pos, illegalPositions);
        }
    }

    private void verifyNotContains(IPos pos, List<IPos> illegalPositions) {
        IPos res = findEquivalentPos(illegalPositions, pos);
        
        Assert.assertNull(format("Illegal position %d:%d generated", pos.getRow(), pos.getColumn()), res);
    }

    private IPos findEquivalentPos(List<IPos> illegalPositions, IPos pos) {
        return findEquivalentPos(illegalPositions, pos.getRow(), pos.getColumn());
    }
    
    private IPos findEquivalentPos(List<IPos> positions, int row, int column) {
        for(IPos p : positions)
            if(p.getRow() == row)
                if(p.getColumn() == column)
                    return p;
        
        return null;
    }

    private IPlayer createRandomAI() {
        return new RandomAI(Marks.O);
    }

}
