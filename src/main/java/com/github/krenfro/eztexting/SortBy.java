package com.github.krenfro.eztexting;

public enum SortBy {
    
    PHONE_NUMBER("PhoneNumber"),
    FIRST_NAME("FirstName"),
    LAST_NAME("LastName"),
    GROUP_NAME("Name"),
    CREATED_AT("CreatedAt");
    
    private final String description;
    
    private SortBy(String desc){
        this.description = desc;
    }

    public String getDescription(){
        return description;
    }
}
