package com.example.repository;

public interface ReportFileRepository {
    byte[] getReportFile(String path);

    void putReportFile(String path, byte[] file);
}
