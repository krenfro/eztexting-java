package com.github.krenfro.eztexting;

import java.io.IOException;

public class EzTextingException extends IOException{
    
    private final Response response;
    
    EzTextingException(Response response){                
        super(response == null ? null : String.format(
                "[%s] %s : %s", response.getCode(), response.getStatus(), response.getErrorsCombined()));        
        this.response = response;
    }
    
    public String getCode(){
        return response == null ? null : response.getCode();
    }
    
    public String getStatus(){
        return response == null ? null : response.getStatus();
    }
    
    public String getErrorMessage(){
        return response == null ? null : response.getErrorsCombined();
    }
}
