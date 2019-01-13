package com.fastbridge.assignment.csvroster.dao;

import java.util.List;

import com.fastbridge.assignment.csvroster.entity.Enrollment;

public interface EnrollmentDao {
	
	public void updateOrInsertEnrollmentDetails(List<Enrollment> enrollmentList);
	
	public void deleteEnrollmentDetails(int studentId);

}
