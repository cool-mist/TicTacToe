package me.ayrus.ttt.cli.display.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.ayrus.ttt.cli.display.IDisplay;
import me.ayrus.ttt.core.IBoard;

public class CLIDisplay implements IDisplay{
    
    Logger m_logger;
    
    public CLIDisplay(String name) {
        m_logger = LoggerFactory.getLogger(name);
    }
    
    /**
     * .
     * .........|.....|.....
     * .3....X..|     |     
     * ...._____|_____|_____
     * .       .|     |     
     * .2      .|..O  |     
     * ...._____|_____|_____
     * .       .|     |     
     * .1      .|     |  X  
     * .........|     |   
     * .  
     * .....a......b......c
     * 
     */
    @Override
    public void show(IBoard board) {
        StringBuilder builder = new StringBuilder();
        
        appendNewLine(builder);
        
        appendRow(0, board, builder);
        appendRow(1, board, builder);
        appendRow(2, board, builder);
        
        appendColumnFooter(builder);
        
        m_logger.info(builder.toString());
    }


    /**
    * .........|.....|.....
    * .1....X..|     |     
    * ...._____|_____|_____ <-- Do not add underscore in this third line if row == lastRow
    */
    private void appendRow(int row, IBoard board, StringBuilder builder) {
        appendEmptySubrow(builder);
        appendNewLine(builder);
        
        appendMiddleSubrow(row, board, builder);
        appendNewLine(builder);
        
        if(row != 2)
            appendUnderscoreSubrow(builder);
        else
            appendEmptySubrow(builder);
        appendNewLine(builder);
    }


    private void appendColumnFooter(StringBuilder builder) {
        appendSpaces(6, builder);
        appendChar('a', builder);
        appendSpaces(5, builder);
        appendChar('b', builder);
        appendSpaces(4, builder);
        appendChar('c', builder);
    
        appendNewLine(builder);
    }


    private void appendMiddleSubrow(int row, IBoard board, StringBuilder builder) {
        appendSpaces(1, builder);
        appendChar(getRowNum(row), builder);
        appendSpaces(2, builder);
        appendCellValue(row, 0, board, builder);
        appendChar('|', builder);
        appendCellValue(row, 1, board, builder);
        appendChar('|', builder);
        appendCellValue(row, 2, board, builder);
    }


    private void appendCellValue(int row, int col, IBoard board, StringBuilder builder) {
        appendSpaces(2, builder);
        appendChar(getSymbol(row, col, board), builder);
        appendSpaces(2, builder);
    }


    private char getRowNum(int row) {
        char res = String.format("%d", 3 - row).charAt(0);
        return res;
    }


    private void appendUnderscoreSubrow(StringBuilder builder) {
        appendSpaces(4, builder);
        appendChar('_', 5, builder);
        appendChar('|', builder);
        appendChar('_', 5, builder);
        appendChar('|', builder);
        appendChar('_', 5, builder);
    }


    private void appendChar(char c, int num, StringBuilder builder) {
        for(int i = 0; i < num; ++i)
            builder.append(c);
    }


    private void appendEmptySubrow(StringBuilder builder) {
        appendSpaces(9, builder);
        appendChar('|', builder);
        appendSpaces(5, builder);
        appendChar('|', builder);
        appendSpaces(5, builder);
    }


    private void appendNewLine(StringBuilder builder) {
        builder.append(System.lineSeparator());
    }


    private void appendChar(char c, StringBuilder builder) {
        builder.append(c);
    }


    private void appendSpaces(int num, StringBuilder builder) {
        for(int i = 0 ; i < num ; ++i)
            builder.append(' ');
    }


    private char getSymbol(int rowNum, int colNum, IBoard board) {
        return board.find(rowNum, colNum).getMark().getSymbol().charAt(0);
    }

}
