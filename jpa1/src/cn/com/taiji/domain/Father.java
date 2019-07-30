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
 * һ
 * ClassName: Father
 * @Description: TODO
 * @Author �����
 * @CreatDate 2019��7��30��
 */
@Entity
public class Father {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//�����Զ�����
	private Integer fid;
	
	@Column(nullable=false,length=20,unique=true)//��Ϊ�գ�����20��Ψһ
	private String  fname;
	
	@Column(length = 10) //����10
	private Integer age;
	
	@JoinColumn(name="Father_id")  //ӳ������е�����
	//ʹ��@OneToMany��fetch�������޸�Ĭ�ϵļ��ز���
	//ʹ��@OneToMany��cascade�������޸�Ĭ�ϵ�ɾ������
	@OneToMany(fetch=FetchType.EAGER,cascade= {CascadeType.REMOVE})  //ӳ�䡤һ�Զ�Ĺ�����ϵ
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
