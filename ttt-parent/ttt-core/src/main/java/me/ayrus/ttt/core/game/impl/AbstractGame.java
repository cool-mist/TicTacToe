package me.ayrus.ttt.core.game.impl;

import static java.lang.String.format;
import static me.ayrus.ttt.core.game.impl.ValidationUtils.validatePlayers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.ISquare;
import me.ayrus.ttt.core.game.IGame;
import me.ayrus.ttt.core.game.IGamePolicy;
import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.impl.Boards;
import me.ayrus.ttt.core.impl.DefaultBoard;
import me.ayrus.ttt.core.impl.PosFormatter;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.player.IPlayer;

abstract class AbstractGame implements IGame{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGame.class);

    private List<IPlayer> m_players;
    private int           m_nextPlayerIndex;
    private IBoard        m_board;
    private IBoard        m_unmodifiableBoard;
    private IGameResult   m_gameResult;
    private IGamePolicy   m_gamePolicy;

    AbstractGame(List<IPlayer> players, IGamePolicy gamePolicy) {
        validatePlayers(players);

        m_players           = players;
        m_nextPlayerIndex   = 0;
        m_board             = new DefaultBoard();
        m_unmodifiableBoard = Boards.createUnmodifiableBoard(m_board, DefaultBoard::new);
        m_gamePolicy        = gamePolicy;
        m_gameResult        = m_gamePolicy.calculate(m_board);
        
        initPlayers();
    }
    
    private void initPlayers() {
        m_players.forEach(p -> p.setBoard(m_unmodifiableBoard));
    }

    @Override
    public void doNextTurn() {
        makeMove();

        m_gameResult      = calculateResult();
        m_nextPlayerIndex = nextPlayer();
    }
    
    @Override
    public IGameResult getResult() {
        return m_gameResult;
    }
    
    @Override
    public IBoard getBoard() {
        return m_unmodifiableBoard;
    }

    private ISquare makeMove() { //TODO: Handle exceptions if player performs an illegal move
        IPlayer currentPlayer = m_players.get(m_nextPlayerIndex);
        IPos    pos           = currentPlayer.nextMove();
        ISquare square        = m_board.find(pos.getRow(), pos.getColumn());
        IMark   mark          = currentPlayer.getMark();
        
        square.setMark(mark);
        
        LOGGER.info(format("Player %s(%d) placed their sign at %s", mark.getSymbol(), mark.getId(), PosFormatter.formatPosition(pos)));
        
        return square;
    }
    
    private int nextPlayer() {
        int size = m_players.size();
        int next = (m_nextPlayerIndex + 1) % size;
        
        return next;
    }

    private IGameResult calculateResult() {
        IGameResult result = m_gamePolicy.calculate(m_unmodifiableBoard);
        
        log(result);
        
        return result;
    }

    private void log(IGameResult result) {
        if(!result.isGameOver())
            return;
        
        if(result.isGameDrawn())
            LOGGER.info("Game is drawn.");
        else
            LOGGER.info(String.format("Player %s(%d) has won.", result.getWinner().getSymbol(), result.getWinner().getId()));
    }
}
