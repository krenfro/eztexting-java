package com.github.krenfro.eztexting;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestCredentials extends EzTextingCredentials {

    private static final Logger logger = Logger.getLogger(TestCredentials.class.getName());
    
    public TestCredentials(){
        super("CHANGEME", "PASSWORD");
        logger.log(
                Level.CONFIG, 
                "Using test credentials [{0}] for integration tests. @see TestCredentials.java", 
                this.getUsername());
    }
}
