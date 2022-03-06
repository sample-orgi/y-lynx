package com.trss.bi.service.aws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

@Service
public class AWSSQSService {
    private final Logger log = LoggerFactory.getLogger(AWSSQSService.class);

    private SqsClient sqsClient;
    private ReceiveMessageRequest receiveMessageRequest;
    private DeleteMessageRequest.Builder deleteMessageRequestBuilder;

    public AWSSQSService(SqsClient sqsClient, ReceiveMessageRequest receiveMessageRequest, DeleteMessageRequest.Builder deleteMessageRequestBuilder) {
        this.sqsClient = sqsClient;
        this.receiveMessageRequest = receiveMessageRequest;
        this.deleteMessageRequestBuilder = deleteMessageRequestBuilder;
    }

    public List<Message> readMessages() {
        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();
        log.info("Messages received: " + messages.size());
        return messages;
    }

    public void deleteMessage(String receiptHandle) {
        DeleteMessageRequest deleteMessageRequest = deleteMessageRequestBuilder
            .receiptHandle(receiptHandle)
            .build();
        sqsClient.deleteMessage(deleteMessageRequest);
    }
}
