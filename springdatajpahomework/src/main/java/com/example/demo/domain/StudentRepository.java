package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {
	@Modifying
	@Query("update Student s SET s.age=:studentage WHERE s.id=:id")
	public void updateStudent(@Param("studentage") Integer age, @Param("id") Integer id);
	
	@Modifying
	@Query("update Student s SET s.flag=:flag WHERE s.id=:id")
	public void updateFlagStudent(@Param("flag") Integer flag, @Param("id") Integer id);
	
//	@Modifying
//	@Query("SELECT s.id,s.studentName FROM student s,student_teacher a WHERE a.teacher_id=1")
//	public void selectStudent(@Param("flag") Integer flag, @Param("id") Integer id);
	
	
}
