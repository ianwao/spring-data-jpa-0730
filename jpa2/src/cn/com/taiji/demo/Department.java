package cn.com.taiji.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="JPA_DEPARTMENT")
public class Department {
	
	@GeneratedValue
	@Id
	private Integer id;
	@Column(name="DEPT_NAME")
	private String deptName;
	//使用@onetoone来映射1-1关联关系，
	//若要在当前数据表中添加主键则需要使用@JoinColumn来进行映射,所以需要添加unique=true
	@JoinColumn(name="MGR_ID",unique=true)
	@OneToOne
	private Manager mgr;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Manager getMgr() {
		return mgr;
	}
	public void setMgr(Manager mgr) {
		this.mgr = mgr;
	}
	
	

}
