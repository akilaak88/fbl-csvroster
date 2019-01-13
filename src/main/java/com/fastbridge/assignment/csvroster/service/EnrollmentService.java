package com.fastbridge.assignment.csvroster.service;

import java.util.List;

import com.fastbridge.assignment.csvroster.entity.Enrollment;

public interface EnrollmentService {
	
	public void updateOrInsertEnrollmentDetails(List<Enrollment> enrollmentList);
	
	public void deleteEnrollment(int studentId);

}
