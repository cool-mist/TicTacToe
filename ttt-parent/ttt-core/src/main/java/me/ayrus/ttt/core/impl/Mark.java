package me.ayrus.ttt.core.impl;

import me.ayrus.ttt.core.IMark;

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
}
