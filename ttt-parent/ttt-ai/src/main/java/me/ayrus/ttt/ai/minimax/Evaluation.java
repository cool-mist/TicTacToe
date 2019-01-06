package me.ayrus.ttt.ai.minimax;

import me.ayrus.ttt.core.IPos;

//TODO: Do not expose implementation details
public class Evaluation {

    IPos m_pos;
    int  m_value;
    int  m_positionsEvaluated;
    
    public Evaluation(IPos pos, int value, int positionsEvaluated) {
        m_pos   = pos;
        m_value = value;
        m_positionsEvaluated = positionsEvaluated;
    }
    
    public IPos getPos() {
        return m_pos;
    }
}
