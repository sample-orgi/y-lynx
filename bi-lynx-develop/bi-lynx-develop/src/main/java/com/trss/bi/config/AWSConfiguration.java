package com.trss.bi.config;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

@Configuration
public class AWSConfiguration {

    @Value( "${sqs.queueUrl}" )
    public String sqsQueueUrl;

    @Value("${sqs.maxNumMessages}")
    public int sqsMaxNumMessages;

    @Value("${sqs.waitTimeSeconds}")
    public int sqsWaitTimeSeconds;

    @Bean
    public AWSLambda awsLambda() {
        return AWSLambdaClientBuilder.standard().build();
    }

    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder().build();
    }

    @Bean
    public ReceiveMessageRequest receiveMessageRequest() {
        return ReceiveMessageRequest.builder()
                .queueUrl(sqsQueueUrl)
                .maxNumberOfMessages(sqsMaxNumMessages)
                .waitTimeSeconds(sqsWaitTimeSeconds)
                .build();
    }

    @Bean
    public DeleteMessageRequest.Builder deleteMessageRequestBuilder() {
        return DeleteMessageRequest.builder().queueUrl(sqsQueueUrl);
    }
}
