package com.example.repository.impl;

import com.example.model.Report;
import com.example.repository.ReportRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ReportRepositoryImpl implements ReportRepository {
    private static final Map<String, Report> reports = new HashMap<>();

    @Override
    public Report findById(String id) {
        return reports.get(id);
    }

    @Override
    public List<Report> all() {
        return new ArrayList<>(reports.values());
    }

    @Override
    public void add(Report report) {
        reports.put(report.id(), report);
    }
}
