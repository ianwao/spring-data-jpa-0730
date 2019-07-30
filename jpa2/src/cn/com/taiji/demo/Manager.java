package cn.com.taiji.demo;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="JPA_MANAGERS")
public class Manager {
	@GeneratedValue
	@Id
	private Integer id;
	@Column(name="MGR_NAME")
	private String mgrName;
	
	//对于不维护关联关系（没有外键的一方），使用@mappedBy来映射，建议设置mappedBy="mgr"
	//若两边都维护关联关系，会无谓的多出Update语句
	@OneToOne(mappedBy="mgr")
	private Department dept;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMgrName() {
		return mgrName;
	}
	public void setMgrName(String mgrName) {
		this.mgrName = mgrName;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	
}
