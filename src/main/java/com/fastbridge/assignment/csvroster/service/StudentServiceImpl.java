package com.fastbridge.assignment.csvroster.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
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
import org.springframework.stereotype.Service;

import com.fastbridge.assignment.csvroster.dao.StudentDao;
import com.fastbridge.assignment.csvroster.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	Logger logger = LogManager.getLogger(StudentServiceImpl.class);
	private String NAME_PATTERN = "[0-9A-Za-z]+";
	
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
	public int deleteStudentDetails(int StudentId) {
		return studentDao.deleteStudentDetails(StudentId);
		
	}

	@Override
	public void getStudentCount() {
		studentDao.getStudentCount();
		
	}
	
	/*
	 * Method Name: bindDetails
	 * Purpose : To Set the values from the csvParser to the fields of Student, Teacher & Enrollment objects and perform the 
	 * 			 insert, update or delete actions in the database.
	 * Returns : None
	 * Arguments : CSVParser
	 */
	
	@Override
	public void bindDetails(String link, String isDelta) {
		List<Student> studentList = new ArrayList<Student>();
		int inValidStudentId = 0;
		int deletedStudents = 0;
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
					deletedStudents = deletedStudents
							+ deleteStudentDetails(Integer.parseInt(csvRecord.get("StudentID")));
				} else {
					if ((!csvRecord.get("StudentID").matches("[0-9]+") || csvRecord.get("StudentID").length() > 7)
							|| (!csvRecord.get("StudentFirstName").matches(NAME_PATTERN)
									|| !csvRecord.get("StudentLastName").matches(NAME_PATTERN))) {
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
					}
				}
			}
			updateOrInsertStudentDetails(studentList);
			logger.info("Number of Students Deleted :" +deletedStudents);
			logger.info("Number Of Records rejected :" + inValidStudentId);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
		 }

