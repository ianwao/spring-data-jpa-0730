package com.ianw.springdatajpa;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ianw.springdatajpa.domain.Dept;
import com.ianw.springdatajpa.domain.User;
import com.ianw.springdatajpa.service.DeptService;
import com.ianw.springdatajpa.service.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringdatajpaApplicationTests {


    private final static Logger LOGGER= LoggerFactory.getLogger(SpringdatajpaApplicationTests.class);

    @Autowired
    DeptService deptService;

    @Autowired
    UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    /*dept插入数据*/
    @Test
    public void addDept() {
        for (int i = 0; i < 9; i++) {
            Dept dept = new Dept();
            dept.setDeptName("机构" + i);
            dept.setFlag(1);
            deptService.saveDept(dept);
        }
    }

    /*dept插入数据*/
    @Test
    public void addUser() {
        for (int i=0;i<9;i++){
            User user = new User();
            user.setUserName("小明"+i);
            user.setFlag(1);
            user.setLoginName("登录"+i);
            userService.saveUser(user);
        }
    }

    /*更新数据*/
    @Test
    public void updateDept(){
        Dept dept = new Dept();
        dept.setId(1);
        dept.setDeptName("机构aaa");
        dept.setFlag(1);
        deptService.updateDept(dept);
    }

    /*查询全部*/
    @Test
    public void queryAll(){
        List<Dept> list=deptService.findAll();
        for (Dept d:list
        ) {
            System.out.println(d.toString());
        }
    }

    @Test
    public void findByname(){
        List<User> list = userService.findByUsernameAndLoginname("小明1","登录1");
        for (User d:list
        ) {
            System.out.println(d.toString());
        }
    }

    @Test
    public void findDpetByMsg(){
        List<Dept> list = deptService.findByMsg(1);
        for (Dept d:list
             ) {
            System.out.println(d.toString());
        }
    }

    @Test
    public void findFirstByOrderByUserNameAsc(){
        User user = userService.findFirstByOrderByUserNameAsc();
        System.out.println(user.toString());
    }

    @Test
    public void queryFirst10ByUserName(){
        Page<User> userPage = userService.queryFirst10ByUserName();
        for (User d:userPage
        ) {
            System.out.println(d.toString());
        }
    }

    /*Dept分页条件查询*/
    @Test
    public void testpageDept(){
        String map = "{\"page\" : 1,\"pageSize\" : 5, \"filter\":{ \"filters\":[{ \"field\" : \"deptName\", \"value\":\"机构8\"}]}}";
        Map searchParameters = new HashMap();
        try {
            searchParameters = objectMapper.readValue(map, new TypeReference<Map>() {
            });
        } catch (JsonParseException e) {
            LOGGER.error("JsonParseException{}:", e.getMessage());
        } catch (JsonMappingException e) {
            LOGGER.error("JsonMappingException{}:", e.getMessage());
        } catch (IOException e) {
            LOGGER.error("IOException{}:", e.getMessage());
        }
        Map mapDept = deptService.getPage(searchParameters);
        /*打印总数*/
        System.out.println(mapDept.get("total"));
        /*打印Dept信息*/
        System.out.println(mapDept.get("depts"));
    }

    /*user分页条件查询*/
    @Test
    public void testpageUser(){
        String map = "{\"page\" : 1,\"pageSize\" : 5, \"filter\":{ \"filters\":[{ \"field\" : \"userName\", \"value\":\"小明8\"}]}}";
        Map searchParameters = new HashMap();
        try {
            searchParameters = objectMapper.readValue(map, new TypeReference<Map>() {
            });
        } catch (JsonParseException e) {
            LOGGER.error("JsonParseException{}:", e.getMessage());
        } catch (JsonMappingException e) {
            LOGGER.error("JsonMappingException{}:", e.getMessage());
        } catch (IOException e) {
            LOGGER.error("IOException{}:", e.getMessage());
        }
        Map mapUser = userService.getPage(searchParameters);
        /*打印总数*/
        System.out.println(mapUser.get("total"));
        /*打印User信息*/
        System.out.println(mapUser.get("users"));
    }

}

