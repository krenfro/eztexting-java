package com.github.krenfro.eztexting;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

public class ContactManager {            
    
    private final EzTextingClient ez;
    
    public ContactManager(EzTextingClient ez){
        Objects.requireNonNull(ez);
        this.ez = ez;
    }
    
    public Contact create(CreateRequest request) throws IOException{        
        Objects.requireNonNull(request);
        WebResource target = ez.getRootResource().path("contacts").queryParam("format", "json");        
        MultivaluedMap form = request.getValues();
        form.add("User", ez.getCredentials().getUsername());
        form.add("Password", ez.getCredentials().getPassword());        
        ClientResponse response = target.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .post(ClientResponse.class, form);        
        String json = response.getEntity(String.class);
        ContactResponse parsed = ez.getJackson().readValue(json, ContactResponse.class);
        if (response.getStatus() == 201){
            return parsed.getContact();
        }
        else{
            throw new EzTextingException(parsed);
        }  
    }        
    
    public List<Contact> retrieveAll(ContactQuery query) throws IOException{
        WebResource target = ez.getRootResource()
                .path("contacts")
                .queryParam("format", "json")
                .queryParam("User", ez.getCredentials().getUsername())
                .queryParam("Password", ez.getCredentials().getPassword());  
        
        if (query != null){
            for (Entry<String,String> entry: query.getValues().entrySet()){
                target = target.queryParam(entry.getKey(), entry.getValue());
            }
        }
        ClientResponse response = target.get(ClientResponse.class);        
        String json = response.getEntity(String.class);
        List<Contact> contacts = new ArrayList<>();
        ContactsResponse parsed = ez.getJackson().readValue(json, ContactsResponse.class);
        if (response.getStatus() == 200){
            contacts = parsed.getContacts();
        }
        else{
            throw new EzTextingException(parsed);
        }        
        return contacts;
    }
    
    
    public Contact retrieve(String id) throws IOException{
        Objects.requireNonNull(id);
        WebResource target = ez.getRootResource()
                .path("contacts").path(id)
                .queryParam("format", "json")
                .queryParam("ID", id)
                .queryParam("User", ez.getCredentials().getUsername())
                .queryParam("Password", ez.getCredentials().getPassword());   
        
        ClientResponse response = target.get(ClientResponse.class);        
        String json = response.getEntity(String.class);
        ContactResponse parsed = ez.getJackson().readValue(json, ContactResponse.class);
        if (response.getStatus() == 200){
            return parsed.getContact();
        }
        else{
            throw new EzTextingException(parsed);
        }        
    }
    
    public Contact optOut(Contact contact) throws IOException{        
        Objects.requireNonNull(contact);
        WebResource target = ez.getRootResource()
                .path("contacts")
                .path(contact.getId())
                .queryParam("format", "json");        
        MultivaluedMap<String,String> form = new MultivaluedMapImpl();        
        form.add("User", ez.getCredentials().getUsername());
        form.add("Password", ez.getCredentials().getPassword());
        form.add("ID", contact.getId());
        form.add("PhoneNumber", contact.getPhoneNumber());
        form.add("OptOut", "1");        
        ClientResponse response = target.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .post(ClientResponse.class, form);        
        String json = response.getEntity(String.class);
        ContactResponse parsed = ez.getJackson().readValue(json, ContactResponse.class);
        if (response.getStatus() == 200){
            return parsed.getContact();
        }
        else{
            throw new EzTextingException(parsed);
        } 
    }
    
        
    public Contact update(Contact contact, UpdateRequest update) throws IOException{
        
        Objects.requireNonNull(contact);
        Objects.requireNonNull(update);
        WebResource target = ez.getRootResource()
                .path("contacts")
                .path(contact.getId())
                .queryParam("format", "json");        
        MultivaluedMap<String,String> form = update.getValues();
        form.add("User", ez.getCredentials().getUsername());
        form.add("Password", ez.getCredentials().getPassword());
        form.add("ID", contact.getId());        
        if (!form.containsKey("PhoneNumber")){
            form.add("PhoneNumber", contact.getPhoneNumber());
        }
        ClientResponse response = target
                .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .post(ClientResponse.class, form);        
        String json = response.getEntity(String.class);
        ContactResponse parsed = ez.getJackson().readValue(json, ContactResponse.class);
        if (response.getStatus() == 200){
            return parsed.getContact();
        }
        else{
            throw new EzTextingException(parsed);
        }  
    }
    
    
    public boolean delete(Contact contact) throws IOException{
        Objects.requireNonNull(contact);
        WebResource target = ez.getRootResource()
                .path("contacts").path(contact.getId())
                .queryParam("format", "json")
                .queryParam("ID", contact.getId())
                .queryParam("User", ez.getCredentials().getUsername())
                .queryParam("Password", ez.getCredentials().getPassword());   
        
        ClientResponse response = target.delete(ClientResponse.class);        
        if (response.getStatus() == 204){
            return true;
        }
        else{
            String json = response.getEntity(String.class);
            ContactResponse parsed = ez.getJackson().readValue(json, ContactResponse.class);        
            throw new EzTextingException(parsed);
        }  
    }    
}
