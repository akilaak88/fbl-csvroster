package com.fastbridge.assignment.csvroster.service;

import java.util.List;

import org.apache.commons.csv.CSVParser;

import com.fastbridge.assignment.csvroster.entity.Enrollment;

public interface EnrollmentService {
	
	public void updateOrInsertEnrollmentDetails(List<Enrollment> enrollmentList);
	
	public int deleteEnrollment(int studentId);
	
	public void bindDetails(String link,String isDelta);

}
