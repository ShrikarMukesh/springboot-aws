# springboot-aws-sqs
Amazon SQS ( Simple Queue Service )
Amazon SQS provides queues for high-throughput, system-to-system messaging. 
You can use queues to decouple heavyweight processes and to buffer and batch work. 
Amazon SQS stores messages until microservices and serverless applications process them.

Benefits and features
1)Highly scalable Standard and FIFO queues
    Queues scale elastically with your application. 
    Nearly unlimited throughput and no limit to the number of messages per queue in Standard queues. 
    First-In-First-Out delivery and exactly once processing in FIFO queues.

2)Durability and availability
     Your queues are distributed on multiple servers.
     Redundant infrastructure provides highly concurrent access to messages.
3)Security
     Protection in transit and at rest.
     Transmit sensitive data in encrypted queues. Send messages in a Virtual Private Cloud.
4)Batching
     Send, receive, or delete messages in batches of up to 10 messages or 256KB to save costs.

![](src/main/resources/SQS1.png)

