package me.ayrus.ttt.core.impl;

import java.util.HashMap;
import java.util.Map;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.ISquare;

public class DefaultBoard implements IBoard{
    
    private static final int ROWS = 3;
    private static final int COLS = 3;

    Map<IPos, ISquare>                  m_squares;
    Map<Integer, Map<Integer, ISquare>> m_indexedSquares;
    
    public DefaultBoard() {
        initSquares();
    }
    
    private void initSquares() {
        m_squares        = new HashMap<>();
        m_indexedSquares = new HashMap<>();
        
        for(int i = 0; i < ROWS; ++i)
            for(int j = 0; j < COLS; ++j)
                initEmptySquare(i, j);
    }

    private void initEmptySquare(int row, int col) {
        IPos pos       = new Pos(row, col);
        ISquare square = new Square(pos);
        
        if(!m_indexedSquares.containsKey(row))
            m_indexedSquares.put(row, new HashMap<>());
        
        m_squares.put(pos, square);
        m_indexedSquares.get(row).put(col, square);
    }

    @Override
    public Map<IPos, ISquare> getSquares() {
        return m_squares;
    }

    @Override
    public ISquare find(int row, int col) {
        if(outOfLimits(row, ROWS) || outOfLimits(col, COLS))
            throw new IllegalArgumentException(String.format("Invalid indices %d:%d", row, col));
        
        ISquare square = m_indexedSquares.get(row).get(col);
        IPos    pos    = square.getPos();
        
        return m_squares.get(pos);
    }

    private boolean outOfLimits(int index, int max) {
        return index < 0 || index >= max;
    }
}
