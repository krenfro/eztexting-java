package com.github.krenfro.eztexting;

public class EzTextingCredentials {

    private final String username;
    private final String password;

    public EzTextingCredentials(String username, String password){
        this.username = username;
        this.password = password;
    }
        
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
