package cn.com.taiji;

import cn.com.taiji.domain.Dept;
import cn.com.taiji.domain.Teacher;
import cn.com.taiji.domain.User;
import cn.com.taiji.service.DeptService;
import cn.com.taiji.service.TeacherService;
import cn.com.taiji.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaijiApplicationTests {
    protected static final Logger log = LoggerFactory.getLogger(TaijiApplicationTests.class);

    @Autowired
    UserService userService;
    @Autowired
    DeptService deptService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test1(){
        //添加
//        for (int i=0;i<9;i++){
//            Dept dept = new Dept();
//            dept.setDeptName("机构"+i);
//            dept.setFlag(1);
//            deptService.saveDept(dept);
//        }
        //查询
//        List<Dept> list = deptService.findAllDepts();
        List<Dept> list= deptService.findDeptTree();
//        List<Dept> list = deptService.findByFlag(1);
        System.out.println(list.size());
        //修改
//        deptService.updateDeptById(1);
        //删除
        Dept dept = new Dept();
        deptService.delectDeptByID(dept);
    }

//    @Test
//    public void testPage() {
//        String map = "{\"page\" : 1,\"pageSize\" : 5, \"filter\":{ \"filters\":[{ \"field\" : \"deptName\", \"value\":\"机构8\"}]}}";
//        Map searchParameters = new HashMap();
//        try {
//            searchParameters = objectMapper.readValue(map, new TypeReference<Map>() {
//            });
//        } catch (JsonParseException e) {
//            log.error("JsonParseException{}:", e.getMessage());
//        } catch (JsonMappingException e) {
//            log.error("JsonMappingException{}:", e.getMessage());
//        } catch (IOException e) {
//            log.error("IOException{}:", e.getMessage());
//        }
//
//        Map mapDept = deptService.getPage(searchParameters);
//
//
//        System.out.println(mapDept.get("total"));
//
//        System.out.println(mapDept.get("depts"));
//    }
//    @Ignore
//    @Test
//    public void testUserPage() {
//        String map = "{\"page\" : 1,\"pageSize\" : 5, \"filter\":{ \"filters\":[{ \"field\" : \"userName\", \"value\":\"XiaoLi\"}]}}";
//        Map searchParameters = new HashMap();
//        try {
//            searchParameters = objectMapper.readValue(map, new TypeReference<Map>() {
//            });
//        } catch (JsonParseException e) {
//            log.error("JsonParseException{}:", e.getMessage());
//        } catch (JsonMappingException e) {
//            log.error("JsonMappingException{}:", e.getMessage());
//        } catch (IOException e) {
//            log.error("IOException{}:", e.getMessage());
//        }
//
//        Map mapUser = userService.getPage(searchParameters);
//
//
//        System.out.println(mapUser.get("total"));
//
//        System.out.println(mapUser.get("users"));
//    }

    @Test
    public void test2(){

        Optional<Dept> dept = deptService.findById(3);

        List<Dept> deptList = new ArrayList<>();
//        deptList.add(dept);
        User user = new User();
        user.setUserName("MengFe");
        user.setPassword("789");
        user.setFlag(1);
        user.setDepts(deptList);
        userService.saveUser(user);
    }

    @Test
    public void test3(){
        //添加
//        Teacher teacher = new Teacher();
//        teacher.setName("张三");
//        teacher.setCourse("数学");
//        teacher.setPost("教师");
//        teacher.setFlag(1);
//        teacherService.saveTeacher(teacher);
        //修改
//        teacherService.updateTeacher(2);
        //删除
//        teacherService.delTeacher(3);
        //查询
        List<Teacher> list = new ArrayList<>();
//        list = teacherService.selectTeacher();
        list = teacherService.selectTeacherByFlag();
        System.out.println(list.size());
    }

    @Test
    public void testTeacherPage() {
        String map = "{\"page\" : 1,\"pageSize\" : 5, \"filter\":{ \"filters\":[{ \"field\" : \"course\", \"value\":\"数学\"}]},\"sort\":[{\"field\":\"id\",\"dir\":\"desc\"}]}";
        //,"sort":[{"field":"id","dir":""}]
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

        Map mapTeacher = teacherService.getPage(searchParameters);

        System.out.println(mapTeacher.get("total"));
        System.out.println(mapTeacher.get("teachers"));
    }
}
