package com.example.usecase;

import com.example.dto.UserReport;
import com.example.service.ReportFileService;
import com.example.service.ReportService;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Base64;

@ApplicationScoped
public class GetReport {
    private final ReportService reportService;
    private final ReportFileService reportFileRepository;

    public GetReport(ReportService reportService, ReportFileService reportFileRepository) {
        this.reportService = reportService;
        this.reportFileRepository = reportFileRepository;
    }

    public UserReport getReport(String id) {
        var report = reportService.findById(id);
        var file = reportFileRepository.getReportFile(report.filename());
        return new UserReport(report.id(), report.filename(), Base64.getEncoder().encodeToString(file));
    }
}
