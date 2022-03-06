package com.trss.bi.service;

import com.trss.bi.service.dto.ReportDTO;
import org.springframework.stereotype.Service;

import java.io.OutputStream;

@Service
public class ExportService {
    private WordDocService wordDocService;
    private ReportService reportService;

    public ExportService(WordDocService wordDocService, ReportService reportService) {
        this.wordDocService = wordDocService;
        this.reportService = reportService;
    }

    public void exportReportAsDocx(String reportId, OutputStream os) {
        ReportDTO reportDTO = reportService.find(reportId);
        if (reportDTO != null) {
            wordDocService.convertXhtmlToDocx(reportDTO.getXhtml(), os);
        }
    }
}
