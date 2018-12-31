package me.ayrus.ttt.core.impl;

import java.util.Map;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IBoardFactory;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.ISquare;

public class BoardFactory implements IBoardFactory{
    @Override
    public IBoard createNewBoard() {
        return new DefaultBoard();
    }
    
    @Override
    public IBoard createUnmodifiableBoard(IBoard board) {
        IBoard             res     = createNewBoard();
        Map<IPos, ISquare> squares = res.getSquares();
        for(IPos pos : squares.keySet()) {
            ISquare square             = findSquareAtPos(pos, board);
            ISquare unmodifiableSquare = new UnmodifiableSquare(square);
            
            squares.put(pos, unmodifiableSquare);
        }
        
        return res;
    }

    private ISquare findSquareAtPos(IPos pos, IBoard board) {
        for(IPos p : board.getSquares().keySet()) {
            if(p.getRow() == pos.getRow())
                if(p.getColumn() == pos.getColumn())
                    return board.getSquares().get(p);
        }
        throw new IllegalStateException(String.format("Position %d:%d not found", pos.getRow(), pos.getColumn()));
    }
}
