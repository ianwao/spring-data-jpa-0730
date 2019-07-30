package cn.com.taiji.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


/**
 * 一
 * ClassName: Father
 * @Description: TODO
 * @Author 范彬慧
 * @CreatDate 2019年7月30日
 */
@Entity
public class Father {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//主键自动递增
	private Integer fid;
	
	@Column(nullable=false,length=20,unique=true)//不为空，长度20，唯一
	private String  fname;
	
	@Column(length = 10) //长度10
	private Integer age;
	
	@JoinColumn(name="Father_id")  //映射外键列的名称
	//使用@OneToMany的fetch属性来修改默认的加载策略
	//使用@OneToMany的cascade属性来修改默认的删除策略
	@OneToMany(fetch=FetchType.EAGER,cascade= {CascadeType.REMOVE})  //映射·一对多的关联关系
	private Set<Son> son=new HashSet<>();
	
	public Set<Son> getSon() {
		return son;
	}
	public void setSon(Set<Son> son) {
		this.son = son;
	}
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
