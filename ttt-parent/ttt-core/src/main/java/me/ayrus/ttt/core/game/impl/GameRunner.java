package me.ayrus.ttt.core.game.impl;

import me.ayrus.ttt.core.game.IGame;
import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.game.IGameRunner;

public class GameRunner implements IGameRunner{

    @Override
    public IGameResult run(IGame game) {
        
        int numMoves = 0;
        
        IGameResult result = game.getResult();
        
        while(!result.isGameOver()) {
            result = doNextTurn(game);
            
            checkMoves(++numMoves);
        }
        
        return result;
    }

    private IGameResult doNextTurn(IGame game) {
        
        game.doNextTurn();
        
        return game.getResult();
    }

    private void checkMoves(int numMoves) {
        if(numMoves > 100)
            throw new IllegalStateException("Game is taking too long to finish, more than 100 moves.");
    }
}
