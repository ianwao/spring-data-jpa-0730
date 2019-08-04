package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Student;
import com.example.demo.domain.Teacher;
import com.example.demo.domain.TeacherRepository;

@Service
public class TeacherService {
	@Autowired
	TeacherRepository teacherRepository;
	
	@Transactional(propagation=Propagation.REQUIRED)//开启事务，查询不需要
	public void saveTeacher(Teacher teacher) {
		teacherRepository.saveAndFlush(teacher);
	}
}
