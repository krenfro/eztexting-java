package com.github.krenfro.eztexting;

public enum ContactSource {    
    UNKNOWN("Unknown"),
    MANUALLY_ADDED("Manually Added"),
    UPLOAD("Upload"),
    WEB_WIDGET("Web Widget"),
    API("API"),
    KEYWORD("Keyword");
    
    private final String description;
    
    ContactSource(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
    
    public static ContactSource fromDescription(String desc){
        for (ContactSource source: ContactSource.values()){
            if (source.getDescription().equalsIgnoreCase(desc)){
                return source;
            }
        }
        return UNKNOWN;
    }
}
