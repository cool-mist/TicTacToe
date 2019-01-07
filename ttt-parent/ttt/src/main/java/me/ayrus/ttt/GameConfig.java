package me.ayrus.ttt;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import me.ayrus.ttt.ai.player.impl.MiniMaxPlayer;
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

public class GameConfig {

    String[]    m_args;
    Options     m_options;
    CommandLine m_cmdLine;

    public GameConfig(Options options, String[] args) {
        m_args    = args;
        m_options = options;
        m_cmdLine = parseOptions(args);
    }

    private CommandLine parseOptions(String[] args) {
        CommandLineParser parser  = new DefaultParser();
        CommandLine       cmdLine = doParse(parser, m_options, args);
        
        return cmdLine;
    }

    private CommandLine doParse(CommandLineParser parser, Options m_options2, String[] args) {
        try {
            return parser.parse(m_options, args);
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar ttt/target/game.jar [options]", m_options);
        }
        
        System.exit(1);
        return null;
    }

    IGame createGame() {
        IPlayer           p1      = createPlayer(Marks.X, m_cmdLine.hasOption("x"));
        IPlayer           p2      = createPlayer(Marks.O, m_cmdLine.hasOption("o"));
        IGamePolicy       policy  = new DefaultGamePolicy();
        IGame             game    = new DefaultGame(p1, p2, policy);

        return game;
    }

    IGameRunner createGameRunner() {
        if(m_cmdLine.hasOption("x") || m_cmdLine.hasOption("o")) {
            return new DefaultCLIGameRunner(new CLIDisplay("Board"));
        }
        
        return new DefaultGameRunner();
    }

    public Options getOptions() {
        return m_options;
    }

    private IPlayer createPlayer(IMark mark, boolean isHuman) {
        if(isHuman) 
            return new CLIPlayer(mark, new MoveScanner(mark.getSymbol(), System.in));
        if(m_cmdLine.hasOption("ai"))
            if(m_cmdLine.getOptionValue("ai", "e").startsWith("h"))
                return new MiniMaxPlayer(mark);
        
        return new RandomAI(mark);
    }
}
