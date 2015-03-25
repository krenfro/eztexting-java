package com.github.krenfro.eztexting;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class Carrier {
    
    private final String name;
    
    @JsonCreator  
    public Carrier(
            @JsonProperty("name") String name, 
            @JsonProperty("PhoneNumber") String phone){        
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
