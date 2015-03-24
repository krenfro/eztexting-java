package com.github.krenfro.eztexting;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Contact {

    private final String id;
    private final String phoneNumber;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String note;
    private final String source;
    private final boolean optOut;
    private final List<String> groups;
    private final Date created;
    
    
    @JsonCreator  
    public Contact(
            @JsonProperty("ID") String id, 
            @JsonProperty("PhoneNumber") String phoneNumber,             
            @JsonProperty("FirstName") String firstName,             
            @JsonProperty("LastName") String lastName,             
            @JsonProperty("Email") String email,             
            @JsonProperty("Note") String note,             
            @JsonProperty("Source") String source,        
            @JsonProperty("OptOut") Boolean optOut,              
            @JsonProperty("Groups") List<String> groups,             
            @JsonProperty("CreatedAt") Date created){
        
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.note = note;
        this.source = source;
        this.groups = groups;
        this.optOut = optOut == null ? false : optOut;
        this.created = created;
    }

    public String getId(){
        return id == null ? "" : id;
    }

    public String getPhoneNumber(){
        return phoneNumber == null ? "" : phoneNumber;
    }

    public String getFirstName(){
        return firstName == null ? "" : firstName;
    }

    public String getLastName(){
        return lastName == null ? "" : lastName;
    }

    public String getEmail(){
        return email == null ? "" : email;
    }

    public String getNote(){
        return note == null ? "" : note;
    }

    public String getSource(){
        return source == null ? "" : source;
    }

    public List<String> getGroups(){
        return groups == null ? new ArrayList<String>() : groups;
    }

    public Date getCreated(){
        return created == null ? new Date() : created;
    }    

    public boolean isOptOut(){
        return optOut;
    }

    @Override
    public String toString(){
        return "Contact{" + "id=" + id + ", phoneNumber=" + phoneNumber + '}';
    }
    
    

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.phoneNumber);
        hash = 29 * hash + Objects.hashCode(this.firstName);
        hash = 29 * hash + Objects.hashCode(this.lastName);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.note);
        hash = 29 * hash + Objects.hashCode(this.source);
        hash = 29 * hash + (this.optOut ? 1 : 0);
        hash = 29 * hash + Objects.hashCode(this.groups);
        hash = 29 * hash + Objects.hashCode(this.created);
        return hash;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        final Contact other = (Contact) obj;
        if (!Objects.equals(this.id, other.id)){
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)){
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)){
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)){
            return false;
        }
        if (!Objects.equals(this.email, other.email)){
            return false;
        }
        if (!Objects.equals(this.note, other.note)){
            return false;
        }
        if (!Objects.equals(this.source, other.source)){
            return false;
        }
        if (this.optOut != other.optOut){
            return false;
        }
        if (!Objects.equals(this.groups, other.groups)){
            return false;
        }
        if (!Objects.equals(this.created, other.created)){
            return false;
        }
        return true;
    }
    
    
    
    

}
