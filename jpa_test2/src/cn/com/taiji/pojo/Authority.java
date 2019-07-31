package cn.com.taiji.pojo;

import java.util.List;

import javax.persistence.*;


@Entity
@NamedQueries({
		@NamedQuery(name = "Authority.finAll",query = "select a from Authority a"),
		@NamedQuery(name = "Authority.findOne",query = "select a from Authority a where a.id=:id")
})
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name; //权限名
    @ManyToMany(mappedBy = "authorityList")
    private List<User> userList;
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
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
