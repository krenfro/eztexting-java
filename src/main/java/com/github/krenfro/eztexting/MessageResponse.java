package com.github.krenfro.eztexting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "Response")
class MessageResponse extends Response {
    
    @JsonProperty("Entry")
    private MessageReceipt receipt;

    public MessageReceipt getReceipt(){
        return receipt;
    }

    public void setReceipt(MessageReceipt receipt){
        this.receipt = receipt;
    }
}
