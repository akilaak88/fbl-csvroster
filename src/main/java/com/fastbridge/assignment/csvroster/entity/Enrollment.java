package com.fastbridge.assignment.csvroster.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="enrollment")
public class Enrollment {
	
	@Id
	@Column(name="student_id")
	private int studentId;
	
	@Column(name="teacher_id")
	private int teacherId;
	
	@Column(name="grade")
	private String grade;
	
	@Column(name="course")
	private String course;
	
	@Column(name="section")
	private String section;
	
	public Enrollment() {
		
	}

	public Enrollment(int studentId, int teacherId, String grade, String course, String section) {
		this.studentId = studentId;
		this.teacherId = teacherId;
		this.grade = grade;
		this.course = course;
		this.section = section;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	
}
