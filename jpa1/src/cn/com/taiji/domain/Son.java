package cn.com.taiji.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 多
 * ClassName: Son
 * @Description: TODO
 * @Author 范彬慧
 * @CreatDate 2019年7月30日
 */
@Entity
public class Son {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //主键自动自增
	private Integer sid;
	
	@Column(nullable=false,length=20,unique=true)//不为空，长度20，唯一
	private String sname;
	
	@Column(length = 10) //长度10
	private Integer sage;
	
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Integer getSage() {
		return sage;
	}
	public void setSage(Integer sage) {
		this.sage = sage;
	}
	
}
