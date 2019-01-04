package me.ayrus.ttt.core.player.impl;

import static java.util.Objects.requireNonNull;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.player.IPlayer;

public abstract class AbstractPlayer implements IPlayer{
    
    private final IMark  m_mark;
    private       IBoard m_board;
    
    public AbstractPlayer(IMark mark) {
        m_mark = mark;
    }
    
    protected abstract IPos doNextMove(IBoard board, IMark mark);

    @Override
    public void setBoard(IBoard board) {
        m_board = board;
    }
    
    @Override
    public IMark getMark() {
        return m_mark;
    }

    @Override
    public IPos nextMove() {
        IBoard board = getBoard();
        
        return doNextMove(board, m_mark);
    }
    
    private IBoard getBoard() {
        requireNonNull(m_board, "Set the board for this player with setBoard() before moving");
        return m_board;
    }
}
