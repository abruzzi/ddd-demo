package com.thoughtworks.ddd.domain.model.project;

import java.util.Date;

public interface AssignmentRepository {
    boolean save(Assignment assignment);
    boolean hasAssignmentBetween(String employeeId, Date start, Date end);
    Assignment currentAssignmentFor(String employeeId);
}
