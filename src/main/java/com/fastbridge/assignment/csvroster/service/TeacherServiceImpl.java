package com.fastbridge.assignment.csvroster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastbridge.assignment.csvroster.dao.TeacherDao;
import com.fastbridge.assignment.csvroster.entity.Teacher;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	private TeacherDao teacherDao;
	
	@Autowired
	public TeacherServiceImpl(TeacherDao theTeacherDao) {
		teacherDao = theTeacherDao;
	}

	@Override
	public void updateOrInsertTeacherDetails(List<Teacher> teacherList) {
		teacherDao.updateOrInsertTeacherDetails(teacherList);

	}

}
