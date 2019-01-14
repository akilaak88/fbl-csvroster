package com.fastbridge.assignment.csvroster.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fastbridge.assignment.csvroster.dao.EnrollmentDao;
import com.fastbridge.assignment.csvroster.entity.Enrollment;
import com.fastbridge.assignment.csvroster.service.EnrollmentService;
import com.fastbridge.assignment.csvroster.service.EnrollmentServiceImpl;


public class EnrollmentServiceTest {

	@Mock
    private EnrollmentDao enrollmentDao;
    
 
    private EnrollmentService enrollmentService;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        enrollmentService = new EnrollmentServiceImpl(enrollmentDao);
    }
  
    @Test
    public void testAddEnrollment() {
        Enrollment enrollment = getTestEnrollment();
        List<Enrollment> enrollmentList = new ArrayList<Enrollment>();
        enrollmentList.add(enrollment);
        enrollmentService.updateOrInsertEnrollmentDetails(enrollmentList);
        Mockito.verify(enrollmentDao, Mockito.times(1)).updateOrInsertEnrollmentDetails(enrollmentList);
    }
    
    @Test
    public void testDeleteEnrollment() {
    	Enrollment enrollment = getTestEnrollment();
        enrollmentService.deleteEnrollment(enrollment.getStudentId());
        Mockito.verify(enrollmentDao, Mockito.times(1)).deleteEnrollmentDetails(enrollment.getStudentId());
    }
    
    private Enrollment getTestEnrollment() {
    	Enrollment enrollment = new Enrollment();
    	enrollment.setStudentId(345452);
    	enrollment.setTeacherId(6723468);
    	enrollment.setCourse("KG");
        return enrollment;
    }

}
