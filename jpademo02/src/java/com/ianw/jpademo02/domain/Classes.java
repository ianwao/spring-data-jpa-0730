package com.ianw.jpademo02.domain;/**
 * ClassName: Classes <br/>
 * Description: <br/>
 * date: 2019/7/30 9:31<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

/**
 *@program: jpademo02
 *@description: 班级
 *@author: tao xujie
 *@create: 2019-07-30 09:31
 */

@Entity
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid", nullable = false)
    private Integer cid;

    @Column(name = "classname",nullable = false, length = 10) // 映射为字段，值不能为空
    private String classname;

    @OneToMany(mappedBy = "classes",cascade = CascadeType.ALL) // People是关系的维护端，当删除 people，会级联删除 address
    private List<Student> student;

    public Classes() {
    }



    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }
}