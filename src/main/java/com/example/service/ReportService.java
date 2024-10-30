package com.example.service;

import com.example.model.Report;

import java.util.List;

public interface ReportService {
    Report findById(String id);

    List<Report> all();

    void add(Report report);
}
