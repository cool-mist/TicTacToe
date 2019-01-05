package me.ayrus.ttt.cli.input.impl;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ayrus.ttt.cli.input.IMoveScanner;
import me.ayrus.ttt.core.IPos;

public class MoveScanner implements IMoveScanner{
    
    private static final Pattern MOVE_PATTERN = Pattern.compile("[abc][123]");
    
    private Scanner m_scanner;
    private Logger  m_logger;
    
    public MoveScanner(String name, InputStream is) {
        m_scanner = new Scanner(is);
        m_logger  = LoggerFactory.getLogger(name);
    }

    @Override
    public IPos scanNextMove() {
        m_logger.info("Please enter a move (Eg: a1) : ");
        String text = m_scanner.next();
        
        if(!MOVE_PATTERN.matcher(text).matches()) {
            throw new IllegalArgumentException(String.format("'%s' is not valid move", text));
        }
        
        int col = toColumn(text.charAt(0));
        int row = toRow(text.charAt(1));
        
        return new IPos() {
            @Override public int getRow()    { return row; }
            @Override public int getColumn() { return col; }
        };
    }

    private int toRow(char charAt) {
        switch(charAt) {
            case '1': return 2;
            case '2': return 1;
        }
        
        return 0;
    }

    private int toColumn(char charAt) {
        switch(charAt) {
            case 'a': return 0;
            case 'b': return 1;
        }
        return 2;
    }

}
