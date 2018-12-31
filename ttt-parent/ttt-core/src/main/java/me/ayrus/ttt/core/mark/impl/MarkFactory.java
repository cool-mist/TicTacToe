package me.ayrus.ttt.core.mark.impl;

import java.util.Random;

import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.mark.IMarkFactory;

public class MarkFactory implements IMarkFactory{
    
    private static final IMark E = new Mark("$", 0);
    private static final IMark X = new Mark("X", 1);
    private static final IMark O = new Mark("O", 2);
    
    private Random m_random;
    
    public MarkFactory() {
        m_random = new Random();
    }
    
    @Override
    public IMark create(String symbol) {
        return new Mark(symbol, m_random.nextInt());
    }

    @Override
    public IMark empty() {
        return E;
    }

    @Override
    public IMark X() {
        return X;
    }

    @Override
    public IMark O() {
        return O;
    }
}
