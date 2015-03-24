package com.github.krenfro.eztexting;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ContactQuery {

    private final Map<String,String> values;
    
    private ContactQuery(Map<String,String> values){
        this.values = new HashMap<>();
        this.values.putAll(values);
    }
    
    Map<String,String> getValues(){
        return values;
    }
    
    public static class Builder{

        Map<String,String> values = new HashMap<>();
        
        public Builder query(String query){
            Objects.requireNonNull(query);
            values.put("query", query);
            return this;
        }

        public Builder source(ContactSource source){
            Objects.requireNonNull(source);
            values.put("source", source.getDescription());
            return this;
        }

        public Builder optOut(boolean optOut){
            values.put("optout", optOut ? "true" : "false");
            return this;
        }

        public Builder group(String name){
            Objects.requireNonNull(name);
            values.put("group", name);
            return this;
        }
        
        public Builder sortBy(SortBy by){        
            Objects.requireNonNull(by);
            if (by == SortBy.GROUP_NAME){
                throw new IllegalArgumentException("Cannot sort groups by " + by);                
            }
            else{
                values.put("sortBy", by.getDescription());
            }
            return this;
        }

        public Builder ascending(){
            values.put("sortDir", "asc");
            return this;
        }

        public Builder descending(){
            values.put("sortDir", "desc");
            return this;
        }

        public Builder itemsPerPage(int itemsPerPage){  
            if (itemsPerPage < 1){
                throw new IllegalArgumentException("itemsPerPage < 1");
            }
            values.put("itemsPerPage", "" + itemsPerPage);
            return this;
        }

        public Builder page(int page){   
            if (page < 0){
                throw new IllegalArgumentException("itemsPerPage < 0");
            }
            values.put("page", "" + page);
            return this;
        }        
        
        public ContactQuery build(){
            return new ContactQuery(values);
        }
    }
    
}
