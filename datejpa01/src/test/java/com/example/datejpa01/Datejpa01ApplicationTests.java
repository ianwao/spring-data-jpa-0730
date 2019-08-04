package com.example.datejpa01;

import com.example.datejpa01.domain.Dept;
import com.example.datejpa01.domain.Director;
import com.example.datejpa01.domain.Movie;
import com.example.datejpa01.domain.User;
import com.example.datejpa01.service.DeptService;
import com.example.datejpa01.service.MovieService;
import com.example.datejpa01.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.rmi.runtime.Log;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest

public class Datejpa01ApplicationTests {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    UserService userService;
    @Autowired
    DeptService deptService;

    private final Logger log = LoggerFactory.getLogger(Datejpa01ApplicationTests.class);





      // @Test
      //public void test(){
      //  Dept dept = new Dept();
      //  dept.setDeptName("mm");
      //  List<Dept> list =new ArrayList<>();
      //  list.add(dept);
       // User user=new User();
       // user.setName("阿明");

      // user.setFlag(1);
      //  userService.saveUser(user);

   // }
        @Ignore
        @Test
        //保存dept
           public void  test01() {
            for (int i = 0; i < 9; i++) {
                Dept dept = new Dept();
                dept.setDeptName("机构" + 1);
                dept.setFlag(1);
                deptService.saveDept(dept);
            }
        }


           //查询所有depts
            @Ignore
            @Test
            public void test02(){
                List<Dept> dlist=deptService.findAllDepts();
                log.info(dlist.toString());
            }


            //根据id查询部门名称（deptName）
     @Ignore
     @Test
     public void test03() {

        Optional<Dept> name=deptService.findById(1);
        log.info(name+"");
     }


           //根据id将部门名称修改为新传入的部门名称
     @Ignore
     @Test
    public void test04() {
        String deptName="NO机构";
        int id=1;
        deptService.updateDeptNameById(deptName, id);
    }

           //根据id逻辑删除，即将flag=0
    @Ignore
    @Test
    public void test05() {

        int id=1;
        deptService.updateFlagById(id);
    }
          //插入数据到user_info表中
    @Ignore
    @Test
    public void test06() {
        for(int i=0;i<9;i++) {
            User user=new User();
            user.setUserName("用户"+i);
            user.setFlag(1);
            userService.saveUser(user);
        }
    }

          //查找flag=1的,且通过deptIndex升序排序后
    @Ignore
    @Test
    public void test07() {

        List<Dept> dlist=deptService.findByFlagAndParentIsNullOrderByDeptIndexAsc(1);
        Iterator<Dept> it=dlist.iterator();
        while(it.hasNext()) {
            Dept dept=it.next();
            System.out.println(dept.getDeptName());
        }

    }

        //按照id降序排序后查找第一个Dept对象
    public void test08() {
        Dept dept=deptService.findFirstByOrderByIdDesc();
        System.out.println(dept.getDeptName());

    }







         //List<Dept> list =deptService.findByFlag();
         //list<Dept> list =deptService.findDeptTree(1);
         //System.out.println(list.size);
         //修改
         //deptService.updateDeptById(1);

    //}

             //按条件分页查询用户08
    @Test
    public void testPageUser() {
       String map = "{\"page\" : 1,\"pageSize\" : 5, \"filter\":{ \"filters\":[{ \"field\" : \"userName\", \"value\":\"用户08\"}]}}";
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

        Map mapDept = deptService.getPage(searchParameters);


        System.out.println(mapDept.get("total"));
        System.out.println(mapDept.get("users"));
    }

       //按条件分页查询Depts  机构8
    @Ignore
     @Test
    public void testPage() {
    String map = "{\"page\" : 1,\"pageSize\" : 5, \"filter\":{ \"filters\":[{ \"field\" : \"deptName\", \"value\":\"机构8\"}]}}";
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

    Map mapUser = deptService.getPage(searchParameters);


    System.out.println(mapUser.get("total"));

    System.out.println(mapUser.get("depts"));
    }




         //维护用户和机构的关系
    public void test10() {

        List<Dept> ld = new ArrayList<Dept>();

        Optional<Dept> odept = deptService.findById(2);
        Dept dept = odept.get();
        dept.setUsers(null);
        ld.add(dept);
        User user = new User();
        user.setDepts(ld);
        user.setFlag(1);
        user.setUserName("用户测试");
        user.setId(2);
        userService.saveUser(user);
    }


          //课下作业

    // 3.spring-data-jpa 自定义类，实现增删改查，查:(1.全部信息的查询，2.根据条件分页查询，3.查询全部的未删除信息)



          @Autowired
          MovieService movieService;
      //添加电影和导演
    @Ignore
    @Test
    public void testSaveBook01() {
        for(int i=0;i<9;i++) {
            Director director=new Director();
            director.setName("导演"+i);
            director.setFlag(1);

            Movie movie = new Movie();
            movie.setMovieIndex("索引"+i);
            movie.setMovieName("电影"+i);
            movie.setDate("2019-07-31");
            movie.Director(director);
            movie.setFlag(1);
            movieService.save(movie);

        }
    }
     @Ignore
     @Test
    //逻辑删除id为3的电影
    public void testDeleteBook02() {
        int id=3;
        movieService.deleteMovie(id);

    }

    @Ignore
    @Test
     //根据电影id更改电影名字
    public void testupdateById03() {
        int id=2;
        String movieName="更改电影";
        movieService.updateByDirectorId(movieName,id);
    }

     @Ignore
     @Test
    // 查询所有电影
    public void testFindAll04() {
        List<Movie> list=movieService.findAll();
        Iterator<Movie> it=list.iterator();
        while(it.hasNext()) {
            Movie book=it.next();
            System.out.println(book.getMovieName()+"-----");
        }
    }
    @Ignore
    @Test
    //查询所有未删除的电影
    public void testFindAllNamedQuery05() {
        List<Movie> list=movieService.findAllNamedQuery();
        Iterator<Movie> it=list.iterator();
        while(it.hasNext()) {
            Movie movie=it.next();
            System.out.println(movie.getMovieName()+"-----");
        }
    }


    @Ignore
    @Test
    //按条件分页查询  1、  电影4   2、  DESC顺序
    public void testMoviePage06() {
        String map = "{\"page\" : 1,\"pageSize\" : 5, \"filter\":{ \"filters\":[{ \"field\" : \"movieName\", \"value\":\"电影4\"},"
                + "{\"field\" : \"movieIndex\", \"value\":\"索引5\"}]},\"sort\":[{ \"field\" : \"movieName\", \"dir\":\"DESC\"}] }";
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

        Map mapMovie = movieService.getPage(searchParameters);


        System.out.println(mapMovie.get("total"));

        System.out.println(mapMovie.get("movies"));
    }


}
