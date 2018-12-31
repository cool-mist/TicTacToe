package me.ayrus.ttt.core.game;

import me.ayrus.ttt.core.IBoard;

public interface IGamePolicy {
    IGameResult calculate(IBoard board);
}
