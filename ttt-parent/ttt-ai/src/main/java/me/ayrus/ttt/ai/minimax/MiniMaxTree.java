package me.ayrus.ttt.ai.minimax;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.mark.IMark;

//TODO : Make this generic
public class MiniMaxTree {
    MiniMaxNode m_root;
    IBoard      m_initialState;
    Evaluation  m_evaluation;
    
    public MiniMaxTree(IBoard initialState, IMark mark) {
        m_initialState  = initialState;
        m_root          = new MiniMaxNode(null, null, initialState, mark);
    }
    
    public Evaluation getEvaluation() {
        if(m_evaluation == null)
            m_evaluation = m_root.getEvaluation();
        
        return m_evaluation;
    }
}
