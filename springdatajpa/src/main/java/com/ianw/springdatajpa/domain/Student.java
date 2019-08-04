package com.ianw.springdatajpa.domain;/**
 * ClassName: Student <br/>
 * Description: <br/>
 * date: 2019/7/31 16:16<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 *@program: springdatajpa
 *@description: 学生表
 *@author: tao xujie
 *@create: 2019-07-31 16:16
 */
@Entity
@Table(name = "student_info")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "studnet_num")
    private String studentNum;

    @Column(name = "age")
    private Integer age;

    @Column(name = "name")
    private String name;

    @Column(name = "class_name")
    private String className;

    @Column(name = "state")
    private String state;//状态（1--在校，0--离校）

    private int flag;

    @Column(name = "student_index")
    private Integer studentIndex; // 排序索引

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentNum='" + studentNum + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", state='" + state + '\'' +
                ", flag=" + flag +
                ", studentIndex=" + studentIndex +
                '}';
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Integer getStudentIndex() {
        return studentIndex;
    }

    public void setStudentIndex(Integer studentIndex) {
        this.studentIndex = studentIndex;
    }
}