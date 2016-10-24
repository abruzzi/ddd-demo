package com.thoughtworks.ddd.domain;

import java.util.Date;
import java.util.UUID;

public class Assignment {
    private String employeeId;
    private String projectId;

    private String id;

    private Date from;
    private Date to;

    public String getId() {
        return id;
    }

    public Assignment(String employeeId, String projectId, Date from, Date to) {
        this.id = UUID.randomUUID().toString();
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.from = from;
        this.to = to;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getProjectId() {
        return projectId;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }
}
