package cn.com.taiji.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * ��
 * ClassName: Son
 * @Description: TODO
 * @Author �����
 * @CreatDate 2019��7��30��
 */
@Entity
public class Son {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //�����Զ�����
	private Integer sid;
	
	@Column(nullable=false,length=20,unique=true)//��Ϊ�գ�����20��Ψһ
	private String sname;
	
	@Column(length = 10) //����10
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
