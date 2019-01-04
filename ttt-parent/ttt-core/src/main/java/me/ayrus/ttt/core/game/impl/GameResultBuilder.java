package me.ayrus.ttt.core.game.impl;

import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.mark.IMark;

class GameResultBuilder implements IGameResultBuilder{
    
    private static IGameResult IN_PROGRESS = new GameResult(false, false, null);
    private static IGameResult DRAWN       = new GameResult(true , true , null);

    @Override
    public IGameResult withWinner(IMark winner) {
        return new GameResult(true, false, winner);
    }

    @Override
    public IGameResult drawn() {
        return DRAWN;
    }

    @Override
    public IGameResult inProgress() {
        return IN_PROGRESS;
    }
}
