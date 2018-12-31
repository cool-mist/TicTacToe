package me.ayrus.ttt.core.player.impl;

import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.player.IPlayer;
import me.ayrus.ttt.core.player.IPlayerFactory;

public class PlayerFactory implements IPlayerFactory{

    @Override
    public IPlayer createAIRandom(IMark mark) {
        return new RandomAI(mark);
    }

}
