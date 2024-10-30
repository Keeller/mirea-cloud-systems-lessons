package com.example.service;

public interface ReportFileService {
    byte[] getReportFile(String path);

    void putReportFile(String path, byte[] file);
}
