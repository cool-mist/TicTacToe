package me.ayrus.ttt.core.game.impl;

import static java.util.stream.Collectors.toList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.ayrus.ttt.core.mark.IMark;
import me.ayrus.ttt.core.player.IPlayer;

class ValidationUtils {
    
    static boolean validatePlayers(List<IPlayer> players) {
        List<IMark> marks = players.stream().map(IPlayer::getMark).collect(toList());
        return validateMarks(marks);
    }
    
    private static boolean validateMarks(List<IMark> marks) {
        List<Integer> markIds   = marks.stream().map(IMark::getId).collect(toList());
        Set<Integer>  markIdSet = new HashSet<>();
        
        for(Integer id : markIds) {
            if(markIdSet.contains(id))
                throw new IllegalArgumentException(String.format("Ids are not unique, %d appears more than once", id));
            
            markIdSet.add(id);
        }
        return true;
    }

}
