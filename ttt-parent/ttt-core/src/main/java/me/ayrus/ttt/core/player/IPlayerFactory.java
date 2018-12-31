package me.ayrus.ttt.core.player;

import me.ayrus.ttt.core.mark.IMark;

public interface IPlayerFactory {
    IPlayer createAIRandom(IMark mark);
}
