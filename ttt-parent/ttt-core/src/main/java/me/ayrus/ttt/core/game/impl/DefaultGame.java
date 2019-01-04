package me.ayrus.ttt.core.game.impl;

import static java.util.Arrays.asList;

import me.ayrus.ttt.core.game.IGamePolicy;
import me.ayrus.ttt.core.player.IPlayer;

public class DefaultGame extends AbstractGame{

    public DefaultGame(IPlayer p1, IPlayer p2, IGamePolicy gamePolicy) {
        super(asList(p1, p2), gamePolicy);
    }

}
