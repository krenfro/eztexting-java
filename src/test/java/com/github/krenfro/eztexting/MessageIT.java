package com.github.krenfro.eztexting;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageIT {

    private static final String TARGET_PHONE_NUMBER = "5551234567";
    
    /**
     * Note: This test can use credits!
     * 
     * @throws IOException 
     */
    @Test
    public void testMessageSend() throws IOException{        
        EzTextingClient ez = new EzTextingClient(new TestCredentials());
        Messaging messaging = new Messaging(ez);          
        
        Message message = new Message.Builder()
            .subject("test")
            .message("This is a test")
            .phone(TARGET_PHONE_NUMBER)
            .build();
        
        MessageReceipt receipt = messaging.send(message);
        assertNotNull(receipt);
        assertEquals("test", receipt.getSubject());
        assertEquals("This is a test", receipt.getMessage());
        assertTrue(receipt.getPhoneNumbers().contains(TARGET_PHONE_NUMBER));
    }
}