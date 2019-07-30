package cn.com.taiji.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.JoinColumn;

import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name="user")  //ע�⣺���ݿ��б���������user����Ȼ���ݿ����ɱ��ʧ�ܣ��²��������ݿ���û�����user���ظ�
@NamedQuery(name = "User.findAll", query = "select u from User u ")
@Entity
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 20, unique = true)
    private String username; // �û��˺ţ��û���¼ʱ��Ψһ��ʶ
    @Column(length = 100)
    private String password; // ��¼ʱ����
    
    //ʹ��@JoinTable��ӳ���м��
    //nameָ���м�������
    //joinColumnsӳ�䵱ǰ�����ڵı����м���е����
    //inverseJoinColumnsӳ��������������м������
    //referencedColumnsָ������й�����ǰ�����һ��
    @ManyToMany
    @JoinTable(name = "user_authority",joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "authority_id"))//inverseJoinColumns��ʾ�����Ķ��������ݱ�������
    //1����ϵά���ˣ������Զ��ϵ�İ󶨺ͽ��
    //2��@JoinTableע���name����ָ������������֣�joinColumnsָ����������֣���������ϵά����(User)
    //3��inverseJoinColumnsָ����������֣�Ҫ�����Ĺ�ϵ��ά����(Authority)
    //4����ʵ���Բ�ʹ��@JoinTableע�⣬Ĭ�����ɵĹ���������Ϊ�������+�»���+�ӱ������
    //������Ϊuser_authority
    //������������������������+�»���+�����е���������,��user_id
    //�������ӱ������������������ڹ�����������+�»���+�ӱ����������,��authority_id
    //������ǹ�ϵά���˶�Ӧ�ı��ӱ���ǹ�ϵ��ά���˶�Ӧ�ı�
    private List<Authority> authorityList=new ArrayList<>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Authority> getAuthorityList() {
		return authorityList;
	}
	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}

    
    
}
