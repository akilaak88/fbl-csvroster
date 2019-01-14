package com.fastbridge.assignment.csvroster.service;

import java.util.List;

import org.apache.commons.csv.CSVParser;

import com.fastbridge.assignment.csvroster.entity.Teacher;

public interface TeacherService {

	public void updateOrInsertTeacherDetails(List<Teacher> teacherList);
	
	public void bindDetails(String link,String isDelta);
	
}
