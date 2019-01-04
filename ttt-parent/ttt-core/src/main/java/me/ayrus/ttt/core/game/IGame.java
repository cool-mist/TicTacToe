package me.ayrus.ttt.core.game;

import me.ayrus.ttt.core.IBoard;

public interface IGame {
    IBoard      getBoard();
    IGameResult getResult();
    void        doNextTurn();
}
