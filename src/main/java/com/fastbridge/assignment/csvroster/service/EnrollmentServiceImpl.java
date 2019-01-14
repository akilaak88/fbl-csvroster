package com.fastbridge.assignment.csvroster.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastbridge.assignment.csvroster.dao.EnrollmentDao;
import com.fastbridge.assignment.csvroster.entity.Enrollment;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	
	Logger logger = LogManager.getLogger(EnrollmentServiceImpl.class);
	private String NAME_PATTERN = "[0-9A-Za-z]+";
	
	private EnrollmentDao enrollmentDao;
	
	@Autowired
	public EnrollmentServiceImpl(EnrollmentDao theEnrollmentDao) {
		enrollmentDao = theEnrollmentDao;
	}

	@Override
	public void updateOrInsertEnrollmentDetails(List<Enrollment> enrollmentList) {
		enrollmentDao.updateOrInsertEnrollmentDetails(enrollmentList);

	}

	@Override
	public int deleteEnrollment(int studentId) {

		return enrollmentDao.deleteEnrollmentDetails(studentId);
		
	}
	
	/*
	 * Method Name: bindDetails
	 * Purpose : To Set the values from the csvParser to the fields of Student, Teacher & Enrollment objects and perform the 
	 * 			 insert, update or delete actions in the database.
	 * Returns : None
	 * Arguments : CSVParser
	 */
	
	@Override
	public void bindDetails(String link,String isDelta) {
		List<Enrollment> enrollmentList = new ArrayList<Enrollment>();
		int deletedEnrollments = 0;
		try {
			URL csvURL = new URL(link);
			HttpURLConnection csvURLHttp = (HttpURLConnection) csvURL.openConnection();
			Map<String, List<String>> csvHeader = csvURLHttp.getHeaderFields();
			for (String header : csvHeader.get(null)) {
				if (header.contains("302")) {
					link = csvHeader.get("Location").get(0);
					csvURL = new URL(link);
					csvURLHttp = (HttpURLConnection) csvURL.openConnection();
					csvHeader = csvURLHttp.getHeaderFields();
				}
			}

			Reader reader = new BufferedReader(new InputStreamReader(csvURLHttp.getInputStream()));

			CSVParser csvParser = new CSVParser(reader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

		
		for (CSVRecord csvRecord : csvParser) {
			if (isDelta.equalsIgnoreCase("Y") && csvRecord.get("Action").equalsIgnoreCase("D")) {
				deletedEnrollments = deletedEnrollments + deleteEnrollment(Integer.parseInt(csvRecord.get("StudentID")));
			}else {
				if ((!csvRecord.get("StudentID").matches("[0-9]+") || csvRecord.get("StudentID").length() > 7)
						|| (!csvRecord.get("StudentFirstName").matches(NAME_PATTERN)
								|| !csvRecord.get("StudentLastName").matches(NAME_PATTERN))) {
					
				} else {
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
		updateOrInsertEnrollmentDetails(enrollmentList);
		logger.info("Number of Enrollments Deleted :" +deletedEnrollments);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
