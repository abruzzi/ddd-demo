package com.thoughtworks.ddd.domain.model.project;

import java.util.Date;
import java.util.UUID;

public class Assignment {
    private String employeeId;
    private String projectId;
    private String role;

    private String id;

    private Date from;
    private Date to;

    public String getId() {
        return id;
    }

    public Assignment(String employeeId, String projectId, String role, Date from, Date to) {
        this.id = UUID.randomUUID().toString();
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.role = role;
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

    public String getRole() {
        return role;
    }
}
