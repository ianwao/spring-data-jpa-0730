package com.ianw.springdatajpa.repository;

import com.ianw.springdatajpa.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

/**
 * ClassName: StudentRepository <br/>
 * Description: <br/>
 * date: 2019/7/31 16:46<br/>
 *
 * @author 72733<br />
 * @since JDK 1.8
 */

public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {

    @Modifying
    @Query("update Student s set s.name =?1, s.age=?2,s.studentNum=?3,s.className=?4 where s.id=?5")
    void updateStudent(String name,int age,String studnetNum,String className,int id);

    @Modifying
    @Query("update Student s set s.flag =0 where s.id=?1")
    void delStudent(int id);

    @Query("select s from Student s where s.flag = 1")
    List<Student> findAllNotDelStudent();




}
