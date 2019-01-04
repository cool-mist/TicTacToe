package me.ayrus.ttt.cli.player.impl;

import me.ayrus.ttt.cli.input.IMoveScanner;
import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.player.impl.AbstractPlayer;

public class CLIPlayer extends AbstractPlayer{
    
    IMoveScanner m_moveScanner;
    
    public CLIPlayer(IMark mark, IMoveScanner moveScanner) {
        super(mark);
        m_moveScanner = moveScanner;
    }

    @Override
    protected IPos doNextMove(IBoard board, IMark mark) {
        return m_moveScanner.scanNextMove();
    }

}
