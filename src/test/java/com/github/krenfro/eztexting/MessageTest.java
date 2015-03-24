package com.github.krenfro.eztexting;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    
    @Test
    public void testValidCharacters(){
        Message.Builder builder = new Message.Builder();
        builder.message("abcABC123\n .,:;!?()~=+-_\\/@$#&%'\"");         
        try{
            builder.message("abc\tABC");        
            fail("should have thrown IAE");
        }
        catch(IllegalArgumentException expected){
        }
    }
    
    @Test
    public void testBuilder(){
        Message.Builder builder = new Message.Builder()
            .message("message")
            .subject("subject")
            .phone("5551234567");
        Message message = builder.build();
        assertEquals("5551234567", message.getValues().getFirst("PhoneNumbers"));        
    }

}