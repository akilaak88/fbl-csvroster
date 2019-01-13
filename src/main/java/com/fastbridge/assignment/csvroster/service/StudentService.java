package com.fastbridge.assignment.csvroster.service;

import java.util.List;

import com.fastbridge.assignment.csvroster.entity.Student;

public interface StudentService {
	
	public void updateOrInsertStudentDetails(List<Student> studentList);
	
	public void deleteStudentDetails(int studentId);
	
	public void getStudentCount();

}
