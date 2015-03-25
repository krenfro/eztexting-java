# eztexting-java
A Java client for the [EZ Texting](http://www.eztexting.com) SMS text message API

## Overview

[EZ Texting](https://www.eztexting.com) provides an [API](https://www.eztexting.com/developers/sms-api-documentation/rest) for sending SMS text messages. 

You will need an account to use this library.

Consult the [API Documentation](https://www.eztexting.com/developers/sms-api-documentation/rest)

This library does _not_ implement all the features of their API.  

This library can:

- Send SMS messages
- Manage Contacts
- Manage Groups
- Carrier Lookup

This library cannot:

- Manage Inbox
- Manage Keywords
- Manage Credits 
- Voice Broadcast

If you need a feature implemented, please submit a pull request or file an issue.

## Basic Usage

### Sending a SMS message

```java
    EzTextingClient ez = new EzTextingClient(
        new EzTextingCredentials("username","password"));
    Messaging messaging = new Messaging(ez);
    Message message = new Message.Builder()
        .subject("test")
        .message("Here I am EZ Texting")
        .phone("5551234567")
        .build();
    messaging.send(message);
```

### Managing Groups

```java
    GroupManager groups = new GroupManager(ez);
    Group group = groups.create("neat");
    GroupQuery query = new GroupQuery.Builder()
        .sortBy(GROUP_NAME)
        .itemsPerPage(10)
        .page(1)
        .build();
    List<Group> all = groups.retrieveAll(query);
```

### Managing Contacts

```java
    ContactManager contacts = new ContactManager(ez);
    CreateRequest request = new CreateRequest.Builder()
        .phone("5551234567")
        .first("first")
        .last("last")
        .email("my@email.com")
        .group("group1")
        .group("group2")
        .build();
    Contact contact = contacts.create(request);

    UpdateRequest update = new UpdateRequest.Builder()
        .email("another@email.com")
        .group("group1", "group3")
        .build();
    contact = contacts.update(contact, update);
    contacts.delete(contact);
```


## Dependencies
- [Jersey Client 1.18.1]() for HTTP I/O
- [Jackson 2.3.3]() for JSON parsing



## Maven 2 Support
This library (will soon) be in the Maven Central Repository. 
If you use Maven 2 for your builds, here is the stuff to put in your pom.xml

    <dependencies>
        <dependency>
            <groupId>com.github.krenfro</groupId>
            <artifactId>eztexting-java</artifactId>
            <version>0.0.1</version>
        </dependency>
    </dependencies>




