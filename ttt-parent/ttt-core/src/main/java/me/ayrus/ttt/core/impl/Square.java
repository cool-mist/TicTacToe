package me.ayrus.ttt.core.impl;

import static java.lang.String.format;

import java.util.concurrent.atomic.AtomicBoolean;

import me.ayrus.ttt.core.IMark;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.ISquare;

class Square implements ISquare{
    
    private static final IMark EMPTY = new Mark("$", 0);

    private final IPos          m_pos;
    private       IMark         m_mark;
    private       AtomicBoolean m_occupied;
    
    public Square(IPos pos) {
        m_pos      = pos;
        m_mark     = EMPTY;
        m_occupied = new AtomicBoolean(false);
    }
    
    public IMark getMark() {
        return m_mark;
    }

    public IPos getPos() {
        return m_pos;
    }

    public void setMark(IMark mark) {
        if(isNotOccupied()) {
            m_mark = mark;
            return;
        }
        
        throw new IllegalStateException(format("The square %d:%d is already occupied with %s(%d)", 
                m_pos.getRow(), m_pos.getColumn(), m_mark.getSymbol(), m_mark.getId()));
    }

    private boolean isNotOccupied() {
        return m_occupied.compareAndSet(false, true);
    }

}
