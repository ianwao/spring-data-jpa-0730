//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package cn.com.taiji.zuoye2;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQuery(
		name = "Address.findAll",
		query = "select a from Address a"
)
public class Address {
	@Id
	@GeneratedValue(
			strategy = GenerationType.IDENTITY
	)
	@Column(
			name = "id",
			nullable = false
	)
	private Integer id;
	@Column(
			name = "address",
			nullable = true,
			length = 100
	)
	private String address;
	@OneToOne(
			mappedBy = "address",
			cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE},
			optional = false
	)
	private People people;

	public Address() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public People getPeople() {
		return this.people;
	}

	public void setPeople(People people) {
		this.people = people;
	}
}
