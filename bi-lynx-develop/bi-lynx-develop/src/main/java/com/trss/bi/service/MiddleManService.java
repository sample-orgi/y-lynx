package com.trss.bi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trss.bi.service.aws.AWSLambdaService;
import com.trss.bi.service.aws.AWSSQSService;
import com.trss.bi.service.request.LynxRequest;
import com.trss.bi.service.request.TrackingRequest;
import com.trss.bi.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import software.amazon.awssdk.services.sqs.model.Message;

@Service
public class MiddleManService {
    private final Logger log = LoggerFactory.getLogger(MiddleManService.class);

    @Value("${clientId}")
    protected String clientId;

    private AWSLambdaService awsLambdaService;
    private AWSSQSService awsSqsService;

    public MiddleManService(AWSLambdaService awsLambdaService, AWSSQSService awsSqsService) {
        this.awsLambdaService = awsLambdaService;
        this.awsSqsService = awsSqsService;
    }

    public JsonNode getTemplates() throws IOException {
        String payload = buildObjectMapper().writeValueAsString(LynxRequest.getTemplatesRequest());

        log.info("Sending get templates request: {}", payload);

        JsonNode response = awsLambdaService.invokeLynxLambda(payload);
        return buildObjectMapper().readTree(response.get("payload").textValue());
    }

    public JsonNode createTrackingEntry(String templateId, String templateName, List<String> companyIds) throws IOException {
        String userId = SecurityUtils.getCurrentUserId().toString();
        String userName = SecurityUtils.getCurrentUserLogin().get();
        String payload = buildObjectMapper().writeValueAsString(new TrackingRequest(clientId, userId, userName, templateId, templateName, companyIds));

        log.info("Sending tracking request: {}", payload);

        return awsLambdaService.invokeTrackingLambda(payload);
    }

    public JsonNode updateTrackingEntry(String collabKey, String errorMessage) throws IOException {
        String payload = buildObjectMapper().writeValueAsString(TrackingRequest.errorRequest(collabKey, errorMessage));

        log.info("Sending tracking update request: {}", payload);

        return awsLambdaService.invokeLynxLambda(payload);
    }

    public JsonNode executeLynxTemplateRequest(String collabKey, String templateId, List<String> companyIds) throws IOException {
        String payload = buildObjectMapper().writeValueAsString(LynxRequest.executeTemplateRequest(collabKey, templateId, companyIds));

        log.info("Sending execute template request: {}", payload);

        JsonNode response = awsLambdaService.invokeLynxLambda(payload);
        return buildObjectMapper().readTree(response.get("payload").textValue());
    }


    public void consumeMessages(Consumer<String> consumer) throws Exception {
        List<Message> messages = awsSqsService.readMessages();
        for (Message message : messages) {
            consumer.accept(message.body());
            awsSqsService.deleteMessage(message.receiptHandle());
        }
    }

    // TODO: move to config/bean
    private ObjectMapper buildObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // TODO: any object mapper settings
        return objectMapper;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
