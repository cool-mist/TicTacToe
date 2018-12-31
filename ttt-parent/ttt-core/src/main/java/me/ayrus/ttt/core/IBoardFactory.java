package me.ayrus.ttt.core;

public interface IBoardFactory {
    IBoard createNewBoard();
    IBoard createUnmodifiableBoard(IBoard board);
}
