package me.ayrus.ttt;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import me.ayrus.ttt.cli.display.impl.CLIDisplay;
import me.ayrus.ttt.cli.game.impl.DefaultCLIGameRunner;
import me.ayrus.ttt.cli.input.impl.MoveScanner;
import me.ayrus.ttt.cli.player.impl.CLIPlayer;
import me.ayrus.ttt.core.game.IGame;
import me.ayrus.ttt.core.game.IGamePolicy;
import me.ayrus.ttt.core.game.IGameRunner;
import me.ayrus.ttt.core.game.impl.DefaultGame;
import me.ayrus.ttt.core.game.impl.DefaultGamePolicy;
import me.ayrus.ttt.core.game.impl.DefaultGameRunner;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.mark.impl.Marks;
import me.ayrus.ttt.core.player.IPlayer;
import me.ayrus.ttt.core.player.impl.RandomAI;

public class App {

    Options m_options;

    public App() {
        m_options = defineOptions();
    }

    //TODO: Add options to select the type of AI
    private Options defineOptions() {
        Options opt = new Options();

        opt.addOption("x", false, "Play as X");
        opt.addOption("o", false, "Play as O");

        return opt;
    }

    //TODO: Parse the arguments only once
    IGame createGame(String[] args) throws ParseException {
        CommandLineParser parser  = new DefaultParser();
        CommandLine       cmdLine = parser.parse(m_options, args);
        IPlayer           p1      = createPlayer(Marks.X, cmdLine.hasOption("x"));
        IPlayer           p2      = createPlayer(Marks.O, cmdLine.hasOption("o"));
        IGamePolicy       policy  = new DefaultGamePolicy();
        IGame             game    = new DefaultGame(p1, p2, policy);

        return game;
    }

    IGameRunner createGameRunner(String[] args) throws ParseException {
        CommandLineParser parser  = new DefaultParser();
        CommandLine       cmdLine = parser.parse(m_options, args);
        
        if(cmdLine.hasOption("x") || cmdLine.hasOption("o")) {
            return new DefaultCLIGameRunner(new CLIDisplay("Board"));
        }
        
        return new DefaultGameRunner();
    }

    public Options getOptions() {
        return m_options;
    }

    private IPlayer createPlayer(IMark mark, boolean isHuman) {
        if(isHuman) {
            return new CLIPlayer(mark, new MoveScanner(mark.getSymbol(), System.in));
        }
        return new RandomAI(mark);
    }

    public static void main(String[] args) {
        App         app     = new App();
        IGame       game    = createGame(args, app);
        IGameRunner runner  = createRunner(args, app);

        runner.run(game);
    }

    //TODO: Remove copy paste error handling
    private static IGameRunner createRunner(String[] args, App app) {
        try {
            return app.createGameRunner(args);
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar ttt/target/game.jar [options]", app.getOptions());
        }
        System.exit(1);
        return null;
    }

    private static IGame createGame(String[] args, App app) {
        try {
            return app.createGame(args);
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar ttt/target/game.jar [options]", app.getOptions());
        }
        System.exit(1);
        return null;
    }
}
