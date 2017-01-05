package com.thoughtworks.ddd.port.adaptor.persistence;

import com.thoughtworks.ddd.domain.model.project.Assignment;
import com.thoughtworks.ddd.domain.model.project.AssignmentRepository;

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
    public boolean hasAssignmentBetween(String employeeId, Date start, Date end) {
        Assignment currentAssignment = assignmentList.stream().filter(x -> x.getEmployeeId().equals(employeeId)).findFirst().orElse(null);
        if(currentAssignment == null) {
            return false;
        }

        if(currentAssignment.getFrom().before(start) && currentAssignment.getTo().after(end)) {
            return true;
        }

        return false;
    }

    @Override
    public Assignment currentAssignmentFor(String employeeId) {
        return assignmentList.stream().filter(x -> x.getEmployeeId().equals(employeeId)).findFirst().orElse(null);
    }
}
