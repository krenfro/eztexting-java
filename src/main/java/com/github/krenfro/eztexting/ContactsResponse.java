package com.github.krenfro.eztexting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.List;


@JsonRootName(value = "Response")
class ContactsResponse extends Response {
    
    @JsonProperty("Entries")
    private List<Contact> contacts;

    public List<Contact> getContacts(){
        return contacts;
    }

    public void setContacts(List<Contact> contacts){
        this.contacts = contacts;
    }
}
