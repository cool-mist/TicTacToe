package me.ayrus.ttt.core;

import me.ayrus.ttt.core.mark.IMark;

public interface ISquare {
    IMark getMark();
    void  setMark(IMark mark);
    IPos  getPos();
}
