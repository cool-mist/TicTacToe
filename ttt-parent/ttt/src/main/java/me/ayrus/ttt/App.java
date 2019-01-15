package me.ayrus.ttt;

import org.apache.commons.cli.Options;

import me.ayrus.ttt.core.game.IGame;
import me.ayrus.ttt.core.game.IGameRunner;
import me.ayrus.ttt.core.game.impl.DefaultGame;
import me.ayrus.ttt.core.game.impl.DefaultGamePolicy;

public class App {
    public static void main(String[] args) {
        Options     options = new DefaultOptions();
        GameConfig  config  = new GameConfig(options, args);
        IGame       game    = new DefaultGame(config.getPlayer1(), config.getPlayer2(), new DefaultGamePolicy());
        IGameRunner runner  = config.getGameRunner();

        runner.run(game);
    }
}
