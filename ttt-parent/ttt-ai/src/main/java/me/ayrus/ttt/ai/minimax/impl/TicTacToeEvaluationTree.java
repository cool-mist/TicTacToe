package me.ayrus.ttt.ai.minimax.impl;

import me.ayrus.ttt.ai.minimax.Evaluation;
import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.mark.IMark;

// TODO: Memoize the states
public class TicTacToeEvaluationTree {
    TicTacToeState    m_root;
    IBoard            m_initialState;
    Evaluation<IPos>  m_evaluation;
    
    public TicTacToeEvaluationTree(IBoard initialState, IMark mark) {
        m_initialState  = initialState;
        m_root          = new TicTacToeState(null, null, initialState, mark);
    }
    
    public Evaluation<IPos> getEvaluation() {
        if(m_evaluation == null)
            m_evaluation = m_root.getEvaluation();
        
        return m_evaluation;
    }
}
