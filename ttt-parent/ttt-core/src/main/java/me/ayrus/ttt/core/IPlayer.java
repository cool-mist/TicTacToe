package me.ayrus.ttt.core;

public interface IPlayer {
    IMark getMark();
    IPos  nextMove();
}
