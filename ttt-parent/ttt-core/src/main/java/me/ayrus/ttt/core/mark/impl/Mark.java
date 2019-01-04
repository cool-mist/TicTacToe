package me.ayrus.ttt.core.mark.impl;

import me.ayrus.ttt.core.mark.IMark;

class Mark implements IMark{
    private final String m_symbol;
    private final int    m_id;

    public Mark(String symbol, int id) {
        m_symbol = symbol;
        m_id = id;
    }

    public String getSymbol() {
        return m_symbol;
    }

    public int getId() {
        return m_id;
    }
    
    @Override
    public String toString() {
        return String.format("%s(%d)", m_symbol, m_id);
    }
}
