package com.github.krenfro.eztexting;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class MessageReceipt {
    
    private final String id;
    private final String subject;
    private final String message;
    private final int messageTypeId;
    private final int recipientsCount;
    private final int credits;
    private final String sentTimestamp;
    private final List<String> phoneNumbers;
    private final List<String> localOptOuts;
    private final List<String> globalOptOuts;
    private final List<String> groups;

    @JsonCreator  
    public MessageReceipt(
            @JsonProperty("ID") String id, 
            @JsonProperty("Subject") String subject, 
            @JsonProperty("Message") String message, 
            @JsonProperty("MessageTypeID") int messageTypeId, 
            @JsonProperty("RecipientsCount") int recipientsCount, 
            @JsonProperty("Credits") int credits,
            @JsonProperty("StampToSend") String sentTimestamp, 
            @JsonProperty("PhoneNumbers") List<String> phoneNumbers, 
            @JsonProperty("LocalOptOuts") List<String> localOptOuts, 
            @JsonProperty("GlobalOptOuts") List<String> globalOptOuts,
            @JsonProperty("Groups") List<String> groups){
        
        this.id = id;
        this.subject = subject;
        this.message = message;
        this.messageTypeId = messageTypeId;
        this.recipientsCount = recipientsCount;
        this.credits = credits;
        this.sentTimestamp = sentTimestamp;
        this.phoneNumbers = phoneNumbers;
        this.localOptOuts = localOptOuts;
        this.globalOptOuts = globalOptOuts;
        this.groups = groups;
    }

    public String getId(){
        return id;
    }

    public String getSubject(){
        return subject;
    }

    public String getMessage(){
        return message;
    }

    public int getMessageTypeId(){
        return messageTypeId;
    }

    public int getRecipientsCount(){
        return recipientsCount;
    }

    public int getCredits(){
        return credits;
    }

    public String getSentTimestamp(){
        return sentTimestamp;
    }

    public List<String> getPhoneNumbers(){
        return phoneNumbers;
    }

    public List<String> getLocalOptOuts(){
        return localOptOuts;
    }

    public List<String> getGlobalOptOuts(){
        return globalOptOuts;
    }

    public List<String> getGroups(){
        return groups;
    }

    @Override
    public String toString(){
        return "Message{" + "id=" + id + ", subject=" + subject + '}';
    }

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.subject);
        hash = 71 * hash + Objects.hashCode(this.message);
        hash = 71 * hash + this.messageTypeId;
        hash = 71 * hash + this.recipientsCount;
        hash = 71 * hash + this.credits;
        hash = 71 * hash + Objects.hashCode(this.sentTimestamp);
        hash = 71 * hash + Objects.hashCode(this.phoneNumbers);
        hash = 71 * hash + Objects.hashCode(this.localOptOuts);
        hash = 71 * hash + Objects.hashCode(this.globalOptOuts);
        hash = 71 * hash + Objects.hashCode(this.groups);
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
        final MessageReceipt other = (MessageReceipt) obj;
        if (!Objects.equals(this.id, other.id)){
            return false;
        }
        if (!Objects.equals(this.subject, other.subject)){
            return false;
        }
        if (!Objects.equals(this.message, other.message)){
            return false;
        }
        if (this.messageTypeId != other.messageTypeId){
            return false;
        }
        if (this.recipientsCount != other.recipientsCount){
            return false;
        }
        if (this.credits != other.credits){
            return false;
        }
        if (!Objects.equals(this.sentTimestamp, other.sentTimestamp)){
            return false;
        }
        if (!Objects.equals(this.phoneNumbers, other.phoneNumbers)){
            return false;
        }
        if (!Objects.equals(this.localOptOuts, other.localOptOuts)){
            return false;
        }
        if (!Objects.equals(this.globalOptOuts, other.globalOptOuts)){
            return false;
        }
        if (!Objects.equals(this.groups, other.groups)){
            return false;
        }
        return true;
    }    
}
