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
import org.springframework.web.bind.annotation.RestController;

import com.fastbridge.assignment.csvroster.entity.Enrollment;
import com.fastbridge.assignment.csvroster.entity.Student;
import com.fastbridge.assignment.csvroster.entity.Teacher;
import com.fastbridge.assignment.csvroster.service.EnrollmentService;
import com.fastbridge.assignment.csvroster.service.StudentService;
import com.fastbridge.assignment.csvroster.service.TeacherService;

@RestController
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
	public void readCSV() throws Exception {

		String link = System.getProperty("path");
		logger.info("File Path***" + link);
		try {
			URL csvurl = new URL(link);
			HttpURLConnection csvurlHttp = (HttpURLConnection) csvurl.openConnection();
			Map<String, List<String>> csvHeader = csvurlHttp.getHeaderFields();
			for (String header : csvHeader.get(null)) {
				if (header.contains("302")) {
					link = csvHeader.get("Location").get(0);
					csvurl = new URL(link);
					csvurlHttp = (HttpURLConnection) csvurl.openConnection();
					csvHeader = csvurlHttp.getHeaderFields();
				}
			}

			Reader reader = new BufferedReader(new InputStreamReader(csvurlHttp.getInputStream()));

			CSVParser csvParser = new CSVParser(reader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			bindDetails(csvParser);
			getStudentCount();

		} catch (MalformedURLException e) {
			System.out.println("Please enter a Valid File Path");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Method Name: bindDetails
	 * Purpose : To Set the values from the csvParser to the fields of Student, Teacher & Enrollment objects and perform the 
	 * 			 insert, update or delete actions in the database.
	 * Returns : None
	 * Arguments : CSVParser
	 */
	public void bindDetails(CSVParser csvParser) {
		List<Student> studentList = new ArrayList<Student>();
		List<Teacher> teacherList = new ArrayList<Teacher>();
		List<Enrollment> enrollmentList = new ArrayList<Enrollment>();
		String isDelta = System.getProperty("isDelta");
		int inValidStudentId = 0;
		try {
			for (CSVRecord csvRecord : csvParser) {
				if (isDelta.equalsIgnoreCase("Y") && csvRecord.get("Action").equalsIgnoreCase("D")) {
					studentService.deleteStudentDetails(Integer.parseInt(csvRecord.get("StudentID")));
					enrollmentService.deleteEnrollment(Integer.parseInt(csvRecord.get("StudentID")));
				} else {
					if ((!csvRecord.get("StudentID").matches("[0-9]+") || csvRecord.get("StudentID").length() > 7)
							|| (!csvRecord.get("StudentFirstName").matches("[0-9A-Za-z]+")
									|| !csvRecord.get("StudentLastName").matches("[0-9A-Za-z]+"))) {
						inValidStudentId++;
					} else {
						Student student = new Student();
						student.setId(Integer.parseInt(csvRecord.get("StudentID")));
						student.setStateId(csvRecord.get("StudentStateID"));
						student.setFirstName(csvRecord.get("StudentFirstName"));
						student.setLastName(csvRecord.get("StudentLastName"));
						student.setGender(csvRecord.get("StudentGender"));
						student.setBirthdate(new SimpleDateFormat("MM/dd/yy").parse(csvRecord.get("StudentBirthDate")));

						student.setRace(csvRecord.get("StudentRace"));
						student.setMealStatus(csvRecord.get("MealStatus"));
						student.setEnglishProficiency(csvRecord.get("EnglishProficiency"));
						student.setNativeLanguage(csvRecord.get("NativeLanguage"));
						student.setServiceCode(csvRecord.get("ServiceCode"));
						student.setPrimaryDisabilityType(csvRecord.get("PrimaryDisabilityType"));
						student.setIepReading(Boolean.parseBoolean(csvRecord.get("IEPReading")));
						student.setIepMath(Boolean.parseBoolean(csvRecord.get("IEPMath")));
						student.setIepBehavior(Boolean.parseBoolean(csvRecord.get("IEPBehavior")));
						student.setGiftedTalented(Boolean.parseBoolean(csvRecord.get("GiftedAndTalented")));
						student.setSection504(Boolean.parseBoolean(csvRecord.get("Section504")));
						student.setMobility(Boolean.parseBoolean(csvRecord.get("Mobility")));

						studentList.add(student);

						Teacher teacher = new Teacher();
						teacher.setId(Integer.parseInt(csvRecord.get("TeacherID")));
						teacher.setFirstName(csvRecord.get("TeacherFirstName"));
						teacher.setLastName(csvRecord.get("TeacherLastName"));
						teacher.setEmail(csvRecord.get("TeacherEmail"));
						teacher.setSchool(csvRecord.get("School"));

						teacherList.add(teacher);

						Enrollment enrollment = new Enrollment();
						enrollment.setStudentId(Integer.parseInt(csvRecord.get("StudentID")));
						enrollment.setTeacherId(Integer.parseInt(csvRecord.get("TeacherID")));
						enrollment.setGrade(csvRecord.get("Grade"));
						enrollment.setSection(csvRecord.get("Section"));
						enrollment.setCourse(csvRecord.get("Course"));

						enrollmentList.add(enrollment);

					}
				}
			}

			studentService.updateOrInsertStudentDetails(studentList);

			teacherService.updateOrInsertTeacherDetails(teacherList);

			enrollmentService.updateOrInsertEnrollmentDetails(enrollmentList);

			logger.info("Number Of Records rejected :" + inValidStudentId);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Method : getStudentCount
	 * Purpose : To report a list of each teacher in the database, and a count of how many students are in a class held by that teacher
	 * Returns : None
	 * Arguments : None
	 */
	public void getStudentCount() {
		studentService.getStudentCount();
	}

}
