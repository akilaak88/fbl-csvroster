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
	
	/*
	 * Method Name: bindDetails
	 * Purpose : To Set the values from the csvParser to the fields of Student, Teacher & Enrollment objects and perform the 
	 * 			 insert, update or delete actions in the database.
	 * Returns : None
	 * Arguments : CSVParser
	 */
	
	@Override
	public void bindDetails(String link,String isDelta) {
		
		List<Teacher> teacherList = new ArrayList<Teacher>();
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
			Teacher teacher = new Teacher();
			teacher.setId(Integer.parseInt(csvRecord.get("TeacherID")));
			teacher.setFirstName(csvRecord.get("TeacherFirstName"));
			teacher.setLastName(csvRecord.get("TeacherLastName"));
			teacher.setEmail(csvRecord.get("TeacherEmail"));
			teacher.setSchool(csvRecord.get("School"));

			teacherList.add(teacher);

		}
		updateOrInsertTeacherDetails(teacherList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
