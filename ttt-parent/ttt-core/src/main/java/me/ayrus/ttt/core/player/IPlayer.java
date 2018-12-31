package me.ayrus.ttt.core.player;

import me.ayrus.ttt.core.IBoard;
import me.ayrus.ttt.core.IPos;
import me.ayrus.ttt.core.mark.IMark;

public interface IPlayer {
    void  setBoard(IBoard board);
    IMark getMark();
    IPos  nextMove();
}
