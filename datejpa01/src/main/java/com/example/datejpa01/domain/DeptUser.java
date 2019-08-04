package com.example.datejpa01.domain;

/**
 * ClassName: DeptUser
 *
 * @Description: 用户机构中间表
 * @Author liming
 * @CreatDate 2019年8月
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="dept_user")
@NamedQuery(name="DeptUser.findAll", query="SELECT d FROM DeptUser d")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class DeptUser implements Serializable {

    private static final long serialVersionUID = 5680225687620614164L;
    @EmbeddedId
    private DeptUserPK id;

    public DeptUser() {
    }

    public DeptUserPK getId() {
        return this.id;
    }

    public void setId(DeptUserPK id) {
        this.id = id;
    }

}