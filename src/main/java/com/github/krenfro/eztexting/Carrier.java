package com.github.krenfro.eztexting;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class Group {
    
    private final String id;
    private final String name;
    private final String note;
    private final int contactCount;
    
    @JsonCreator  
    public Group(
            @JsonProperty("ID") String id, 
            @JsonProperty("Name") String name, 
            @JsonProperty("Note") String note, 
            @JsonProperty("ContactCount") int contactCount){        
        this.id = id;
        this.name = name;
        this.note = note;
        this.contactCount = contactCount;
    }

    public String getId(){
        return id == null ? "" : id;
    }
    
    public String getName(){
        return name == null ? "" : name;
    }

    public String getNote(){
        return note == null ? "" : note;
    }

    public int getContactCount(){
        return contactCount;
    }

    @Override
    public String toString(){
        return "Group{" + "id=" + id + ", name=" + name + ", note=" + note + ", contactCount=" + contactCount + '}';
    }
    
    @Override
    public int hashCode(){
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.note);
        hash = 31 * hash + this.contactCount;
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
        final Group other = (Group) obj;
        if (!Objects.equals(this.id, other.id)){
            return false;
        }
        if (!Objects.equals(this.name, other.name)){
            return false;
        }
        if (!Objects.equals(this.note, other.note)){
            return false;
        }
        if (this.contactCount != other.contactCount){
            return false;
        }
        return true;
    }

    
    
}
