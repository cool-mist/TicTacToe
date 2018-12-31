package me.ayrus.ttt.core.game;

import me.ayrus.ttt.core.mark.IMark;

public interface IGameResultBuilder {
    IGameResult withWinner(IMark winner);
    IGameResult drawn();
    IGameResult inProgress();
}
