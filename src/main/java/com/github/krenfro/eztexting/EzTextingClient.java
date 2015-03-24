package com.github.krenfro.eztexting;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class EzTextingClient {

    public static final URI DEFAULT_URI;    
    static{
        try{
            DEFAULT_URI = new URI("https://app.eztexting.com");
        }
        catch (URISyntaxException ex){
            throw new IllegalStateException(ex);
        }
    }
    
    private final URI baseUri;
    private final EzTextingCredentials credentials;
    private final Client jersey;
    private final ObjectMapper jackson;

    public EzTextingClient(EzTextingCredentials credentials){
        this(DEFAULT_URI, credentials);
    }
    
    public EzTextingClient(URI uri, EzTextingCredentials credentials){
        Objects.requireNonNull(uri);
        Objects.requireNonNull(credentials);
        this.baseUri = uri;
        this.credentials = credentials;
        
        jersey = Client.create();
        jersey.setFollowRedirects(false);                
        jackson = new ObjectMapper();
        jackson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        jackson.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        jackson.setDateFormat(new SimpleDateFormat("MM-dd-yyyy"));
        
    }
    
    protected EzTextingClient(Client jersey, ObjectMapper jackson, URI uri, EzTextingCredentials credentials){        
        Objects.requireNonNull(jersey);
        Objects.requireNonNull(jackson);
        Objects.requireNonNull(uri);
        Objects.requireNonNull(credentials);
        this.baseUri = uri;
        this.credentials = credentials;
        this.jersey = jersey;
        this.jackson = jackson;
    }
    
    public URI getBaseUri(){
        return baseUri;
    }

    public EzTextingCredentials getCredentials(){
        return credentials;
    }

    public Client getJersey(){
        return jersey;
    }

    public ObjectMapper getJackson(){
        return jackson;
    }
    
    WebResource getRootResource(){
        return jersey.resource(baseUri);
    }
}
