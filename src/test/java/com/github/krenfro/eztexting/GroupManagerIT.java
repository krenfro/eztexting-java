package com.github.krenfro.eztexting;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class GroupManagerIT {

    private static final Logger logger = Logger.getLogger(GroupManagerIT.class.getName());    
    private static final String TEST_GROUP_PREFIX = "junit";    
    private final String groupName;
    
    public GroupManagerIT() {
        groupName = (TEST_GROUP_PREFIX + Math.abs(new Random().nextInt())).substring(0, 11);
    }    
    
    @AfterClass
    @BeforeClass
    public static void deleteAllTestGroups() throws IOException{
        EzTextingClient ez = new EzTextingClient(new TestCredentials());
        GroupManager groupManager = new GroupManager(ez);
        List<Group> groups = groupManager.retrieveAll(new GroupQuery.Builder().build());
        for (Group group: groups){
            if (group.getName().startsWith(TEST_GROUP_PREFIX)){
                logger.log(Level.INFO, "Deleting: {0}", group);
                groupManager.delete(group);
            }
        }
    }
    
    @Test
    public void testBadCredentials() throws IOException{
        EzTextingClient ez = new EzTextingClient(new EzTextingCredentials("bad", "password"));
        GroupManager groupManager = new GroupManager(ez);
        try{
            groupManager.retrieveAll(new GroupQuery.Builder().build());
            fail("expected EzTextingException");
        }
        catch(EzTextingException ex){
            assertEquals("401", ex.getCode());
        }
        assertNotNull(groupManager);
    }
    
    @Test
    public void testGroupManagement() throws IOException{        
        EzTextingClient ez = new EzTextingClient(new TestCredentials());
        GroupManager groupManager = new GroupManager(ez);           
        Group group = groupManager.create(groupName);
        assertNotNull(group);
        assertEquals("", group.getNote());
        assertNotNull(group.getId());
        assertEquals(groupName, group.getName());        
        List<Group> groups = groupManager.retrieveAll(new GroupQuery.Builder().build());
        assertTrue(groups.contains(group)); 
        
        assertTrue(groupManager.delete(group));        
        try{
            groupManager.delete(group);
            fail("expected EzTextingException");
        }
        catch(EzTextingException expected){
            assertEquals("403", expected.getCode());
        }        
        groups = groupManager.retrieveAll(new GroupQuery.Builder().build());
        assertFalse(groups.contains(group));        
    }
    
    @Test
    public void testGroupRename() throws IOException{        
        EzTextingClient ez = new EzTextingClient(new TestCredentials());
        GroupManager groupManager = new GroupManager(ez);           
        Group group = groupManager.create(groupName);
        assertNotNull(group);
        assertEquals("", group.getNote());
        assertNotNull(group.getId());
        assertEquals(groupName, group.getName());        
        List<Group> groups = groupManager.retrieveAll(new GroupQuery.Builder().build());
        assertTrue(groups.contains(group));         
        String updatedName = group.getName().concat("b");        
        group = groupManager.renameGroup(group, updatedName);
        assertEquals(updatedName, group.getName());
        assertTrue(groupManager.delete(group));        
        try{
            groupManager.delete(group);
            fail("expected EzTextingException");
        }
        catch(EzTextingException expected){
            assertEquals("403", expected.getCode());
        }        
        groups = groupManager.retrieveAll(new GroupQuery.Builder().build());
        assertFalse(groups.contains(group));        
    }
    
    @Test
    public void testGroupNotes() throws IOException{        
        EzTextingClient ez = new EzTextingClient(new TestCredentials());
        GroupManager groupManager = new GroupManager(ez);           
        Group group = groupManager.create(groupName);
        assertNotNull(group);
        assertEquals("", group.getNote());
        assertNotNull(group.getId());
        assertEquals(groupName, group.getName());        
        List<Group> groups = groupManager.retrieveAll(new GroupQuery.Builder().build());
        assertTrue(groups.contains(group));         
        
        group = groupManager.updateNote(group, "A note");
        assertEquals("A note", group.getNote());
        assertTrue(groupManager.delete(group));        
        try{
            groupManager.delete(group);
            fail("expected EzTextingException");
        }
        catch(EzTextingException expected){
            assertEquals("403", expected.getCode());
        }        
        groups = groupManager.retrieveAll(new GroupQuery.Builder().build());
        assertFalse(groups.contains(group));        
    }
    
    
}