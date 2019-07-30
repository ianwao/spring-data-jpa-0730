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
    
    //nullable=false是这个字段在保存时必需有值，不能还是null值就调用save去保存入库;
    @Column(name = "address", nullable = true, length = 100)
    private String address;//地址
    
    //如果不需要根据Address级联查询People，可以注释掉
    //对于不维护关联关系的一方，使用@OneToOne来进行映射，建议设置mappedBy=true
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