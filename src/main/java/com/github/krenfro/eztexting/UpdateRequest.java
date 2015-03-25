package com.github.krenfro.eztexting;

import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.util.Objects;
import javax.ws.rs.core.MultivaluedMap;

public class UpdateRequest {

    private final Contact contact;
    private final MultivaluedMap values;
    
    private UpdateRequest(Contact contact, MultivaluedMap values){
        Objects.requireNonNull(contact);
        Objects.requireNonNull(values);
        this.contact = contact;
        this.values = new MultivaluedMapImpl();
        this.values.putAll(values);
    }
    
    Contact getContact(){
        return contact;
    }
    
    MultivaluedMap getValues(){
        return values;
    }
    
    public static class Builder{

        private MultivaluedMap values = new MultivaluedMapImpl();
        private Contact contact;
        
        public Builder(Contact contact){
            Objects.requireNonNull(contact);
            this.contact = contact;
        }
        
        public Builder phone(String phone){
            Objects.requireNonNull(phone);
            values.add("PhoneNumber", phone);    
            return this;
        }
        
        public Builder first(String first){
            Objects.requireNonNull(first);
            values.add("FirstName", first);
            return this;
        }        
        
        public Builder last(String last){
            Objects.requireNonNull(last);
            values.add("LastName", last);
            return this;
        }
        
        public Builder email(String email){
            Objects.requireNonNull(email);
            values.add("Email", email);
            return this;
        }
        
        public Builder optOut(){
            values.add("OptOut", "1");
            return this;
        }
        
        public Builder group(String group){
            Objects.requireNonNull(group);
            values.add("Groups", group);
            return this;
        }
        
        public Builder group(String ... groups){
            Objects.requireNonNull(groups);
            for (String group: groups){
                group(group);
            }
            return this;
        }
        
        public UpdateRequest build(){
            return new UpdateRequest(contact, values);
        }
    }
    
}
