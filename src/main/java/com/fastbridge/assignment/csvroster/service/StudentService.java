package com.fastbridge.assignment.csvroster.service;

import java.util.List;

import org.apache.commons.csv.CSVParser;

import com.fastbridge.assignment.csvroster.entity.Student;

public interface StudentService {
	
	public void updateOrInsertStudentDetails(List<Student> studentList);
	
	public int deleteStudentDetails(int studentId);
	
	public void getStudentCount();
	
	public void bindDetails(String link, String isDelta);

}
