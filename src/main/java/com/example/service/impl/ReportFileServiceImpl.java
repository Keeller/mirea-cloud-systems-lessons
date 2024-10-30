package com.example.service.impl;

import com.example.repository.ReportFileRepository;
import com.example.service.ReportFileService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@ApplicationScoped
public class ReportFileServiceImpl implements ReportFileService {
    private final ReportFileRepository reportFileRepository;

    @Inject
    public ReportFileServiceImpl(ReportFileRepository reportFileRepository) {
        this.reportFileRepository = reportFileRepository;
    }

    @Override
    public byte[] getReportFile(String path) {
        return reportFileRepository.getReportFile(path);
    }

    @Override
    public void putReportFile(String path, byte[] file) {
        reportFileRepository.putReportFile(path, file);
    }
}
