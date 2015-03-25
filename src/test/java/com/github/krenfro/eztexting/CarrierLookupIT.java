package com.github.krenfro.eztexting;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class ContactManagerIT {

    private static final Logger logger = Logger.getLogger(ContactManagerIT.class.getName());   
    
    private static final String TEST_PHONE = "5556497126";
    private static final String TEST_PHONE2 = "5556497127";
    
    @AfterClass
    @BeforeClass
    public static void deleteTestContactsAndGroups() throws IOException{
        EzTextingClient ez = new EzTextingClient(new TestCredentials());
        ContactManager contactManager = new ContactManager(ez);
        GroupManager groupManager = new GroupManager(ez);
        
        List<Group> groups = groupManager.retrieveAll(new GroupQuery.Builder().itemsPerPage(100).build());        
        for (Group group: groups){
            if (group.getName().equals("JUNIT")){
                logger.log(Level.INFO, "Deleting test group: {0}", groupManager.delete(group));   
                groupManager.delete(group);
            }
        }
        
        ContactQuery query = new ContactQuery.Builder().query(TEST_PHONE).build();
        for (Contact contact: contactManager.retrieveAll(query)){
            if (contact.getPhoneNumber().equals(TEST_PHONE)){
                logger.log(Level.INFO, "Deleting test contact: {0}", contactManager.delete(contact));                
            }
        }
        
        query = new ContactQuery.Builder().query(TEST_PHONE2).build();
        for (Contact contact: contactManager.retrieveAll(query)){
            if (contact.getPhoneNumber().equals(TEST_PHONE2)){
                logger.log(Level.INFO, "Deleting test contact: {0}", contactManager.delete(contact));                
            }
        }
    }
    
    @Test
    public void testCreateContact() throws IOException{           
        EzTextingClient ez = new EzTextingClient(new TestCredentials());
        ContactManager contactManager = new ContactManager(ez);
        CreateRequest request = new CreateRequest.Builder()
            .phone(TEST_PHONE)
            .first("first")
            .last("last")
            .email("test@email.com")
            .build();
        Contact contact = contactManager.create(request);
        assertNotNull(contact);
        assertTrue(contact.getId() != null);
        assertEquals(TEST_PHONE, contact.getPhoneNumber());
        assertEquals("first", contact.getFirstName());
        assertEquals("last", contact.getLastName());
        assertEquals("test@email.com", contact.getEmail());
        assertTrue(contact.getGroups().isEmpty());        
        assertTrue(contactManager.delete(contact));
    }
    
    @Test
    public void testCreateContactWithGroups() throws IOException{           
        EzTextingClient ez = new EzTextingClient(new TestCredentials());
        ContactManager contactManager = new ContactManager(ez);
        GroupManager groupManager = new GroupManager(ez);        
        Group group = groupManager.create("JUNIT");        
        CreateRequest request = new CreateRequest.Builder()
            .phone(TEST_PHONE)
            .first("first")
            .last("last")
            .email("test@email.com")
            .group("JUNIT")
            .build();
        Contact contact = contactManager.create(request);
        assertNotNull(contact);
        assertTrue(contact.getId() != null);
        assertEquals(TEST_PHONE, contact.getPhoneNumber());
        assertEquals("first", contact.getFirstName());
        assertEquals("last", contact.getLastName());
        assertEquals("test@email.com", contact.getEmail());
        assertFalse(contact.getGroups().isEmpty());
        assertTrue(contact.getGroups().contains(group.getName()));
        assertTrue(contactManager.delete(contact));        
        assertTrue(groupManager.delete(group));
    }
        

    
    @Test
    public void testUpdateContact() throws IOException{
        EzTextingClient ez = new EzTextingClient(new TestCredentials());
        ContactManager contactManager = new ContactManager(ez);      
        GroupManager groupManager = new GroupManager(ez);          
        CreateRequest request = new CreateRequest.Builder()
            .phone(TEST_PHONE)
            .first("first")
            .last("last")
            .email("test@email.com")
            .build();
        Contact contact = contactManager.create(request);
        Group group = groupManager.create("JUNIT");        
        UpdateRequest update = new UpdateRequest.Builder()
            .phone(TEST_PHONE2)
            .group("JUNIT").build();                                      
        contact = contactManager.update(contact, update);
        assertEquals(TEST_PHONE2, contact.getPhoneNumber());
        assertFalse(contact.getGroups().isEmpty());
        assertTrue(contact.getGroups().contains(group.getName()));        
        assertTrue(contactManager.delete(contact));
        assertTrue(groupManager.delete(group));
    }
    
    
    //@Test
    public void testOptOut() throws IOException{
        //Cannot delete opted out contacts, so this test is disabled to 
        //limit the number of un-deletable contacts in the ez-texting account.
        EzTextingClient ez = new EzTextingClient(new TestCredentials());
        ContactManager contactManager = new ContactManager(ez);         
        CreateRequest request = new CreateRequest.Builder()
            .phone(TEST_PHONE)
            .first("first")
            .last("last")
            .email("test@email.com")
            .build();
        Contact contact = contactManager.create(request);
        assertNotNull(contact);
        assertTrue(contact.getId() != null);        
        assertFalse(contact.isOptOut());        
        contact = contactManager.optOut(contact);
        assertTrue(contact.isOptOut());        
        try{
            contactManager.optOut(contact);
            fail("Cannot update an opted-out contact");
        }
        catch(EzTextingException expected){
        }
        assertTrue(contactManager.delete(contact));
    }
}