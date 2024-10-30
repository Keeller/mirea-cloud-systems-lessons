package com.example.repository;

import com.example.model.Report;

import java.util.List;

public interface ReportRepository {
    Report findById(String id);

    List<Report> all();

    void add(Report report);
}
