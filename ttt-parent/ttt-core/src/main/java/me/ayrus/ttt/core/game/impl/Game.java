package me.ayrus.ttt.core.game.impl;

import static me.ayrus.ttt.core.game.impl.ValidationUtils.validatePlayers;

import java.util.List;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IBoardFactory;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.ISquare;
import me.ayrus.ttt.core.game.IGame;
import me.ayrus.ttt.core.game.IGamePolicy;
import me.ayrus.ttt.core.game.IGameResult;
import me.ayrus.ttt.core.impl.BoardFactory;
import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.player.IPlayer;

public class Game implements IGame{

    private List<IPlayer> m_players;
    private int           m_nextPlayerIndex;
    private IBoard        m_board;
    private IBoard        m_unmodifiableBoard;
    private IBoardFactory m_factory;
    private IGameResult   m_gameResult;
    private IGamePolicy   m_gamePolicy;

    public Game(List<IPlayer> players, IGamePolicy gamePolicy) {
        validatePlayers(players);

        m_players           = players;
        m_nextPlayerIndex   = 0;
        m_factory           = new BoardFactory();
        m_board             = m_factory.createNewBoard();
        m_unmodifiableBoard = m_factory.createUnmodifiableBoard(m_board);
        m_gamePolicy        = gamePolicy;
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

    private ISquare makeMove() {
        IPlayer currentPlayer = m_players.get(m_nextPlayerIndex);
        IPos    pos           = currentPlayer.nextMove();
        ISquare square        = m_board.getSquares().get(pos);
        IMark   mark          = currentPlayer.getMark();

        square.setMark(mark);
        
        return square;
    }
    
    private int nextPlayer() {
        int size = m_players.size();
        int next = (m_nextPlayerIndex + 1) % size;
        
        return next;
    }

    private IGameResult calculateResult() {
        return m_gamePolicy.calculate(m_unmodifiableBoard);
    }
}
