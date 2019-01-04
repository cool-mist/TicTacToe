package me.ayrus.ttt.cli.input.impl;

import static java.lang.String.format;

import java.io.InputStream;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ayrus.ttt.cli.input.IMoveScanner;
import me.ayrus.ttt.core.IPos;

public class MoveScanner implements IMoveScanner{
    
    private Scanner m_scanner;
    private Logger  m_logger;
    
    public MoveScanner(String name, InputStream is) {
        m_scanner = new Scanner(is);
        m_logger  = LoggerFactory.getLogger(name);
    }

    @Override
    public IPos scanNextMove() {
        m_logger.info("Please enter a move (Eg: 21) : ");
        if(!m_scanner.hasNextInt()) {
            String text = m_scanner.next();
            throw new IllegalArgumentException(format("Unable to read %s, please enter a valid integer of the form XX - RowColumn", text));
        }
        
        int dimen = m_scanner.nextInt();
        
        int row = dimen / 10;
        int col = dimen % 10;
        
        return new IPos() {
            @Override public int getRow()    { return row; }
            @Override public int getColumn() { return col; }
        };
    }

}
