package cn.com.taiji.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Author {
	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	@Column(name = "id", nullable = false)
	private Integer id; // id
	@Column(nullable = false, length = 20)
	private String name;// 姓名
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<Book> bookList = new ArrayList<>();// 书籍
	
	private Integer flag;
	
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
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
	public List<Book> getBookList() {
		return bookList;
	}
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	

}
