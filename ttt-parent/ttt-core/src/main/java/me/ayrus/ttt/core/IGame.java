package me.ayrus.ttt.core;

public interface IGame {
    boolean isOver();
    boolean isDrawn();
    IPlayer getWinner();
    void    toNextTurn();
}
