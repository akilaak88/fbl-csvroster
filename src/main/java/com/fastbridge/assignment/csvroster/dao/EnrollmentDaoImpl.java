package com.fastbridge.assignment.csvroster.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fastbridge.assignment.csvroster.entity.Enrollment;

@Repository
public class EnrollmentDaoImpl implements EnrollmentDao {

	Logger logger = LogManager.getLogger(EnrollmentDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public EnrollmentDaoImpl(JdbcTemplate jdbcTemp) {
		jdbcTemplate = jdbcTemp;
	}

	/*
	 * Method : updateOrInsertEnrollmentDetails 
	 * Purpose : To insert or update Enrollment details to enrollment table. If the student's record exists then
	 * an update to that record will be performed. Else, a new record will be added
	 * for that student. 
	 * Returns : None 
	 * Arguments : List of Enrollments.
	 */

	@Override
	@Transactional
	public void updateOrInsertEnrollmentDetails(List<Enrollment> enrollmentList) {
		int updatedRows = 0;
		System.out.println("enrollmentList size" +enrollmentList.size());
		for (Enrollment enrollment : enrollmentList) {

			int i = jdbcTemplate.update(
					"update enrollment set grade=?,course=?,section=?,teacher_id=? where student_id=?",
					enrollment.getGrade(), enrollment.getCourse(), enrollment.getSection(), enrollment.getTeacherId(),
					enrollment.getStudentId());

			if (i == 0) {
				i = jdbcTemplate.update("insert into enrollment values(?,?,?,?,?)", enrollment.getStudentId(),
						enrollment.getTeacherId(), enrollment.getGrade(), enrollment.getCourse(),
						enrollment.getSection());

			}
			updatedRows = updatedRows + i;
		}

		logger.info("Number of Rows Updated for Enrollment : " + updatedRows);

	}

	/*
	 * Method : deleteEnrollmentDetails 
	 * Purpose : To delete Enrollment details
	 * enrollment table. Returns : None Arguments : List of Enrollments.
	 */

	@Override
	public int deleteEnrollmentDetails(int studentId) {

		int updatedRows = jdbcTemplate.update("delete from enrollment where student_id=? ", studentId);
		return updatedRows;
	}

}
