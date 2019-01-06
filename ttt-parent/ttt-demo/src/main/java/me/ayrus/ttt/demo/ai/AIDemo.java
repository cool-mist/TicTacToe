package me.ayrus.ttt.demo.ai;

import me.ayrus.ttt.ai.player.impl.MiniMaxPlayer;
import me.ayrus.ttt.cli.display.impl.CLIDisplay;
import me.ayrus.ttt.cli.game.impl.DefaultCLIGameRunner;
import me.ayrus.ttt.cli.input.impl.MoveScanner;
import me.ayrus.ttt.cli.player.impl.CLIPlayer;
import me.ayrus.ttt.core.game.IGame;
import me.ayrus.ttt.core.game.IGameRunner;
import me.ayrus.ttt.core.game.impl.DefaultGame;
import me.ayrus.ttt.core.game.impl.DefaultGamePolicy;
import me.ayrus.ttt.core.mark.impl.Marks;

public class AIDemo{
    
    public static void main(String[] args) {
        IGameRunner runner = new DefaultCLIGameRunner(new CLIDisplay("Board"));
        IGame       game   = new DefaultGame(
                                    new CLIPlayer    (Marks.X, new MoveScanner("Player-1",System.in)), 
                                    new MiniMaxPlayer(Marks.O), 
                                    new DefaultGamePolicy()
                             );
        
        runner.run(game);
    }
    
}
