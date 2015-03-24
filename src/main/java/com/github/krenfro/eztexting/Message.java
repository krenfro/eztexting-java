package com.github.krenfro.eztexting;

import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.ws.rs.core.MultivaluedMap;

public class Message {
    
    private static final Pattern PHONE = Pattern.compile("[0-9]{10}");
    private static final Pattern MESSAGE = Pattern.compile("([a-zA-Z0-9 \\.,:;!\\?\\(\\)~=+-_\\/@$#&%'\"\n\r])+");
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); 
    
    private final MultivaluedMap values;
    
    private Message(Map<String,String> values){
        this.values = new MultivaluedMapImpl();
        this.values.putAll(values);
    }
    
    MultivaluedMap getValues(){
        return values;
    }
    
    public static class Builder{

        private boolean standardDelivery = true;
        private String message;
        private String subject = "";
        private int phoneCount = 0;
        MultivaluedMap values = new MultivaluedMapImpl();
                
        /**
         * @param phone 10 digit phone
         * @return 
         */
        public Builder phone(String phone){
            Objects.requireNonNull(phone);
            if (!PHONE.matcher(phone).matches()){
                throw new IllegalArgumentException("Invalid phone [" + phone + "]");
            }
            if (phoneCount >= 10){
                throw new IllegalArgumentException("Limit of 10 phone numbers exceeded.");
            }
            values.add("PhoneNumbers", phone);
            phoneCount++;
            return this;
        }
        
        /**
         * @param phones 10 digit phone numbers
         * @return 
         */
        public Builder phones(Collection<String> phones){
            Objects.requireNonNull(phones);
            for (String phone: phones){
                phone(phone);
            }
            return this;
        }
        
        public Builder subject(String subject){
            Objects.requireNonNull(subject);
            if (subject.length() > 13){
                throw new IllegalArgumentException("Subject [" + subject + "] > 13 characters");
            }
            this.subject = subject;
            values.add("Subject", subject);
            return this;
        }
        
        public Builder message(String message){
            Objects.requireNonNull(message);
            if (!MESSAGE.matcher(message).matches()){
                throw new IllegalArgumentException("Invalid characters in message [" + message + "]");
            }
            this.message = message;
            values.add("Message", message);
            return this;
        }
        
        public Builder group(String group){
            Objects.requireNonNull(group);
            values.add("Groups", group);
            return this;
        }
        
        public Builder groups(String ... groups){
            Objects.requireNonNull(groups);
            for (String group: groups){
                group(group);
            }
            return this;
        }
        
        public Builder sendOn(Date date){
            Objects.requireNonNull(date);
            values.add("StampToSend", DATE_FORMAT.format(date));
            return this;
        }
        
        public Builder standardDelivery(){
            values.remove("MessageTypeID");
            values.add("MessageTypeID", 1);
            return this;
        }
        
        public Builder expressDelivery(){
            values.remove("MessageTypeID");
            values.add("MessageTypeID", 2);
            standardDelivery = false;
            return this;
        }
        
        public Message build(){
            
            int len = message.length();
            if (subject != null){
                len += subject.length();
            }
            
            if (standardDelivery && len > 130){
                throw new IllegalArgumentException("subject + message length [" + len + "] > 130 characters");
            }
            else if (!standardDelivery && len > 160){
                throw new IllegalArgumentException("subject + message length [" + len + "] > 160 characters");
            }
            
            if (!values.containsKey("PhoneNumbers") && !values.containsKey("Groups")){
                throw new IllegalArgumentException("You must specify a phone number or group");
            }
            
            return new Message(values);
        }
    }
}
