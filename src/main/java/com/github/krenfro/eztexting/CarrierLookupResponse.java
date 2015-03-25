package com.github.krenfro.eztexting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "Response")
class CarrierLookupResponse extends Response {
    
    @JsonProperty("Entry")
    private Carrier carrier;

    public String getCarrierName(){
        return carrier.toString();
    }
}
