package me.ayrus.ttt.core.impl;

import me.ayrus.ttt.core.IPos;

public class PosFormatter {

    public static String formatPosition(IPos pos) {
        int col = pos.getColumn();
        int row = pos.getRow();
        
        return String.format("%c%d", col + 'a', 3 - row);
    }
}
