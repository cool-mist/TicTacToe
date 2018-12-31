package me.ayrus.ttt.core.player.impl;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.ISquare;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.player.IPlayer;

class RandomAI implements IPlayer{

    private final IMark  m_mark;
    private       IBoard m_board;
    
    RandomAI(IMark mark) {
        m_mark  = mark;
    }
    
    @Override
    public void setBoard(IBoard board) {
        m_board = board;
    }
    
    @Override
    public IMark getMark() {
        return m_mark;
    }

    @Override
    public IPos nextMove() {
        IBoard             board          = getBoard();
        Map<IPos, ISquare> state          = board.getSquares();
        List<IPos>         emptyPositions = calculateEmptyPositions(state);
        
        if(emptyPositions.isEmpty())
            throw new IllegalStateException("No move to make");
        
        int randIndex = getRandNumberWithin(emptyPositions.size());
        return emptyPositions.get(randIndex);
    }
    
    private List<IPos> calculateEmptyPositions(Map<IPos, ISquare> state) {
        return state.entrySet()
                .stream()
                .filter(this::isNotEmpty)
                .map(Entry::getKey)
                .collect(toList());
    }

    private IBoard getBoard() {
        requireNonNull(m_board, "Set the board for this player with setBoard() before moving");
        return m_board;
    }

    private int getRandNumberWithin(int size) { 
        // 0(inc) to size(exc)
        return (int) Math.floor(Math.random() * size);
    }

    private boolean isNotEmpty(Entry<IPos, ISquare> entry) {
        ISquare square = entry.getValue();
        if (square.getMark().getId() == 0)
            return true;
        
        return false;
    }
    
}
