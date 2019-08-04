package com.example.demo.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="teacher")
//@NamedQuery(name="Teacher.findAll",query="select t from teacher t")
public class Teacher {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private Integer id;
	
	@Column(name="teacher_name")
	private String teacherName;
	
	@Column(name="student_index")
	private String studentIndex;
	
	@Column(name="subject")
	private String subject;
	
	@Column(name="flag")
	private Integer flag;
	
	@Column(name="teacher_age")
	private Integer teacherAge;
	
	@Column(name="teacher_sex")
	private String teacherSex;
	
	@ManyToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
	private List<Student> student;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getStudentIndex() {
		return studentIndex;
	}

	public void setStudentIndex(String studentIndex) {
		this.studentIndex = studentIndex;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getTeacherAge() {
		return teacherAge;
	}

	public void setTeacherAge(Integer teacherAge) {
		this.teacherAge = teacherAge;
	}

	public String getTeacherSex() {
		return teacherSex;
	}

	public void setTeacherSex(String teacherSex) {
		this.teacherSex = teacherSex;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
