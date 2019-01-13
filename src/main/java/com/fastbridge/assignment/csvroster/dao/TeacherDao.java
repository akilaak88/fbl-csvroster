package com.fastbridge.assignment.csvroster.dao;

import java.util.List;

import com.fastbridge.assignment.csvroster.entity.Teacher;

public interface TeacherDao {
	
	public void updateOrInsertTeacherDetails(List<Teacher> teacherList);

}
