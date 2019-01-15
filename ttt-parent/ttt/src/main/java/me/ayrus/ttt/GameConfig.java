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
import me.ayrus.ttt.core.game.IGameRunner;
import me.ayrus.ttt.core.game.impl.DefaultGameRunner;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.mark.impl.Marks;
import me.ayrus.ttt.core.player.IPlayer;
import me.ayrus.ttt.core.player.impl.RandomAI;

public class GameConfig {

    String[]    m_args;
    Options     m_options;
    CommandLine m_cmdLine;
    IPlayer     m_player1;
    IPlayer     m_player2;
    IGameRunner m_gameRunner;

    public GameConfig(Options options, String[] args) {
        m_args    = args;
        m_options = options;
        m_cmdLine = parseOptions(args);
        
        init();
    }

    private void init() {
        m_player1     = createPlayer(Marks.X, m_cmdLine.hasOption("x"));
        m_player2     = createPlayer(Marks.O, m_cmdLine.hasOption("o"));
        m_gameRunner  = createGameRunner();
    }
    
    public IPlayer getPlayer1() {
        return m_player1;
    }
    
    public IPlayer getPlayer2() {
        return m_player2;
    }
    
    public IGameRunner getGameRunner() {
        return m_gameRunner;
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

    private IGameRunner createGameRunner() {
        if(m_cmdLine.hasOption("x") || m_cmdLine.hasOption("o")) {
            return new DefaultCLIGameRunner(new CLIDisplay("Board"));
        }
        
        return new DefaultGameRunner();
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
