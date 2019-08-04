package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.Student;
import com.example.demo.domain.Teacher;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonMappingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringdatajpahomeworkApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(SpringdatajpahomeworkApplicationTests.class);

	@Autowired
	StudentService studentService;

	@Autowired
	TeacherService teacherService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void TestSaveStudent() {
		for (int i = 1; i < 5; i++) {
			Student student = new Student();
			student.setStudentName("小一" + i);
			student.setAge(12);
			student.setFlag(1);
			student.setStudentBirth(new Date());
			studentService.saveStudent(student);
		}

		Student student2 = new Student();
		student2.setStudentName("小二");
		student2.setAge(12);
		student2.setFlag(1);
		student2.setStudentBirth(new Date());

		List<Student> listStudent = new ArrayList<Student>();
		listStudent.add(student2);

		Teacher teacher = new Teacher();
		teacher.setTeacherName("小李");
		teacher.setFlag(1);
		teacher.setTeacherSex("男");

		List<Teacher> listTeacher = new ArrayList<Teacher>();
		listTeacher.add(teacher);

		teacher.setStudent(listStudent);
		student2.setTeacher(listTeacher);

		studentService.saveStudent(student2);
		teacherService.saveTeacher(teacher);

	}

	@Test
	public void TestFandStudent() {
		Student student = studentService.findStudent(1);
		System.out.println(student.toString());
	}

	@Test
	public void TestDelectStudent() {
		Student student = studentService.findStudent(2);
		studentService.deleteStudennt(student);
	}
	
	@Test
	public void TestUpdateStudent() {
		studentService.updateStudent(32, 4);
	}
	
	@Test
	public void TestUpdateFlagStudent() {
		studentService.updateFlagStudent(0, 4);
	}
	
	
	//?????????????????????????
	@Test
	public void TestAddTeacher() {
		Student student=studentService.findStudent(3);
		List<Student> list=new ArrayList<Student>();
		list.add(student);
		Teacher teacher=new Teacher();
		teacher.setTeacherName("lili");
		teacher.setId(6);
		teacher.setFlag(1);
		teacher.setStudent(list);
		teacherService.saveTeacher(teacher);
	}
	
	
	@Test
	public void testPageStudent() {
		//模拟从页面传输的数据
		String map = "{\"page\" : 1,\"pageSize\" : 5, \"filter\":{ \"filters\":[{ \"field\" : \"studentName\", \"value\":\"小一\"}]}}";
		Map searchParameters = new HashMap();
		try {
			searchParameters = objectMapper.readValue(map, new TypeReference<Map>() {
			});
		} catch (JsonParseException e) {
			log.error("JsonParseException{}:", e.getMessage());
		} catch (JsonMappingException e) {
			log.error("JsonMappingException{}:", e.getMessage());
		} catch (IOException e) {
			log.error("IOException{}:", e.getMessage());
		}

		Map mapStudent = studentService.getPage(searchParameters);

		 
		System.out.println(mapStudent.get("total"));

		System.out.println(mapStudent.get("students"));
	}
	
	/**
	 * 
	   * @Description 没有条件的分页查询
	   * @Author 范彬慧
	   * @CreatDate 2019年8月4日
	   * @UpdateUser 范彬慧
	   * @UpdateDate 2019年8月4日
	   * @throws  返回值void
	 */
	@Test
	public void PageStudent() {
		Pageable pageable=new PageRequest(0, 2);
		Page<Student> page=studentService.PageStudent(pageable);
		System.out.println(page.getContent());//得到数据集合列表
		System.out.println(page.getTotalElements());//得到总页数
		System.out.println(page.getTotalPages());//得到总条数

	}
	
}
