package com.github.krenfro.eztexting;

import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.util.Objects;
import javax.ws.rs.core.MultivaluedMap;

public class CreateRequest {

    private final MultivaluedMap values;
    
    private CreateRequest(MultivaluedMap values){
        this.values = new MultivaluedMapImpl();
        this.values.putAll(values);
    }
    
    MultivaluedMap getValues(){
        return values;
    }
    
    public static class Builder{

        MultivaluedMap values = new MultivaluedMapImpl();
        
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
        
        public CreateRequest build(){            
            if (!values.containsKey("PhoneNumber")){
                throw new IllegalArgumentException("Phone number must be specified");
            }
            return new CreateRequest(values);
        }
    }
    
}
