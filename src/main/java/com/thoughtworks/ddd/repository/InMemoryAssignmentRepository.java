package com.thoughtworks.ddd.repository;

import com.thoughtworks.ddd.domain.Assignment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InMemoryAssignmentRepository implements AssignmentRepository {
    private List<Assignment> assignmentList = new ArrayList<>();

    @Override
    public boolean save(Assignment assignment) {
        return assignmentList.add(assignment);
    }

    @Override
    public Assignment findOne(String id) {
        return assignmentList.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public boolean hasAssignmentBetween(String employeeId, Date start, Date end) {
        return false;
    }
}
