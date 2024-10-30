package com.example;

import com.example.dto.CreateReportRequest;
import com.example.dto.FilledReport;
import com.example.dto.ReportId;
import com.example.dto.UserReport;
import com.example.usecase.CreateReport;
import com.example.usecase.FillReport;
import com.example.usecase.GetReport;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.Base64;

@Path("/report")
public class ReportResource {

    private final CreateReport createReport;
    private final FillReport fillReport;
    private final GetReport getReport;

    @Inject
    public ReportResource(CreateReport createReport, FillReport fillReport, GetReport getReport) {
        this.createReport = createReport;
        this.fillReport = fillReport;
        this.getReport = getReport;
    }

    @POST
    public ReportId createReport(CreateReportRequest createReportRequest) {
        var data = Base64.getDecoder().decode(createReportRequest.file());
        var filename = createReportRequest.name();
        var params = createReportRequest.content();
        return new ReportId(createReport.createReport(filename, params, data));
    }

    @GET
    @Path("/fill/{id}")
    public FilledReport filledReport(@PathParam("id") String id) throws JsonProcessingException {
        return new FilledReport(Base64.getEncoder().encodeToString(fillReport.fillReport(id)));
    }

    @GET
    @Path("/{id}")
    public UserReport getById(@PathParam("id") String id) {
        return getReport.getReport(id);
    }
}
