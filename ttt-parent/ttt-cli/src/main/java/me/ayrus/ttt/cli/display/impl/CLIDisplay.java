package me.ayrus.ttt.cli.display.impl;

import java.io.OutputStream;
import java.io.PrintStream;

import me.ayrus.ttt.cli.display.IDisplay;
import me.ayrus.ttt.core.IBoard;

public class CLIDisplay implements IDisplay{
    
    PrintStream m_ps;
    
    public CLIDisplay(OutputStream os) {
        m_ps = new PrintStream(os);
    }
    
    @Override
    public void show(IBoard board) {
        StringBuilder builder = new StringBuilder();
        
        appendSeparatingLine(builder);
        appendRow(0, board, builder);
        appendSeparatingLine(builder);
        appendRow(1, board, builder);
        appendSeparatingLine(builder);
        appendRow(2, board, builder);
        appendSeparatingLine(builder);
        
        m_ps.print(builder.toString());
    }

    private void appendRow(int rowNum, IBoard board, StringBuilder builder) {
        builder.append("|");
        builder.append(getSymbol(rowNum, 0, board));
        builder.append("|");
        builder.append(getSymbol(rowNum, 1, board));
        builder.append("|");
        builder.append(getSymbol(rowNum, 2, board));
        builder.append("|\n");
    }

    private String getSymbol(int rowNum, int colNum, IBoard board) {
        return board.find(rowNum, colNum).getMark().getSymbol();
    }

    private void appendSeparatingLine(StringBuilder builder) {
        builder.append("._._._.\n");
    }
}
