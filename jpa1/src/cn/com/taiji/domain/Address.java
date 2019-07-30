package cn.com.taiji.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="address.findAll",query="select a from Address a")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;//id
    
    //nullable=false������ֶ��ڱ���ʱ������ֵ�����ܻ���nullֵ�͵���saveȥ�������;
    @Column(name = "address", nullable = true, length = 100)
    private String address;//��ַ
    
    //�������Ҫ����Address������ѯPeople������ע�͵�
    //���ڲ�ά��������ϵ��һ����ʹ��@OneToOne������ӳ�䣬��������mappedBy=true
//    @OneToOne(mappedBy = "address", cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
//    private People people;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	 
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    
    
    
}