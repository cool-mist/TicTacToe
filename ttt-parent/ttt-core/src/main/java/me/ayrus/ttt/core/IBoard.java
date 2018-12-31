package me.ayrus.ttt.core;

import java.util.Map;

public interface IBoard {
    Map<IPos, ISquare> getSquares();
    ISquare            find(int row, int col);
}
