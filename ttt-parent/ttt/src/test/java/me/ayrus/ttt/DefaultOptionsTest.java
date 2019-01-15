package me.ayrus.ttt;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.junit.Assert;
import org.junit.Test;

public class DefaultOptionsTest {

    @Test
    public void testOptions() {
        Options opt = new DefaultOptions();
        
        Option x = opt.getOption("x");
        Assert.assertFalse(x.isRequired());
        Assert.assertFalse(x.hasArg());
        
        Option o = opt.getOption("o");
        Assert.assertFalse(o.isRequired());
        Assert.assertFalse(o.hasArg());
        
        Option ai = opt.getOption("ai");
        Assert.assertFalse(ai.isRequired());
        Assert.assertTrue(ai.hasArg());
    }
}
