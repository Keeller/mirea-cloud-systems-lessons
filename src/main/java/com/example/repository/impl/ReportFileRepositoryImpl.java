package com.example.repository.impl;

import com.example.repository.ReportFileRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

@ApplicationScoped
public class ReportFileRepositoryImpl implements ReportFileRepository {
    private static final String REPORT_FILE_PATH = "report";

    static {
        var path = Path.of(REPORT_FILE_PATH);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public byte[] getReportFile(String path) {
        byte[] content;
        try {
            content = Files.readAllBytes(Path.of(REPORT_FILE_PATH + FileSystems.getDefault().getSeparator() + path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }

    @Override
    public void putReportFile(String path, byte[] file) {
        try {
            Files.write(Path.of(REPORT_FILE_PATH + FileSystems.getDefault().getSeparator() + path), file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
