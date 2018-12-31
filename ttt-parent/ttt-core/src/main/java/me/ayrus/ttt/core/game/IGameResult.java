package me.ayrus.ttt.core.game;

import me.ayrus.ttt.core.mark.IMark;

public interface IGameResult{
    boolean isGameOver();
    boolean isGameDrawn();
    IMark   getWinner();
}
