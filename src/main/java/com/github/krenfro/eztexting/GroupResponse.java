package com.github.krenfro.eztexting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName(value = "Response")
class GroupResponse extends Response {
    
    @JsonProperty("Entry")
    private Group group;

    public Group getGroup(){
        return group;
    }

    public void setGroup(Group group){
        this.group = group;
    }
}
