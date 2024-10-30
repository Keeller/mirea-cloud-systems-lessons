package com.example.usecase;

import com.example.service.ReportFileService;
import com.example.service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Map;
import java.util.regex.Pattern;

@ApplicationScoped
public class FillReport {

    private static final String FILE_NAME_PATTERN = "\\$\\{(.+)}";
    private static final Pattern REPLASE_PATTERN = Pattern.compile(FILE_NAME_PATTERN);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final ReportService reportService;
    private final ReportFileService reportFileRepository;

    @Inject
    public FillReport(ReportService reportService, ReportFileService reportFileRepository) {
        this.reportService = reportService;
        this.reportFileRepository = reportFileRepository;
    }

    public byte[] fillReport(String id) throws JsonProcessingException {
        var report = reportService.findById(id);

        var context = OBJECT_MAPPER.readValue(report.reportData(), new TypeReference<Map<String, String>>() {
        });

        var reportFile = reportFileRepository.getReportFile(report.filename());
        return REPLASE_PATTERN.matcher(new String(reportFile)).replaceAll(matchResult -> {
            var name = matchResult.group(1);
            return context.get(name);
        }).getBytes();
    }
}
