package me.ayrus.ttt.core.game;

public interface IGame {
    IGameResult getResult();
    void        doNextTurn();
}
