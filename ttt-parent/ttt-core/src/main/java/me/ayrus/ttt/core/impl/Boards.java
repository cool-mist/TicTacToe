package me.ayrus.ttt.core.impl;

import java.util.Map;
import java.util.function.Supplier;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.ISquare;

public class Boards {
    
    public static IBoard createUnmodifiableBoard(IBoard board, Supplier<IBoard> constructor) {
        IBoard             res     = constructor.get();
        Map<IPos, ISquare> squares = res.getSquares();
        for(IPos pos : squares.keySet()) {
            ISquare square             = findSquareAtPos(pos, board);
            ISquare unmodifiableSquare = new UnmodifiableSquare(square);
            
            squares.put(pos, unmodifiableSquare);
        }
        
        return res;
    }

    private static ISquare findSquareAtPos(IPos pos, IBoard board) {
        for(IPos p : board.getSquares().keySet()) {
            if(p.getRow() == pos.getRow())
                if(p.getColumn() == pos.getColumn())
                    return board.getSquares().get(p);
        }
        throw new IllegalStateException(String.format("Position %d:%d not found", pos.getRow(), pos.getColumn()));
    }

}
