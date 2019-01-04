package me.ayrus.ttt.core.game.impl;

import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.mark.IMark;

interface IGameResultBuilder {
    IGameResult withWinner(IMark winner);
    IGameResult drawn();
    IGameResult inProgress();
}
