package com.fastbridge.assignment.csvroster.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fastbridge.assignment.csvroster.entity.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

	Logger logger = LogManager.getLogger(StudentDaoImpl.class);
	
	private JdbcTemplate jdbcTemplate;	
	
	@Autowired
	public StudentDaoImpl(JdbcTemplate jdbcTemp) {
		jdbcTemplate = jdbcTemp;
	}
	
	/*
	 * Method : updateOrinsertStudentDetails 
	 * Purpose : To insert or update Student details to student table. If the student's record exists then
	 * an update to that record will be performed. Else, a new record will be added
	 * for that student. 
	 * Returns : None 
	 * Arguments : List of students.
	 */
	
	@Override
	@Transactional
	public void updateOrinsertStudentDetails(List<Student> studentList) {
		
		int updatedRows = 0;
		
		for(Student student:studentList) {
			
			int i =jdbcTemplate.update("update student set state_id=?,first_name=?,last_name=?,gender=?,birthdate=?,"
					+ "race=?,meal_status=?,english_proficiency=?,native_language=?,service_code=?,primary_disability_type=?,"
					+ "iep_reading=?,iep_math=?,iep_behavior=?,gifted_talented=?,section504=?,mobility=? where id=?",
					student.getStateId(),student.getFirstName(),student.getLastName(),
					student.getGender(),student.getBirthdate(),student.getRace(),student.getMealStatus(),
					student.getEnglishProficiency(),student.getNativeLanguage(),student.getServiceCode(),
					student.getPrimaryDisabilityType(),student.isIepReading(),student.isIepMath(),
					student.isIepBehavior(),student.isGiftedTalented(),student.isSection504(),student.isMobility(),student.getId());
			if(i==0) {
		
			 i = jdbcTemplate.update("insert into student values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					student.getId(),student.getStateId(),student.getFirstName(),student.getLastName(),
					student.getGender(),student.getBirthdate(),student.getRace(),student.getMealStatus(),
					student.getEnglishProficiency(),student.getNativeLanguage(),student.getServiceCode(),
					student.getPrimaryDisabilityType(),student.isIepReading(),student.isIepMath(),
					student.isIepBehavior(),student.isGiftedTalented(),student.isSection504(),student.isMobility());
			}
			
			updatedRows = updatedRows + i;
			
		}
		
		logger.info("Number of Rows Updated For Students" +updatedRows);
		
	}

	/*
	 * Method : deleteStudentDetails 
	 * Purpose : To delete Student details
	 * Returns : None 
	 * Arguments : List of students.
	 */
	
	@Override
	public void deleteStudentDetails(int StudentId) {
		
		    int updatedRows = jdbcTemplate.update("delete from student where id=?",StudentId);
		    logger.info("Number of Rows Deleted For Students" +updatedRows);

	}
	
	/*
	 * Method : getStudentCount
	 * Purpose : To report a list of each teacher in the database, and a count of how many students are in a class held by that teacher
	 * Returns : None
	 * Arguments : None
	 */

	@Override
	public void getStudentCount() {
		
	List<Map<String, Object>> result =	jdbcTemplate.queryForList("select teacher_id,rosters.teacher.first_name,last_name, rosters.enrollment.grade,count(rosters.enrollment.student_id) as student_count "
					+ "from rosters.enrollment,rosters.teacher "+
					"where rosters.enrollment.teacher_id=rosters.teacher.id "+
					"group by teacher_id,rosters.enrollment.grade");
		
		if(result!=null && !result.isEmpty()) {
			for (Map<String, Object> temp : result) {
				logger.info("--------------------------");
				for (Iterator<Map.Entry<String, Object>> it = temp.entrySet().iterator(); it.hasNext();) {
					Map.Entry<String, Object> entry = it.next();
					String key = entry.getKey();
					Object value = entry.getValue();
					logger.info(key + " = " + value);
				}
			}

		}

		
	}

}
