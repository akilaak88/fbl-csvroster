package com.fastbridge.assignment.csvroster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastbridge.assignment.csvroster.dao.StudentDao;
import com.fastbridge.assignment.csvroster.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	private StudentDao studentDao;
	
	@Autowired
	public StudentServiceImpl(StudentDao theStudentDao) {
		studentDao = theStudentDao;
	}

	@Override
	public void updateOrInsertStudentDetails(List<Student> studentList) {
		studentDao.updateOrinsertStudentDetails(studentList);

	}

	@Override
	public void deleteStudentDetails(int StudentId) {
		studentDao.deleteStudentDetails(StudentId);
		
	}

	@Override
	public void getStudentCount() {
		studentDao.getStudentCount();
		
	}

}
