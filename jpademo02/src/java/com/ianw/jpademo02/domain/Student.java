package com.ianw.jpademo02.domain;/**
 * ClassName: Student <br/>
 * Description: <br/>
 * date: 2019/7/30 9:33<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */

import javax.persistence.*;

/**
 *@program: jpademo02
 *@description: 学生
 *@author: tao xujie
 *@create: 2019-07-30 09:33
 */
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    @Column(name = "sid", nullable = false)
    private Integer sid;

    @Column(name = "studentname",nullable = false,length = 10)
    private String studnetname;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=true)
    @JoinColumn(name = "Classes_cid")
    private Classes classes;

    public Student() {
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getStudnetname() {
        return studnetname;
    }

    public void setStudnetname(String studnetname) {
        this.studnetname = studnetname;
    }

}