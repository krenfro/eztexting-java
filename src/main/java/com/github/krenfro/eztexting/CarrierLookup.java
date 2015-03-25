package com.github.krenfro.eztexting;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.util.*;

public class CarrierLookup {            
    
    private final EzTextingClient ez;
    
    public CarrierLookup(EzTextingClient ez){
        Objects.requireNonNull(ez);
        this.ez = ez;
    }
    
    public String lookup(String phone) throws IOException{        
        Objects.requireNonNull(phone);
        WebResource target = ez.getRootResource()
                .path("sending/phone-numbers/")
                .path(phone)
                .queryParam("User", ez.getCredentials().getUsername())
                .queryParam("Password", ez.getCredentials().getPassword())
                .queryParam("format", "json");        
        ClientResponse response = target.get(ClientResponse.class);
        String json = response.getEntity(String.class);
        CarrierLookupResponse parsed = ez.getJackson().readValue(json, CarrierLookupResponse.class);
        if (response.getStatus() == 200){
            return parsed.getCarrierName();
        }
        else{
            throw new EzTextingException(parsed);
        }  
    }  
}
