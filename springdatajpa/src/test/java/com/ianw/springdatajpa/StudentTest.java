package com.ianw.springdatajpa;/**
 * ClassName: StudentTest <br/>
 * Description: <br/>
 * date: 2019/7/31 16:44<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ianw.springdatajpa.domain.Student;
import com.ianw.springdatajpa.service.StudentService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@program: springdatajpa
 *@description: 学生表测试类
 *@author: tao xujie
 *@create: 2019-07-31 16:44
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest {

    private final static Logger  log = LoggerFactory.getLogger(StudentTest.class);

    @Autowired
    StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Ignore
    @Test
    public void addStudent(){
        log.info("进入添加学生信息的方法");
        for (int i=0;i<10;i++){
            Student student = new Student();
            student.setName("学生"+i);
            student.setAge(15+i);
            student.setFlag(1);
            student.setStudentNum("2019000"+i);
            student.setClassName("高一"+i+"班");
            studentService.addStudent(student);
        }
        log.info("方法执行完毕，正在退出该方法");
    }

    @Ignore
    @Test
    public void updateStudent(){
        log.info("进入修改学生信息的方法");
        Student student = new Student();
        student.setId(1);
        student.setName("小明");
        student.setAge(15);
        student.setStudentNum("20190001");
        student.setClassName("高二1班");
        studentService.updateStudent(student);
        log.info("离开修改学生信息的方法");
    }

    //@Ignore
    @Test
    public void delStudent(){
        log.info("进入删除学生信息的方法");
        int id = 1;
        studentService.delStudent(id);
        log.info("离开删除学生信息的方法");
    }

    //@Ignore
    @Test
    public void queryAll(){
        log.info("进入查询学生信息的方法");
        List<Student> list = studentService.queryAll();
        for (Student s:list
             ) {
            System.out.println(s.toString());
        }
        log.info("离开查询学生信息的方法");
    }

    @Ignore
    @Test
    public void queryAllByFlag(){
        log.info("进入查询学生信息的方法");
        List<Student> list = studentService.queryAll();
        for (Student s:list
        ) {
            System.out.println(s.toString());
        }
        log.info("离开查询学生信息的方法");
    }

    @Ignore
    @Test
    public void queryAllNotDelete(){
        log.info("进入查询未删除学生信息的方法");
        List<Student> list = studentService.queryAllNotDelete();
        for (Student s:list
        ) {
            System.out.println(s.toString());
        }
        log.info("离开查询未删除学生信息的方法");
    }

    @Test
    public void testpageUser(){
        String map = "{\"page\" : 1,\"pageSize\" : 5, \"filter\":{ \"filters\":[{ \"field\" : \"name\", \"value\":\"学生1\"},{ \"field\" : \"studentNum\", \"value\":\"20190001\"}]}}";
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
        Map mapUser = studentService.queryAllByPage(searchParameters);
        /*打印总数*/
        System.out.println(mapUser.get("total"));
        /*打印User信息*/
        System.out.println(mapUser.get("students"));
    }



}