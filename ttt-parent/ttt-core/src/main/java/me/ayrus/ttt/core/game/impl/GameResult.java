package me.ayrus.ttt.core.game.impl;

import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.mark.IMark;

class GameResult implements IGameResult{
    
    private boolean m_gameOver;
    private boolean m_gameDrawn;
    private IMark   m_winner;
    
    GameResult(boolean gameOver, boolean gameDrawn, IMark winner){
        m_gameOver  = gameOver;
        m_gameDrawn = gameDrawn;
        m_winner    = winner;
    }
    
    @Override
    public boolean isGameOver() {
        return m_gameOver;
    }

    @Override
    public boolean isGameDrawn() {
        if(!m_gameOver)
            throw new IllegalAccessError("Game is not over");
        return m_gameDrawn;
    }

    @Override
    public IMark getWinner() {
        if(!m_gameOver)
            throw new IllegalAccessError("Game is not over");
        
        if(m_gameDrawn)
            throw new IllegalAccessError("Game is drawn");
        
        return m_winner;
    }

}
