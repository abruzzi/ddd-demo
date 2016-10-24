package com.thoughtworks.ddd.repository;

import com.thoughtworks.ddd.domain.Assignment;

import java.util.Date;

public interface AssignmentRepository {
    boolean save(Assignment assignment);
    Assignment findOne(String id);

    boolean hasAssignmentBetween(String employeeId, Date start, Date end);
}
