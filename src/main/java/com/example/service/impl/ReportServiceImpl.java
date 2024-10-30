package com.example.service.impl;

import com.example.model.Report;
import com.example.repository.ReportRepository;
import com.example.service.ReportService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@ApplicationScoped
public class ReportServiceImpl implements ReportService {
    private final ReportRepository repository;

    @Inject
    public ReportServiceImpl(ReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public Report findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Report> all() {
        return repository.all();
    }

    @Override
    public void add(Report report) {
        repository.add(report);
    }


}
