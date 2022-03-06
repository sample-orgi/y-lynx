package com.trss.bi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trss.bi.domain.Report;
import com.trss.bi.domain.Status;
import com.trss.bi.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Service
public class TemplateService {

    private MiddleManService middleManService;
    private ReportService reportService;

    public TemplateService(MiddleManService middleManService, ReportService reportService) {
        this.middleManService = middleManService;
        this.reportService = reportService;
    }

    public String getTemplates() throws IOException {
        return middleManService.getTemplates().toString();
    }

    public String executeTemplate(String templateId, List<String> companyIds, String templateName) throws IOException {
        JsonNode trackingResultJson = middleManService.createTrackingEntry(templateId, templateName, companyIds);
        String collabKey = createReportEntry(trackingResultJson, templateId, companyIds, templateName);
        try {
            JsonNode executeResultJson = middleManService.executeLynxTemplateRequest(collabKey, templateId, companyIds);
            return new ObjectMapper().writeValueAsString(executeResultJson);
        }
        // if there's an exception executing, update our report record, and update the middleman tracking entry
        catch (Exception e) {
            Report report = reportService.findByCollabKey(collabKey);
            report.status(Status.ERRORED);
            report.message(e.getMessage());
            reportService.save(report);

            middleManService.updateTrackingEntry(collabKey, e.getMessage());

            throw e;
        }
    }

    private String createReportEntry(JsonNode resultJson, String templateId, List<String> companyIds, String templateName) {
        String collabKey = resultJson.get("collabKey").textValue();
        long trssCreatedDate = resultJson.get("createdDate").longValue();
        long trssLastUpdatedDate = resultJson.get("lastUpdatedDate").longValue();

        Report report = new Report();
        report.collabKey(collabKey)
            .templateId(templateId)
            .templateName(templateName)
            .companyIds(companyIds)
            .userId(SecurityUtils.getCurrentUserId())
            .customerId(SecurityUtils.getCurrentCustomerId())
            .status(Status.IN_PROGRESS)
            .trssCreatedDate(Instant.ofEpochMilli(trssCreatedDate))
            .trssUpdatedDate(Instant.ofEpochMilli(trssLastUpdatedDate));

        reportService.save(report);

        return collabKey;
    }
}
