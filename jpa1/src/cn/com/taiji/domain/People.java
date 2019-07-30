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
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //��������������
	@Column(name = "id", nullable = false)     //����������Ϊ��
	private Integer id;// id
	
	@Column(name = "name", nullable = true, length = 20)    //���֣�����Ϊ�գ�����20
	private String name;// ����
	
	@Column(name = "sex", nullable = true, length = 1)   //���֣�����Ϊ�գ�����1
	private String sex;// �Ա�
	
	//��fecht����ΪLAZY���޸ļ��ز���
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY) // People�ǹ�ϵ��ά���ˣ�ӵ���ߣ�����ɾ�� people���ἶ��ɾ�� address
	//����Ҫ�ڵ�ǰ���ݱ�����������Ҫʹ��@JoinColumn��ӳ�������ע�⣺һ��һ������ϵ����Ҫ���unique=true��������������ظ�
	@JoinColumn(name = "address_id", referencedColumnName = "id") // people�е�address_id�ֶβο�address���е�id�ֶΣ�
	private Address address;// ��ַ

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
