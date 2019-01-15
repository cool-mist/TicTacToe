package me.ayrus.ttt.ai.minimax;

public class Evaluation<Action> {

    Action m_action;
    int    m_value;
    int    m_positionsEvaluated;
    
    public Evaluation(Action pos, int value, int positionsEvaluated) {
        m_action             = pos;
        m_value              = value;
        m_positionsEvaluated = positionsEvaluated;
    }
    
    public Action getPos() {
        return m_action;
    }
}
