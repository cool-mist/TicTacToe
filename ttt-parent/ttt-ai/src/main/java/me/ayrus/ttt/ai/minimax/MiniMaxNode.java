package me.ayrus.ttt.ai.minimax;

import java.util.List;

public abstract class MiniMaxNode<State, Action> {
    
    MiniMaxNode<State, Action> m_parent;
    Action                     m_landingAction;
    State                      m_currentState;
    Evaluation<Action>         m_evaluation;
    
    // TODO: Show time taken for calculations
    public MiniMaxNode(MiniMaxNode<State, Action> parent, Action landingMove, State currentState) {
        m_parent        = parent;
        m_landingAction = landingMove;
        m_currentState  = currentState;
    }
    
    protected abstract boolean                    isMaxNode();
    protected abstract boolean                    isTerminalState();
    protected abstract List<Action>               getAllActions(State state);
    protected abstract int                        evaluateTerminalState();
    protected abstract MiniMaxNode<State, Action> getNewNode(MiniMaxNode<State, Action> parent, State fromState, Action action);
    
    public Evaluation<Action> getEvaluation() {
        if(m_evaluation == null) {
            m_evaluation = doGetEvaluation();
        }
        
        return m_evaluation;
    }
    
    private Evaluation<Action> doGetEvaluation() {
        if(isTerminalState())
            return new Evaluation<>(null, evaluateTerminalState(), 1);
        
        List<Action> actions            = getAllActions(m_currentState);
        int          bestValue          = getDefaultEvaluation();
        int          positionsEvaluated = 0;
        Action       bestAction         = null;
        
        for(Action action : actions) {
            MiniMaxNode<State, Action> newNode  = getNewNode(this, m_currentState, action);
            Evaluation<Action>          eval    = newNode.getEvaluation();
            
            if(betterMove(eval.m_value, bestValue)) {
                bestAction  = action;
                bestValue = eval.m_value;
            }
            
            positionsEvaluated = positionsEvaluated + eval.m_positionsEvaluated;
        }
        
        return new Evaluation<>(bestAction, bestValue, positionsEvaluated);
    }

    private boolean betterMove(int newValue, int existingValue) {
        if(isMaxNode())
            return newValue > existingValue;
            
        return newValue < existingValue;
    }

    private int getDefaultEvaluation() {
        if(isMaxNode())
            return Integer.MIN_VALUE;
        return Integer.MAX_VALUE;
    }
}
