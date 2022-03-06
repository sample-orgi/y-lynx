package com.trss.bi.service.aws;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvocationType;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AWSLambdaService {

    @Value("${lambda.arn.tracking}")
    public String lambdaArnTracking;

    @Value("${lambda.arn.lynx}")
    public String lambdaArnLynx;

    private AWSLambda awsLambda;

    public AWSLambdaService(AWSLambda awsLambda) {
        this.awsLambda = awsLambda;
    }

    public JsonNode invokeTrackingLambda(String jsonPayload) throws IOException {
        return invokeLambda(lambdaArnTracking, jsonPayload);
    }

    public JsonNode invokeLynxLambda(String jsonPayload) throws IOException {
        return invokeLambda(lambdaArnLynx, jsonPayload);
    }

    public JsonNode invokeLambda(String lambdaName, String jsonPayload) throws IOException {
        InvokeRequest invokeRequest = new InvokeRequest()
            .withInvocationType(InvocationType.RequestResponse)
            .withFunctionName(lambdaName)
            .withPayload(jsonPayload);

        InvokeResult result = awsLambda.invoke(invokeRequest);

        String resultJson = new String(result.getPayload().array());

        if (StringUtils.isNotEmpty(result.getFunctionError())) {
            throw new RuntimeException("Function Error: " + result.getFunctionError() + ": " + new String(result.getPayload().array()));
        }

        if (result.getStatusCode() < 200 || result.getStatusCode() >= 300) {
            throw new RuntimeException("Error calling Lambda: " + result.getStatusCode() +
                " - " + result.getLogResult());
        }

        return new ObjectMapper().readTree(resultJson);
    }

    public String getLambdaArnTracking() {
        return lambdaArnTracking;
    }

    public void setLambdaArnTracking(String lambdaArnTracking) {
        this.lambdaArnTracking = lambdaArnTracking;
    }

    public String getLambdaArnLynx() {
        return lambdaArnLynx;
    }

    public void setLambdaArnLynx(String lambdaArnLynx) {
        this.lambdaArnLynx = lambdaArnLynx;
    }
}
