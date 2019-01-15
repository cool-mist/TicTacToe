package me.ayrus.ttt.ai.player.impl;

import me.ayrus.ttt.ai.minimax.impl.TicTacToeEvaluationTree;
import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.player.impl.AbstractPlayer;

//TODO: Add tests for this class
public class MiniMaxPlayer extends AbstractPlayer{

    IMark m_mark;
    
    public MiniMaxPlayer(IMark mark) {
        super(mark);
        m_mark = mark;
    }

    @Override
    protected IPos doNextMove(IBoard board, IMark mark) {
        return new TicTacToeEvaluationTree(board, mark).getEvaluation().getPos();
    }

}
