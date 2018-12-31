package me.ayrus.ttt.core.mark;

public interface IMarkFactory {
    IMark create(String symbol);
    IMark empty();
    IMark X();
    IMark O();
}
