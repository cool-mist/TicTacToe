package me.ayrus.ttt.ai.minimax;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.ISquare;
import me.ayrus.ttt.core.game.IGamePolicy;
import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.game.impl.DefaultGamePolicy;
import me.ayrus.ttt.core.impl.DefaultBoard;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.mark.impl.Marks;

// TODO: Make this generic
public class MiniMaxNode {
    IPos        m_landingMove;
    MiniMaxNode m_parent;
    Evaluation  m_evaluation;
    IBoard      m_currentState;
    List<IPos>  m_possibleMoves;
    IMark       m_currentMark;
    IMark       m_nextMark;
    IGamePolicy m_gamePolicy;
    IGameResult m_currentResult;
    
    //TODO: Add functionality to print the time-taken scanning a move
    //TODO: Add memoization for evaluated states and do it at startup
    public MiniMaxNode(MiniMaxNode parent, IPos landingMove, IBoard currentState, IMark currentMark) {
        m_parent        = parent;
        m_landingMove   = landingMove;
        m_currentState  = currentState;
        m_possibleMoves = getPossibleMoves(m_currentState);
        m_currentMark   = currentMark;
        m_nextMark      = getNextMark(m_currentMark);
        m_gamePolicy    = new DefaultGamePolicy();
        m_currentResult = m_gamePolicy.calculate(m_currentState);
    }
    
    private IMark getNextMark(IMark mark) {
        if(mark == Marks.X)
            return Marks.O;
        
        return Marks.X;
    }

    private List<IPos> getPossibleMoves(IBoard state) {
        return state.getSquares().values().stream()
                    .filter(this::empty)
                    .map(ISquare::getPos)
                    .collect(Collectors.toList());
    }
    
    private boolean empty(ISquare square) {
        return square.getMark() == Marks.E;
    }
    
    public Evaluation getEvaluation() {
        if(m_evaluation == null) {
            m_evaluation = doGetEvaluation();
        }
        
        return m_evaluation;
    }
    
    private Evaluation doGetEvaluation() {
        if(isTerminalState()) {
            return evaluateTerminalPosition();
        }
        
        int  bestValue         = getDefaultEvaluation(m_currentMark);
        IPos bestMove           = null;
        int  positionsEvaluated = 0;
        for(IPos pos : m_possibleMoves) {
            IBoard      newState = getNewState(m_currentState, pos);
            MiniMaxNode newNode  = new MiniMaxNode(this, pos, newState, m_nextMark);
            Evaluation  eval     = newNode.getEvaluation();
            
            if(betterMove(eval.m_value, bestValue, m_currentMark)) {
                bestMove  = pos;
                bestValue = eval.m_value;
            }
            
            positionsEvaluated = positionsEvaluated + eval.m_positionsEvaluated;
        }
        
        return new Evaluation(bestMove, bestValue, positionsEvaluated);
        
    }
    
    private boolean betterMove(int newValue, int existingValue, IMark mark) {
        if(mark == Marks.X)
            return newValue > existingValue;
            
        return newValue < existingValue;
    }

    private int getDefaultEvaluation(IMark mark) {
        if(mark == Marks.X)
            return Integer.MIN_VALUE;
        return Integer.MAX_VALUE;
    }

    private Evaluation evaluateTerminalPosition() {
        if(m_currentResult.isGameDrawn())
            return new Evaluation(null, 0, 1);
        
        IMark winner = m_currentResult.getWinner();
        if(winner == Marks.X)
            return new Evaluation(null, 1, 1);
        
        return new Evaluation(null, -1, 1);
    }

    private boolean isTerminalState() {
        return m_currentResult.isGameOver();
    }

    private IBoard getNewState(IBoard board, IPos pos) {
        IBoard result = cloneBoard(board);
        placeMark(result, pos, m_currentMark);
        return result;
    }
    
    private void placeMark(IBoard board, IPos pos, IMark mark) {
        board.find(pos.getRow(), pos.getColumn()).setMark(mark);
    }

    private IBoard cloneBoard(IBoard board) {
        IBoard             result  = new DefaultBoard();
        Map<IPos, ISquare> squares = board.getSquares();
        for(IPos pos : squares.keySet()) {
            IMark mark = squares.get(pos).getMark();
            if(mark != Marks.E)
                placeMark(result, pos, mark);
        }
        
        return result;
    }
}
