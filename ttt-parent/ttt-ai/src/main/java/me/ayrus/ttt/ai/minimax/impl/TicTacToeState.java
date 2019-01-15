package me.ayrus.ttt.ai.minimax.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import me.ayrus.ttt.ai.minimax.MiniMaxNode;
import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.ISquare;
import me.ayrus.ttt.core.game.IGamePolicy;
import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.game.impl.DefaultGamePolicy;
import me.ayrus.ttt.core.impl.DefaultBoard;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.mark.impl.Marks;

public class TicTacToeState extends MiniMaxNode<IBoard, IPos> {

    private static final IGamePolicy GAME_POLICY = new DefaultGamePolicy();

    private IMark       m_currentMark;
    private IGameResult m_currentResult;

    public TicTacToeState(MiniMaxNode<IBoard, IPos> parent, IPos landingMove, IBoard currentState, IMark currentMark) {
        super(parent, landingMove, currentState);
        m_currentResult = GAME_POLICY.calculate(currentState);
        m_currentMark   = currentMark;
    }
    
    @Override
    protected MiniMaxNode<IBoard, IPos> getNewNode(MiniMaxNode<IBoard, IPos> parent, IBoard fromState, IPos action) {
        IBoard newState = createNewState(fromState, action);
        IMark  nextMark = getNextMark();
        
        return new TicTacToeState(parent, action, newState, nextMark);
    }
    
    private IMark getNextMark() {
        return m_currentMark == Marks.X ? Marks.O : Marks.X;
    }

    private IBoard createNewState(IBoard fromState, IPos pos) {
        IBoard result = cloneBoard(fromState);
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

    @Override
    protected boolean isMaxNode() {
        return m_currentMark == Marks.X;
    }

    @Override
    protected List<IPos> getAllActions(IBoard state) {
        return state.getSquares().values().stream()
                .filter(this::empty)
                .map(ISquare::getPos)
                .collect(Collectors.toList());
    }

    private boolean empty(ISquare square) {
        return square.getMark() == Marks.E;
    }

    @Override
    protected boolean isTerminalState() {
        return m_currentResult.isGameOver();
    }

    @Override
    protected int evaluateTerminalState() {
        if(m_currentResult.isGameDrawn())
            return 0;
        
        IMark winner = m_currentResult.getWinner();
        if(winner == Marks.X)
            return 1;
        
        return -1;
    }
}
