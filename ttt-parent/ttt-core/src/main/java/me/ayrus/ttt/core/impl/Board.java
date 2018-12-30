package me.ayrus.ttt.core.impl;

import java.util.HashMap;
import java.util.Map;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.ISquare;

class Board implements IBoard{
    
    private static final int ROWS = 3;
    private static final int COLS = 3;

    Map<IPos, ISquare> m_squares;
    
    public Board() {
        initSquares();
    }
    
    private void initSquares() {
        m_squares = new HashMap<>();
        for(int i = 0; i < ROWS; ++i)
            for(int j = 0; j < COLS; ++j)
                initEmptySquare(i, j);
    }

    private void initEmptySquare(int row, int col) {
        IPos pos = new Pos(row, col);
        m_squares.put(pos, new Square(pos));
    }

    @Override
    public Map<IPos, ISquare> getSquares() {
        return m_squares;
    }

}
