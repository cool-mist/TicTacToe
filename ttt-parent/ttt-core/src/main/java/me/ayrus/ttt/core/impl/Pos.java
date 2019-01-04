package me.ayrus.ttt.core.impl;

import me.ayrus.ttt.core.IPos;

class Pos implements IPos{

    private final int m_row;
    private final int m_column;

    public Pos(int row, int col) {
        m_row    = row;
        m_column = col;
    }
    public int getRow() {
        return m_row;
    }

    public int getColumn() {
        return m_column;
    }
    
    @Override
    public String toString() {
        return String.format("%d:%d", m_row, m_column);
    }
}
