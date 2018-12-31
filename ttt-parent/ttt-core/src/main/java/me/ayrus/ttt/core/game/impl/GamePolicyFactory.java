package me.ayrus.ttt.core.game.impl;

import me.ayrus.ttt.core.game.IGamePolicy;
import me.ayrus.ttt.core.game.IGamePolicyFactory;

public class GamePolicyFactory implements IGamePolicyFactory{

    @Override
    public IGamePolicy defaultPolicy() {
        return new DefaultGamePolicy();
    }

}
