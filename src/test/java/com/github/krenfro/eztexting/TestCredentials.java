package com.github.krenfro.eztexting;

import java.util.logging.Logger;

public class TestCredentials extends EzTextingCredentials {

    private static final Logger logger = Logger.getLogger(TestCredentials.class.getName());
    
    public TestCredentials(){
        super("YOUR USERNAME", "YOUR PASSWORD");
        logger.config("Using test credentials for integration tests. @see TestCredentials.java");
    }
}
