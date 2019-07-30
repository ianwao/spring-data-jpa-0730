package cn.com.taiji.domain;


import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQuery(name="people.findAll",query="select p from People p")
public class People {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //主键自增长策略
	@Column(name = "id", nullable = false)     //主键，不能为空
	private Integer id;// id
	
	@Column(name = "name", nullable = true, length = 20)    //名字，可以为空，长度20
	private String name;// 姓名
	
	@Column(name = "sex", nullable = true, length = 1)   //名字，可以为空，长度1
	private String sex;// 性别
	
	//将fecht设置为LAZY来修改加载策略
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY) // People是关系的维护端（拥有者），当删除 people，会级联删除 address
	//如需要在当前数据表中添加外键需要使用@JoinColumn来映射外键，注意：一对一关联关系，需要添加unique=true来限制外键不能重复
	@JoinColumn(name = "address_id", referencedColumnName = "id") // people中的address_id字段参考address表中的id字段，
	private Address address;// 地址

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}


	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
