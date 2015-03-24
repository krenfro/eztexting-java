package com.github.krenfro.eztexting;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.util.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

public class Messaging {
        
    private final EzTextingClient ez;
    
    public Messaging(EzTextingClient ez){
        Objects.requireNonNull(ez);
        this.ez = ez;
    }

    
    public MessageReceipt send(Message message) throws IOException{
        Objects.requireNonNull(message);
        WebResource target = ez.getRootResource().path("sending/messages").queryParam("format", "json");        
        MultivaluedMap form = message.getValues();
        form.add("User", ez.getCredentials().getUsername());
        form.add("Password", ez.getCredentials().getPassword());
        ClientResponse response = target
                .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .post(ClientResponse.class, form);
        
        String json = response.getEntity(String.class);
        MessageResponse parsed = ez.getJackson().readValue(json, MessageResponse.class);
        if (response.getStatus() == 201){
            return parsed.getReceipt();
        }
        else{
            throw new EzTextingException(parsed);
        }  
    }
}
