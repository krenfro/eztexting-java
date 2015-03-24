package com.github.krenfro.eztexting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "Response")
class ContactResponse extends Response {
    
    @JsonProperty("Entry")
    private Contact contact;

    public Contact getContact(){
        return contact;
    }

    public void setContact(Contact contact){
        this.contact = contact;
    }
}
