package com.trss.bi.service;

import com.trss.bi.domain.Report;
import com.trss.bi.domain.Status;
import com.trss.bi.repository.ReportRepository;
import com.trss.bi.security.AuthoritiesConstants;
import com.trss.bi.security.SecurityUtils;
import com.trss.bi.service.dto.ReportDTO;
import com.trss.bi.service.mapper.ReportMapper;
import com.trss.bi.service.parser.LynxParserOutput;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReportService {
    private final Logger log = LoggerFactory.getLogger(ReportService.class);

    private ReportRepository reportRepository;
    private ReportMapper reportMapper;
    public ReportService(ReportRepository reportRepository, ReportMapper reportMapper) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
    }

    public Report save(Report report) {
        return reportRepository.save(report);
    }

    public void delete(String id) {
        if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
            reportRepository.deleteById(new ObjectId(id));
        } else if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.CUSTOMER_ADMIN)) {
           reportRepository.deleteByIdAndCustomerId(new ObjectId(id), SecurityUtils.getCurrentCustomerId());
        } else {
            reportRepository.deleteByIdAndUserId(new ObjectId(id), SecurityUtils.getCurrentUserId());
        }
    }

    @Transactional
    public void updateFromLynxOutput(String collabKey, LynxParserOutput lynxParserOutput) {
        Report report = reportRepository.findByCollabKey(collabKey);
        if (report != null) {
            report.status(Status.COMPLETED).xhtml(lynxParserOutput.getXhtml());
            if (lynxParserOutput.getError() != null) {
                report.status(Status.ERRORED).message(lynxParserOutput.getError());
            }
            reportRepository.save(report);
        }
    }

    public ReportDTO find(String id) {
        Optional<Report> maybeReport = Optional.empty();
        if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
            maybeReport = reportRepository.findById(new ObjectId(id));
        } else if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.CUSTOMER_ADMIN)) {
            maybeReport = reportRepository.findByIdAndCustomerId(new ObjectId(id), SecurityUtils.getCurrentCustomerId());
        } else {
            maybeReport = reportRepository.findByIdAndUserId(new ObjectId(id), SecurityUtils.getCurrentUserId());
        }
        return reportMapper.toDto(maybeReport.orElse(null));
    }

    public Page<ReportDTO> findAll(Pageable pageable) {
        // return all reports for admins
        if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
            return findAllAdmin(pageable);
        }

        // customer admins can see all of their users' reports
        if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.CUSTOMER_ADMIN)) {
            return findAllByCustomerId(pageable, SecurityUtils.getCurrentCustomerId());
        }

        // users can only see their reports
        return findAllByOwner(pageable, SecurityUtils.getCurrentUserId());
    }

    private Page<ReportDTO> findAllAdmin(Pageable pageable) {
        Page<Report> page = reportRepository.findAll(pageable);
        return page.map(reportMapper::toDto);
    }

    private Page<ReportDTO> findAllByOwner(Pageable pageable, Long userId) {
        Page<Report> page = reportRepository.findByUserId(pageable, userId);
        return page.map(reportMapper::toDto);
    }

    private Page<ReportDTO> findAllByCustomerId(Pageable pageable, Long customerId) {
        Page<Report> page = reportRepository.findByCustomerId(pageable, customerId);
        return page.map(reportMapper::toDto);
    }

    public Report findByCollabKey(String collabKey) {
        return reportRepository.findByCollabKey(collabKey);
    }
}
