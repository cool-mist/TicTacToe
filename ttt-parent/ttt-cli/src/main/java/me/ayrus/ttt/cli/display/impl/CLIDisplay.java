package me.ayrus.ttt.cli.display.impl;

import static java.lang.System.lineSeparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ayrus.ttt.cli.display.IDisplay;
import me.ayrus.ttt.core.IBoard;

public class CLIDisplay implements IDisplay{
    
    Logger m_logger;
    
    public CLIDisplay(String name) {
        m_logger = LoggerFactory.getLogger(name);
    }
    
    @Override
    public void show(IBoard board) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(lineSeparator());
        
        appendSeparatingLine(builder);
        appendRow(0, board, builder);
        appendSeparatingLine(builder);
        appendRow(1, board, builder);
        appendSeparatingLine(builder);
        appendRow(2, board, builder);
        appendSeparatingLine(builder);
        
        m_logger.info(builder.toString());
    }

    private void appendRow(int rowNum, IBoard board, StringBuilder builder) {
        builder.append("|");
        builder.append(getSymbol(rowNum, 0, board));
        builder.append("|");
        builder.append(getSymbol(rowNum, 1, board));
        builder.append("|");
        builder.append(getSymbol(rowNum, 2, board));
        builder.append("|");
        builder.append(lineSeparator());
    }

    private String getSymbol(int rowNum, int colNum, IBoard board) {
        return board.find(rowNum, colNum).getMark().getSymbol();
    }

    private void appendSeparatingLine(StringBuilder builder) {
        builder.append("._._._.");
        builder.append(lineSeparator());
    }
}
