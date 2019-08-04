package com.example.demo.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "student")
@NamedQuery(name="Student.findAll",query="select s from Student s")
//因为懒加载这个对象属性只是一个代理对象，如果json直接当作一个存在的属性去序列化就会出现错误
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class Student implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "student_name")
	private String studentName;

	@Column(name = "student_age")
	private Integer age;

	@Column(name = "student_index")
	private String studentIndex;

	@Column(name = "student_birth")
	@Temporal(TemporalType.DATE) // 表示精确到天
	private Date studentBirth;

	@Column(name = "created_time")
	@Temporal(TemporalType.TIMESTAMP) // 表示精确到秒
	private Date createdTime;

	@Column(name = "flag")
	private Integer flag;

	@Column(name = "student_sex")
	private String studentSex;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "student_teacher", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "teacher_id"))
	private List<Teacher> teacher;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getStudentIndex() {
		return studentIndex;
	}

	public void setStudentIndex(String studentIndex) {
		this.studentIndex = studentIndex;
	}

	public Date getStudentBirth() {
		return studentBirth;
	}

	public void setStudentBirth(Date studentBirth) {
		this.studentBirth = studentBirth;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getStudentSex() {
		return studentSex;
	}

	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}

	public List<Teacher> getTeacher() {
		return teacher;
	}

	public void setTeacher(List<Teacher> teacher) {
		this.teacher = teacher;
	}

}
