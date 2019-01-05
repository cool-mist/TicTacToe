package me.ayrus.ttt.core.game.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.ISquare;
import me.ayrus.ttt.core.game.IGamePolicy;
import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.mark.impl.Marks;

public class DefaultGamePolicy implements IGamePolicy{
    
    private static final Logger            LOGGER         = LoggerFactory.getLogger(DefaultGamePolicy.class);
    private static final GameResultBuilder RESULT_BUILDER = new GameResultBuilder(); 
    private static final int[][][] WINNING = {
            { {0, 0 }, { 0, 1 }, { 0, 2 } },
            { {1, 0 }, { 1, 1 }, { 1, 2 } },
            { {2, 0 }, { 2, 1 }, { 2, 2 } },
            { {0, 0 }, { 1, 0 }, { 2, 0 } },
            { {0, 1 }, { 1, 1 }, { 2, 1 } },
            { {0, 2 }, { 1, 2 }, { 2, 2 } },
            { {0, 0 }, { 1, 1 }, { 2, 2 } },
            { {0, 2 }, { 1, 1 }, { 2, 0 } },
    };
    
    @Override
    public IGameResult calculate(IBoard board) {
        boolean emptyCells = board.getSquares().values().stream()
                                    .filter(this::squareEmpty)
                                    .count() > 0;
                                    
        IGameResult result = null;
        for(int[][] pattern : WINNING) {
            result = isWinning(pattern, board);
            if(result.isGameOver())
                break;
        }
        
        if(result.isGameOver())
            return result;
        
        if(emptyCells)
            return RESULT_BUILDER.inProgress();
        
        return RESULT_BUILDER.drawn();
    }

    private IGameResult isWinning(int[][] pattern, IBoard board) {
        IMark mark = board.find(pattern[0][0], pattern[0][1]).getMark();
        if(mark == Marks.E)
            return RESULT_BUILDER.inProgress();
        
        int row, col;
        for(int i = 1; i < pattern.length; ++i) {
            row = pattern[i][0];
            col = pattern[i][1];
            
            if(board.find(row, col).getMark() != mark)
                return RESULT_BUILDER.inProgress();
        }
       LOGGER.debug((String.format("Winning with pattern %s", formattedString(pattern))));
        return RESULT_BUILDER.withWinner(mark);
    }
    
    private String formattedString(int[][] pattern) {
        StringBuilder builder = new StringBuilder();
        for(int[] pos : pattern) {
            builder.append(String.format(" {%d, %d} ", pos[0], pos[1]));
        }
        
        return builder.toString();
    }

    private boolean squareEmpty(ISquare square) {
        return square.getMark() == Marks.E;
    }
}
