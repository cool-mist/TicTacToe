package me.ayrus.ttt.cli.game.demo;

import me.ayrus.ttt.cli.display.IDisplay;
import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.game.IGamePolicy;
import me.ayrus.ttt.core.game.impl.DefaultGame;
import me.ayrus.ttt.core.player.IPlayer;

public class DefaultCLIGame extends DefaultGame{
    
    private IDisplay m_display;

    public DefaultCLIGame(IPlayer p1, IPlayer p2, IGamePolicy gamePolicy, IDisplay display) {
        super(p1, p2, gamePolicy);
        
        m_display = display;
    }
    
    @Override
    public void doNextTurn() {
        drawBoard();
        
        super.doNextTurn();
    }

    private void drawBoard() {
        IBoard board = getBoard();
        m_display.show(board);
    }

}
