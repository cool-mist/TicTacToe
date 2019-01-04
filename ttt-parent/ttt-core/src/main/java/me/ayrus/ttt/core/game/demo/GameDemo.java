package me.ayrus.ttt.core.game.demo;

import static me.ayrus.ttt.core.mark.impl.Marks.O;
import static me.ayrus.ttt.core.mark.impl.Marks.X;

import me.ayrus.ttt.core.game.IGame;
import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.game.IGameRunner;
import me.ayrus.ttt.core.game.impl.DefaultGame;
import me.ayrus.ttt.core.game.impl.DefaultGamePolicy;
import me.ayrus.ttt.core.game.impl.GameRunner;
import me.ayrus.ttt.core.player.impl.RandomAI;

public class GameDemo {

    public static void main(String[] args) {
        IGameRunner runner = new GameRunner();

        IGame game = new DefaultGame( new RandomAI(X), new RandomAI(O), new DefaultGamePolicy());

        IGameResult result = runner.run(game);
        
        System.out.println("GameOver  : " + result.isGameOver());
        System.out.println("GameDrawn : " + result.isGameDrawn());
    }
}
