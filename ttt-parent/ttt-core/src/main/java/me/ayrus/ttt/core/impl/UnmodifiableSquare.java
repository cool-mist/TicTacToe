package me.ayrus.ttt.core.impl;

import static java.lang.String.format;

import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.ISquare;
import me.ayrus.ttt.core.mark.IMark;

class UnmodifiableSquare implements ISquare{

    private ISquare m_square;
    
    public UnmodifiableSquare(ISquare square) {
        m_square = square;
    }
    
    @Override
    public IMark getMark() {
        return m_square.getMark();
    }

    @Override
    public void setMark(IMark mark) {
        IPos pos = getPos();
        throw new IllegalAccessError(format("Cannot place mark at %d:%d", pos.getRow(), pos.getColumn()));
    }

    @Override
    public IPos getPos() {
        return m_square.getPos();
    }

}
