package me.ayrus.ttt;

import org.apache.commons.cli.Options;

import me.ayrus.ttt.core.game.IGame;
import me.ayrus.ttt.core.game.IGameRunner;

public class App {
    
    public static void main(String[] args) {
        Options     options = createOptions();
        GameConfig  config  = new GameConfig(options, args);
        IGame       game    = config.createGame();
        IGameRunner runner  = config.createGameRunner();

        runner.run(game);
    }
    
    private static Options createOptions() {
        Options opt = new Options();

        opt.addOption("x" , false, "Play as X");
        opt.addOption("o" , false, "Play as O");
        opt.addOption("ai", true,  "AI (e)z or (h)ard");

        return opt;
    }

}
