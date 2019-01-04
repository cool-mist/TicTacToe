package me.ayrus.ttt.core.game.impl;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.toList;

import java.util.Map.Entry;
import java.util.Optional;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.ISquare;
import me.ayrus.ttt.core.game.IGamePolicy;
import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.mark.IMark;

public class DefaultGamePolicy implements IGamePolicy{

    private IGameResultBuilder builder;

    public DefaultGamePolicy() {
        builder = new GameResultBuilder();
    }

    @Override
    public IGameResult calculate(IBoard board) {
        int numEmptyCells = board.getSquares().entrySet().stream().map(Entry::getValue).filter(this::notEmpty).collect(toList()).size();

        IGameResult result = matchRows(board)
                .orElse(matchColumns(board)
                        .orElse(matchDiagonals(board)
                                .orElse(builder.inProgress())));

        if(result == builder.inProgress() && numEmptyCells == 0)
            return builder.drawn();

        return result;
    }

    private boolean notEmpty(ISquare square) {
        return square.getMark().getId() == 0;
    }

    private Optional<IGameResult> matchDiagonals(IBoard board) {
        
        if(markIdAt(1, 1, board) == 0)
            return empty();
        
        if(markIdAt(0, 0, board) == markIdAt(1, 1, board) && 
                markIdAt(2, 2, board) == markIdAt(1, 1, board)) {

            return of(builder.withWinner(findMark(1, 1, board)));
        }

        if(markIdAt(0, 2, board) == markIdAt(1, 1, board) && 
                markIdAt(2, 0, board) == markIdAt(1, 1, board)) {

            return of(builder.withWinner(findMark(1, 1, board)));
        }

        return empty();
    }

    //TODO: Generalize this
    private Optional<IGameResult> matchRows(IBoard board) {
        for(int i = 0; i < 3; ++i) { 
            if(markIdAt(i, 0, board) == 0)
                continue;

            if(markIdAt(i, 0, board) == markIdAt(i, 1, board) && 
                    markIdAt(i, 2, board) == markIdAt(i, 1, board)) {

                return of(builder.withWinner(findMark(i, 0, board)));
            }
        }
        return empty();
    }

    private Optional<IGameResult> matchColumns(IBoard board) {
        for(int i = 0; i < 3; ++i) { 
            if(markIdAt(i, 0, board) == 0)
                continue;
            
            if(markIdAt(0, i, board) == markIdAt(1, i, board) && 
                    markIdAt(2, i, board) == markIdAt(1, i, board)) {

                return of(builder.withWinner(findMark(1, i, board)));
            }
        }
        return empty();
    }

    private IMark findMark(int i, int j, IBoard board) {
        return board.find(i, j).getMark();
    }

    private int markIdAt(int i, int j, IBoard board) {
        return findMark(i, j, board).getId();
    }

}
