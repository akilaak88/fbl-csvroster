package com.fastbridge.assignment.csvroster.dao;

import java.util.List;

import com.fastbridge.assignment.csvroster.entity.Student;

public interface StudentDao {
	
	public void updateOrinsertStudentDetails(List<Student> studentList);
	
	public int deleteStudentDetails(int StudentId);
	
	public void getStudentCount();

}
