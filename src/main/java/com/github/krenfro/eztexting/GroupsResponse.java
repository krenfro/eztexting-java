package com.github.krenfro.eztexting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.List;


@JsonRootName(value = "Response")
class GroupsResponse extends Response {
    
    @JsonProperty("Entries")
    private List<Group> groups;

    public List<Group> getGroups(){
        return groups;
    }

    public void setGroups(List<Group> groups){
        this.groups = groups;
    }
}
