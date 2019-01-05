package me.ayrus.ttt.cli.game.impl;

import me.ayrus.ttt.cli.display.IDisplay;
import me.ayrus.ttt.core.game.IGame;
import me.ayrus.ttt.core.game.impl.DefaultGameRunner;

public class DefaultCLIGameRunner extends DefaultGameRunner{

    private IDisplay m_display;

    public DefaultCLIGameRunner(IDisplay display) {
        super();
        m_display = display;
    }

    @Override
    protected void onGameBegin(IGame game) {
        super.onGameBegin(game);
        m_display.show(game.getBoard());
    }
    
    @Override
    protected void onTurnEnd(IGame game) {
        super.onTurnEnd(game);
        m_display.show(game.getBoard());
    }
}
