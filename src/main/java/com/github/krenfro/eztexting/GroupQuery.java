package com.github.krenfro.eztexting;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GroupQuery {

    private final Map<String,String> values;
    
    private GroupQuery(Map<String,String> values){
        this.values = new HashMap<>();
        this.values.putAll(values);
    }
    
    Map<String,String> getValues(){
        return values;
    }
    
    public static class Builder{

        Map<String,String> values = new HashMap<>();
        
        public Builder sortBy(SortBy by){        
            Objects.requireNonNull(by);
            if (by == SortBy.GROUP_NAME){
                values.put("sortBy", by.getDescription());
            }
            else{
                throw new IllegalArgumentException("Cannot sort groups by " + by);
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
        
        public GroupQuery build(){
            return new GroupQuery(values);
        }
    }
    
}
