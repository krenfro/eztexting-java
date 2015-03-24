package com.github.krenfro.eztexting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@JsonRootName(value = "Response")        
class Response {

    @JsonProperty("Status")
    private String status;
    
    @JsonProperty("Code")
    private String code;
    
    @JsonProperty("Errors")
    private List<String> errors;
    
    @JsonProperty("Entry")
    private Map<String,Object> entry;
    
    @JsonProperty("Entries")
    private List<Map<String,Object>> entries;
    

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public List<String> getErrors(){
        return errors;
    }

    public void setErrors(List<String> errors){
        this.errors = errors;
    }
    
    public String getErrorsCombined(){        
        return errors == null ? null : Arrays.toString(errors.toArray());
    }

    public Map<String, Object> getEntry(){
        return entry;
    }

    public void setEntry(Map<String, Object> entry){
        this.entry = entry;
    }

    public List<Map<String, Object>> getEntries(){
        return entries;
    }

    public void setEntries(List<Map<String, Object>> entries){
        this.entries = entries;
    }
}
