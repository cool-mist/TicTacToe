package me.ayrus.ttt;

import org.apache.commons.cli.Options;

public class DefaultOptions extends Options{

    private static final long serialVersionUID = 5501119232651421310L;
    
    public DefaultOptions() {
        createOptions(this);
    }
    
    private static Options createOptions(Options opt){
        opt.addOption("x" , false, "Play as X");
        opt.addOption("o" , false, "Play as O");
        opt.addOption("ai", true,  "AI (e)z or (h)ard");
        
        return opt;
    }
}
