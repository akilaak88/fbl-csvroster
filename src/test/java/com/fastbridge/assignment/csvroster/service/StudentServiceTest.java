package com.fastbridge.assignment.csvroster.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fastbridge.assignment.csvroster.dao.StudentDao;
import com.fastbridge.assignment.csvroster.entity.Student;
import com.fastbridge.assignment.csvroster.service.StudentService;
import com.fastbridge.assignment.csvroster.service.StudentServiceImpl;


public class StudentServiceTest {

    @Mock
    private StudentDao studentDao;
    
    private StudentService studentService;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        studentService = new StudentServiceImpl(studentDao);
    }
    
    @Test
    public void testAddStudent() {
        Student student = getTestStudent();
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(student);
        studentService.updateOrInsertStudentDetails(studentList);
        Mockito.verify(studentDao, Mockito.times(1)).updateOrinsertStudentDetails(studentList);
    }
    
	
	  @Test 
	  public void testDeleteStudent() { 
		  Student student = getTestStudent();
	  studentService.deleteStudentDetails(student.getId());
	  Mockito.verify(studentDao,Mockito.times(1)).deleteStudentDetails(student.getId()); }
	  
	     
    private Student getTestStudent() {
        Student student = new Student();
        student.setId(55555);
        student.setFirstName("Test");
        student.setLastName("Name");
        return student;
    }

}
