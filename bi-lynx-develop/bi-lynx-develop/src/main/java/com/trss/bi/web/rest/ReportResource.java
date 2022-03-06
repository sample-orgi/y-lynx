package com.trss.bi.web.rest;

import com.trss.bi.service.ExportService;
import com.trss.bi.service.ReportService;
import com.trss.bi.service.dto.ReportDTO;

import org.apache.commons.io.IOUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api")
public class ReportResource {
    private final Logger log = LoggerFactory.getLogger(ReportResource.class);

    private ReportService reportService;
    private ExportService exportService;

    public ReportResource(ReportService reportService, ExportService exportService) {
        this.reportService = reportService;
        this.exportService = exportService;
    }

    @GetMapping("/reports/{id}")
    public ReportDTO getReport(@PathVariable String id) {
        log.debug("REST request to getReport '{}'", id);
        return reportService.find(id);
    }

    @GetMapping("/reports")
    public Page<ReportDTO> getReports(Pageable pageable, @RequestParam(required = false) String name) {
        log.debug("REST request to getReports");
        return reportService.findAll(pageable);
    }

    @GetMapping("/reports/{id}/export")
    public ResponseEntity<InputStreamResource> generateWordReport(@PathVariable String id) throws Exception {
        log.debug("REST request to generateWordReport '{}'", id);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        String filename = "lynx-report-" + id + "-" + System.currentTimeMillis();

        try {
            exportService.exportReportAsDocx(id, os);
            return new ResponseEntity<>(
                new InputStreamResource(new ByteArrayInputStream(os.toByteArray())),
                buildWordReportHeaders(filename, os),
                HttpStatus.OK
            );
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

    private HttpHeaders buildWordReportHeaders(String filename, ByteArrayOutputStream os) {
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
        respHeaders.setContentLength(os.size());
        respHeaders.setContentDisposition(ContentDisposition.builder("attachment").filename(filename).build());
        return respHeaders;
    }

    @DeleteMapping("/reports/{id}")
    public void deleteReport(@PathVariable String id) {
        log.debug("REST request to deleteReport '{}'", id);
        reportService.delete(id);
    }
}
