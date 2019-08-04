package com.ianw.springdatajpa.service;/**
 * ClassName: StudentService <br/>
 * Description: <br/>
 * date: 2019/7/31 16:49<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */

import com.ianw.springdatajpa.domain.Student;
import com.ianw.springdatajpa.domain.User;
import com.ianw.springdatajpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@program: springdatajpa
 *@description: 学生表业务层
 *@author: tao xujie
 *@create: 2019-07-31 16:49
 */

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    /*新增学生信息*/
    @Transactional(propagation = Propagation.REQUIRED)
    public void addStudent(Student student) {
        studentRepository.saveAndFlush(student);
    }

    /*修改学生信息*/
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateStudent(Student student) {
        studentRepository.updateStudent(student.getName(),student.getAge(),student.getStudentNum(),student.getClassName(),student.getId());
    }

    /*伪删除学生信息*/
    @Transactional(propagation = Propagation.REQUIRED)
    public void delStudent(int id) {
        studentRepository.delStudent(id);
    }


    /*查询全部学生信息*/
    public List<Student> queryAll() {
        List<Student> list = studentRepository.findAll();
        return list;
    }


    /*查询全部没有删除的学生信息*/
    public List<Student> queryAllNotDelete(){
        List<Student> list = studentRepository.findAllNotDelStudent();
        return list;
    }


    /*分页查询学生信息*/
    public Map queryAllByPage(Map searchParameters){
        Map map = new HashMap();
        int page = 0;
        int pageSize = 10;
        Page<Student> pageList;
        if (searchParameters != null && searchParameters.size() > 0 && searchParameters.get("page") != null) {
            page = Integer.parseInt(searchParameters.get("page").toString()) - 1;
        }
        if (searchParameters != null && searchParameters.size() > 0 && searchParameters.get("pageSize") != null) {
            pageSize = Integer.parseInt(searchParameters.get("pageSize").toString());
        }
        if (pageSize < 1)pageSize = 1;
        if (pageSize > 100)pageSize = 100;
        List<Map> orderMaps = (List<Map>) searchParameters.get("sort");
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        if (orderMaps != null) {
            for (Map m : orderMaps) {
                if (m.get("field") == null)
                    continue;
                String field = m.get("field").toString();
                if (!StringUtils.isEmpty(field)) {
                    String dir = m.get("dir").toString();
                    if ("DESC".equalsIgnoreCase(dir)) {
                        orders.add(new Sort.Order(Sort.Direction.DESC, field));
                    } else {
                        orders.add(new Sort.Order(Sort.Direction.ASC, field));
                    }
                }
            }
        }
        PageRequest pageable;
        if (orders.size() > 0) {
            pageable = PageRequest.of(page, pageSize, Sort.by(orders));
        } else {
            Sort sort = new Sort(Sort.Direction.ASC, "studentIndex");
            pageable = PageRequest.of(page, pageSize, sort);
        }
        Map filter = (Map) searchParameters.get("filter");
        if (filter != null) {
            final List<Map> filters = (List<Map>) filter.get("filters");
            Specification<Student> spec = (Specification<Student>) (root, query, cb) -> {
                List<Predicate> pl = new ArrayList<Predicate>();
                for (Map f : filters) {
                    String field = f.get("field").toString().trim();
                    String value = f.get("value").toString().trim();

                    if (value != null && value.length() > 0) {
                        if ("name".equalsIgnoreCase(field)) {
                            pl.add(cb.like(root.<String>get(field), value + "%"));
                        } else if ("studentNum".equalsIgnoreCase(field)) {
                            pl.add(cb.like(root.<String>get(field), value + "%"));
                        }
                    }
                }
                // 查询出未删除的
                pl.add(cb.equal(root.<Integer>get("flag"), 1));
                return cb.and(pl.toArray(new Predicate[0]));
            };
            pageList = studentRepository.findAll(spec, pageable);
        } else {
            Specification<Student> spec = new Specification<Student>() {
                public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> list = new ArrayList<Predicate>();
                    // 查询出未删除的
                    list.add(cb.equal(root.<Integer>get("flag"), 1));
                    return cb.and(list.toArray(new Predicate[0]));
                }
            };
            pageList = studentRepository.findAll(spec, pageable);
        }
        map.put("total", pageList.getTotalElements());
        List<Student> list = pageList.getContent();

        map.put("students", list);
        return map;
    }

}