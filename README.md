# Spark Streaming Receiver for Salesforce CometD API
This is a basic Apache Spark Streaming receiver for Salesforce CometD API

## Salesforce Topics
### Platform event
```
/event/EventName__e
```

### Change Data Capture event
- For all change events
```
/data/ChangeEvents
```

- For a specific standard object
```
/data/ObjectNameChangeEvent
```

- For a specific custom object
```
/data/CustomObjectName__ChangeEvent
```

### PushTopic event
```
/topic/PushTopicName
```
e.g.
```
/topic/InvoiceStatementUpdates
```
### Generic event
```
/u/notifications/GenericStreamingChannel
```

## PushTopic Subscription Test
- Create a new object in Salesforce Object Manager or reference an existing object in the code example below 

- Create push topic located at /topic/InvoiceStatementUpdates in Salesforce Developer Console (Apex code below)
```
PushTopic pushTopic = new PushTopic();
pushTopic.Name = 'InvoiceStatementUpdates';
pushTopic.Query = 'SELECT Id, Name, Status__c FROM Invoice_Statement__c';
pushTopic.ApiVersion = 49.0;
pushTopic.NotifyForOperationCreate = true;
pushTopic.NotifyForOperationUpdate = true;
pushTopic.NotifyForOperationUndelete = true;
pushTopic.NotifyForOperationDelete = true;
pushTopic.NotifyForFields = 'Referenced';
insert pushTopic;
```

- Subscribe to updates for a push topic on the client side
``` 
SampleProgram https://login.salesforce.com [username] [password] /topic/InvoiceStatementUpdates
```

- Create an in-memory object and insert it to database via Salesforce Developer Console (Apex code below)
```
Invoice_Statement__c myCustomObject = new Invoice_Statement__c (
Status__c = 'Closed'
);

insert myCustomObject;
```

- Monitor client output for new messages, e.g.:

```
<<<<
Received:
{"event":{"createdDate":"2020-10-12T11:41:17.703Z","replayId":2,"type":"created"},"sobject":{"Id":"a003g0000025N4qAAE","Status__c":"Closed","Name":"INV-0002"}}
>>>>
```