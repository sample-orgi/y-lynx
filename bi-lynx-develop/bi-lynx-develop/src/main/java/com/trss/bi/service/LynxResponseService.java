package com.trss.bi.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trss.bi.domain.Report;
import com.trss.bi.domain.Status;
import com.trss.bi.service.dto.LynxResponseDto;
import com.trss.bi.service.dto.LynxResponseMessageAttributesDto;
import com.trss.bi.service.dto.ReportRestorePropertiesDto;
import com.trss.bi.service.parser.LynxParserOutput;
import com.trss.bi.service.parser.LynxResponseParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import software.amazon.awssdk.core.exception.SdkClientException;

import java.time.Instant;
import java.util.Map;

@Service
public class LynxResponseService {
    private final Logger log = LoggerFactory.getLogger(LynxResponseService.class);

    private final static int JOB_FREQUENCY_MS = 1 * 60 * 1000; // Run every minute
    private final static String STANDARD_RESPONSE_TYPE = "Notification";
    private final static String RESTORE_RESPONSE_TYPE = "Restore";

    private static final Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .create();

    private MiddleManService middleManService;
    private ReportService reportService;

    public LynxResponseService(MiddleManService middleManService, ReportService reportService) {
        this.middleManService = middleManService;
        this.reportService = reportService;
    }

    @Scheduled(fixedDelay = JOB_FREQUENCY_MS)
    public void processLynxResponses() throws Exception {
        try {
            middleManService.consumeMessages(response -> {
                LynxResponseDto lynxResponse = gson.fromJson(response, LynxResponseDto.class);

                if (STANDARD_RESPONSE_TYPE.equals(lynxResponse.getType())) {
                    String collabKey = null;
                    LynxResponseMessageAttributesDto responseMessageAttributes = lynxResponse.getMessageAttributes();
                    if (responseMessageAttributes != null) {
                        Map<String, String> correlationIdMap = responseMessageAttributes.getCorrelationId();
                        if (!CollectionUtils.isEmpty(correlationIdMap)) {
                            collabKey = responseMessageAttributes.getCorrelationId().get("Value");
                            if (StringUtils.isEmpty(collabKey)) {
                                log.error("CorrelationId not found in lynxResponse: {}", lynxResponse);
                            }
                        } else {
                            log.error("CorrelationId not found in lynxResponse: {}", lynxResponse);
                        }
                    } else {
                        log.error("CorrelationId not found in lynxResponse: {}", lynxResponse);
                    }

                    if (!StringUtils.isEmpty(collabKey)) {
                        String xml = lynxResponse.getMessage();

                        if (!StringUtils.isEmpty(xml)) {
                            LynxParserOutput parserOutput = LynxResponseParser.newInstance().parseResponse(xml);

                            if (!StringUtils.isEmpty(xml)) {
                                reportService.updateFromLynxOutput(collabKey, parserOutput);
                            } else {
                                log.error("No report xhtml found for Lynx response: {}", response);
                            }
                        } else {
                            log.error("No Message found for Lynx response: {}", response);
                        }
                    } else {
                        log.error("CorrelationId not found in lynxResponse: {}", lynxResponse);
                    }
                } else if (RESTORE_RESPONSE_TYPE.equals(lynxResponse.getType())) {
                    ReportRestorePropertiesDto restoreProperties = lynxResponse.getReportRestoreProperties();

                    LynxParserOutput parserOutput = LynxResponseParser.newInstance().parseResponse(lynxResponse.getMessage());

                    Report report = new Report();
                    report.collabKey(restoreProperties.getCollabKey())
                        .templateId(restoreProperties.getTemplateId())
                        .templateName(restoreProperties.getTemplateName())
                        .companyIds(restoreProperties.getCompanyIds())
                        .userId(Long.valueOf(restoreProperties.getRequestUserId()))
                        .status(Status.COMPLETED)
                        .xhtml(parserOutput.getXhtml())
                        .trssCreatedDate(Instant.ofEpochMilli(restoreProperties.getCreatedDate()))
                        .trssUpdatedDate(Instant.ofEpochMilli(restoreProperties.getLastUpdatedDate()));

                    report.setCreatedBy(restoreProperties.getRequestUserName());
                    report.setCreatedDate(Instant.ofEpochMilli(restoreProperties.getCreatedDate()));
                    report.setLastModifiedBy(restoreProperties.getRequestUserName());
                    report.setLastModifiedDate(Instant.ofEpochMilli(restoreProperties.getLastUpdatedDate()));

                    reportService.save(report);
                } else {
                    log.error("Invalid Type: {}", lynxResponse.getType());
                }


            });
        } catch(SdkClientException e) {
            // catching and logging out as warning due to sporadic connection errors
            log.warn("Unexpected SdkClientException", e);
        }
    }
}
