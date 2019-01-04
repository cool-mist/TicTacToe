package me.ayrus.ttt.cli.game.demo;

import me.ayrus.ttt.cli.display.impl.CLIDisplay;
import me.ayrus.ttt.cli.input.impl.MoveScanner;
import me.ayrus.ttt.cli.player.impl.CLIPlayer;
import me.ayrus.ttt.core.game.IGame;
import me.ayrus.ttt.core.game.IGameRunner;
import me.ayrus.ttt.core.game.impl.DefaultGamePolicy;
import me.ayrus.ttt.core.game.impl.GameRunner;
import me.ayrus.ttt.core.mark.impl.Marks;
import me.ayrus.ttt.core.player.impl.RandomAI;

public class CLIGameDemo {
    
    public static void main(String[] args) {
        IGameRunner runner = new GameRunner();
        IGame       game   = new DefaultCLIGame(
                                    new CLIPlayer(Marks.X, new MoveScanner(System.in, System.out)), 
                                    new RandomAI (Marks.O), 
                                    new DefaultGamePolicy(),
                                    new CLIDisplay(System.out)
                             );
        
        runner.run(game);
    }

}
