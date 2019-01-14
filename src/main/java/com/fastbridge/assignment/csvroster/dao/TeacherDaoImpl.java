package com.fastbridge.assignment.csvroster.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fastbridge.assignment.csvroster.entity.Teacher;

@Repository
public class TeacherDaoImpl implements TeacherDao {
	
	Logger logger = LogManager.getLogger(TeacherDaoImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public TeacherDaoImpl(JdbcTemplate jdbcTemp) {
		jdbcTemplate = jdbcTemp;
	}
	
	/*
	 * Method : updateOrInsertTeacherDetails 
	 * Purpose : To insert or update Teacher details to teacher table. If the teacher's record exists then
	 * an update to that record will be performed. Else, a new record will be added
	 * for that teacher. 
	 * Returns : None 
	 * Arguments : List of teachers.
	 */

	@Override
	@Transactional
	public void updateOrInsertTeacherDetails(List<Teacher> teacherList) {
		
		int updatedRows = 0;	
		
		for(Teacher teacher : teacherList) {
			
			int i = jdbcTemplate.update("update teacher set first_name=?,last_name=?,email=?,school=? where "
					+ "id=?",teacher.getFirsName(),teacher.getLastName(),teacher.getEmail(),teacher.getSchool(),teacher.getId());
			
			if(i==0) {
				i= jdbcTemplate.update("insert into teacher values (?,?,?,?,?)",teacher.getId(),teacher.getFirsName(),teacher.getLastName(),teacher.getEmail(),teacher.getSchool());
				
			}
			
			updatedRows = updatedRows+i;
		}
		logger.info("Number of Rows Updated for Teacher " +updatedRows);

	}
	
	

}
