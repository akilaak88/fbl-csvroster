package com.fastbridge.assignment.csvroster.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fastbridge.assignment.csvroster.dao.EnrollmentDao;
import com.fastbridge.assignment.csvroster.entity.Enrollment;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	
	private EnrollmentDao enrollmentDao;
	
	public EnrollmentServiceImpl(EnrollmentDao theEnrollmentDao) {
		enrollmentDao = theEnrollmentDao;
	}

	@Override
	public void updateOrInsertEnrollmentDetails(List<Enrollment> enrollmentList) {
		enrollmentDao.updateOrInsertEnrollmentDetails(enrollmentList);

	}

	@Override
	public void deleteEnrollment(int studentId) {

		enrollmentDao.deleteEnrollmentDetails(studentId);
		
	}

}
