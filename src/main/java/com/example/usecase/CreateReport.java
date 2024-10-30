package com.example.usecase;

import com.example.model.Report;
import com.example.service.ReportFileService;
import com.example.service.ReportService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class CreateReport {
    private final ReportService reportService;
    private final ReportFileService reportFileRepository;

    @Inject
    public CreateReport(ReportService reportService, ReportFileService reportFileRepository) {
        this.reportService = reportService;
        this.reportFileRepository = reportFileRepository;
    }

    public String createReport(String name, String context, byte[] file) {
        var id = UUID.randomUUID().toString();
        reportService.add(new Report(id, name, context));
        reportFileRepository.putReportFile(name, file);
        return id;
    }
}
