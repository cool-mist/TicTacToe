package me.ayrus.ttt.core.impl;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IBoardFactory;

public class BoardFactory implements IBoardFactory{
    @Override
    public IBoard createNewBoard() {
        return new Board();
    }
}
