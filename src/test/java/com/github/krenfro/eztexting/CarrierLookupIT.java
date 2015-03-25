package com.github.krenfro.eztexting;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

public class CarrierLookupIT {
    
    @Test
    public void testLookup() throws IOException{           
        //I've never had a lookup enabled account, so this is untested.
        EzTextingClient ez = new EzTextingClient(new TestCredentials());
        CarrierLookup lookup = new CarrierLookup(ez);
        assertEquals("unknown", lookup.lookup("5551234567"));
    }
}