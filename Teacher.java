package cn.com.taiji.domain;

import javax.persistence.*;

@Entity
@Table(name = "teacher_info")
@NamedQuery(name = "Teacher.finAllTeacher", query = "select t from Teacher t where t.flag=1")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "t_name")
    private String name;
    @Column(name = "t_course")
    private String course;
    @Column(name = "t_post")
    private String post;

    private Integer flag;

    public Teacher() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
