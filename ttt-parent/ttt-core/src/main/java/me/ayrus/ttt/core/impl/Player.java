package me.ayrus.ttt.core.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IMark;
import me.ayrus.ttt.core.IPlayer;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.ISquare;

class Player implements IPlayer{

    private final IMark  m_mark;
    private final IBoard m_board;
    
    Player(IMark mark, IBoard board) {
        m_mark  = mark;
        m_board = board;
    }
    
    @Override
    public IMark getMark() {
        return m_mark;
    }

    @Override
    public IPos nextMove() {
        Map<IPos, ISquare> state          = m_board.getSquares();
        List<IPos>         emptyPositions = state.entrySet().stream()
                                                      .filter(this::isNotEmpty)
                                                      .map(Entry::getKey)
                                                      .collect(toList());
        
        if(emptyPositions.isEmpty()) {
            throw new IllegalStateException("No move to make");
        }
        int randIndex = getRandNumberWithin(emptyPositions.size());
        return emptyPositions.get(randIndex);
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
