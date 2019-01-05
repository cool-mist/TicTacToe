package me.ayrus.ttt.core.game.impl;

import me.ayrus.ttt.core.game.IGame;
import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.game.IGameRunner;

public class DefaultGameRunner implements IGameRunner{
    
    protected void onGameBegin(IGame game) {}
    protected void onGameEnd  (IGame game) {}
    protected void onTurnEnd  (IGame game) {}

    @Override
    public IGameResult run(IGame game) {
        
        int numMoves = 0;
        
        IGameResult result = game.getResult();
        onGameBegin(game);
        
        while(!result.isGameOver()) {
            result = doNextTurn(game);
            onTurnEnd(game);
            
            checkMoves(++numMoves);
        }
        
        onGameEnd(game);
        
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
