package com.fastbridge.assignment.csvroster.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.fastbridge.assignment.csvroster.entity.Enrollment;
import com.fastbridge.assignment.csvroster.entity.Student;
import com.fastbridge.assignment.csvroster.service.EnrollmentService;
import com.fastbridge.assignment.csvroster.service.StudentService;
import com.fastbridge.assignment.csvroster.service.TeacherService;

@Controller
@Component
public class MainController {

	Logger logger = LogManager.getLogger(MainController.class);

	private StudentService studentService;
	private TeacherService teacherService;
	private EnrollmentService enrollmentService;

	@Autowired
	public MainController(StudentService theStudentService, TeacherService theTeacherService,
			EnrollmentService theEnrollmentService) {
		studentService = theStudentService;
		teacherService = theTeacherService;
		enrollmentService = theEnrollmentService;
	}

	/*
	 * Method Name : readCSV()
	 * Purpose : to Read the CSV file which is given as an input in the command Line
	 * Returns : None
	 * Arguments : None
	 */
	public void readCSV(String link,String isDelta) throws Exception {

		//String link = System.getProperty("path");
		logger.info("File Path***" + link);
		
			studentService.bindDetails(link, isDelta);
			teacherService.bindDetails(link, isDelta);
			enrollmentService.bindDetails(link, isDelta);
			studentService.getStudentCount();

		} 

	
}
