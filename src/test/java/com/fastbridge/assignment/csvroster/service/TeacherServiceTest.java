package com.fastbridge.assignment.csvroster.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fastbridge.assignment.csvroster.dao.TeacherDao;
import com.fastbridge.assignment.csvroster.entity.Teacher;
import com.fastbridge.assignment.csvroster.service.TeacherService;
import com.fastbridge.assignment.csvroster.service.TeacherServiceImpl;

public class TeacherServiceTest {

	@Mock
	private TeacherDao teacherDao;

	private TeacherService teacherService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		teacherService = new TeacherServiceImpl(teacherDao);
	}

	@Test
	public void testAddTeacher() {
		Teacher teacher = getTestTeacher();
		List<Teacher> teacherList = new ArrayList<Teacher>();
		teacherList.add(teacher);
		teacherService.updateOrInsertTeacherDetails(teacherList);
		Mockito.verify(teacherDao, Mockito.times(1)).updateOrInsertTeacherDetails(teacherList);
	}

	private Teacher getTestTeacher() {
		Teacher teacher = new Teacher();
		teacher.setId(56445);
		teacher.setFirstName("Teacher");
		teacher.setLastName("Test");
		return teacher;
	}
}
