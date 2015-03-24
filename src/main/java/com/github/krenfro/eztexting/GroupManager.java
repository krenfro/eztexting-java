package com.github.krenfro.eztexting;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

public class GroupManager {
    
    private final EzTextingClient ez;
    
    public GroupManager(EzTextingClient ez){
        Objects.requireNonNull(ez);
        this.ez = ez;
    }
    
    /**
     * Retrieve all groups.
     * @param query
     * @return all groups, never null
     * @throws IOException 
     */
    public List<Group> retrieveAll(GroupQuery query) throws IOException{           
        WebResource target = ez.getRootResource()
                .path("groups")
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
        List<Group> groups = new ArrayList<>();
        GroupsResponse parsed = ez.getJackson().readValue(json, GroupsResponse.class);
        if (response.getStatus() == 200){
            groups = parsed.getGroups();
        }
        else{
            throw new EzTextingException(parsed);
        }        
        return groups;
    }
    
    /**
     * Retrieve Group by id.
     * @param id
     * @return a Group, or null if not found.
     * @throws java.io.IOException
     */
    public Group retrieve(String id) throws IOException{
        Objects.requireNonNull(id);
        WebResource target = ez.getRootResource()
                .path("groups").path(id)
                .queryParam("format", "json")
                .queryParam("ID", id)
                .queryParam("User", ez.getCredentials().getUsername())
                .queryParam("Password", ez.getCredentials().getPassword());   
        
        ClientResponse response = target.get(ClientResponse.class);        
        String json = response.getEntity(String.class);
        GroupResponse parsed = ez.getJackson().readValue(json, GroupResponse.class);
        if (response.getStatus() == 200){
            return parsed.getGroup();
        }
        else{
            throw new EzTextingException(parsed);
        }        
    }
    
    /**
     * @param name not null, max 12 characters
     * @param note
     * @return the created group.
     * @throws IOException 
     */
    public Group create(String name, String note) throws IOException{
        Objects.requireNonNull(name);
        if (name.length() > 12){
            throw new IllegalArgumentException("Group name [" + name + "] is longer than 12 characters.");
        }
        WebResource target = ez.getRootResource().path("groups").queryParam("format", "json");        
        MultivaluedMap form = new MultivaluedMapImpl();        
        form.add("User", ez.getCredentials().getUsername());
        form.add("Password", ez.getCredentials().getPassword());
        form.add("Name", name);
        if (note != null){
            form.add("Note", note);
        }
        ClientResponse response = target.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, form);
        
        String json = response.getEntity(String.class);
        GroupResponse parsed = ez.getJackson().readValue(json, GroupResponse.class);
        if (response.getStatus() == 201){
            return parsed.getGroup();
        }
        else{
            throw new EzTextingException(parsed);
        }  
    }
    
    
    /**
     * @param name not null. max 12 characters
     * @return the created group
     * @throws IOException 
     */
    public Group create(String name) throws IOException{
        return create(name, null);
    }
    
    public boolean delete(Group group) throws IOException{
        Objects.requireNonNull(group);
        WebResource target = ez.getRootResource()
                .path("groups").path(group.getId())
                .queryParam("format", "json")
                .queryParam("ID", group.getId())
                .queryParam("User", ez.getCredentials().getUsername())
                .queryParam("Password", ez.getCredentials().getPassword());   
        
        ClientResponse response = target.delete(ClientResponse.class);        
        if (response.getStatus() == 204){
            return true;
        }
        else{
            String json = response.getEntity(String.class);
            GroupResponse parsed = ez.getJackson().readValue(json, GroupResponse.class);        
            throw new EzTextingException(parsed);
        }  
    }
    
    
    public Group renameGroup(Group group, String name) throws IOException{
    
        Objects.requireNonNull(group);
        Objects.requireNonNull(name);
        
        WebResource target = ez.getRootResource().path("groups").path(group.getId()).queryParam("format", "json");        
        MultivaluedMap form = new MultivaluedMapImpl();        
        form.add("User", ez.getCredentials().getUsername());
        form.add("Password", ez.getCredentials().getPassword());
        form.add("Name", name);
        ClientResponse response = target.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, form);
        String json = response.getEntity(String.class);
        GroupResponse parsed = ez.getJackson().readValue(json, GroupResponse.class);
        if (response.getStatus() == 200){
            return parsed.getGroup();
        }
        else{
            throw new EzTextingException(parsed);
        }  
    }
    
    public Group updateNote(Group group, String note) throws IOException{    
        Objects.requireNonNull(group);
        Objects.requireNonNull(note);        
        WebResource target = ez.getRootResource().path("groups").path(group.getId()).queryParam("format", "json");        
        MultivaluedMap form = new MultivaluedMapImpl();        
        form.add("User", ez.getCredentials().getUsername());
        form.add("Password", ez.getCredentials().getPassword());
        form.add("Name", group.getName());
        form.add("Note", note);
        ClientResponse response = target.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, form);
        String json = response.getEntity(String.class);
        GroupResponse parsed = ez.getJackson().readValue(json, GroupResponse.class);
        if (response.getStatus() == 200){
            return parsed.getGroup();
        }
        else{
            throw new EzTextingException(parsed);
        }  
    }
}
