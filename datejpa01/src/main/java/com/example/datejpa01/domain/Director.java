package com.example.datejpa01.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Director {
	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	@Column(name = "id", nullable = false)
	private Integer id; // id
	@Column(nullable = false, length = 20)
	private String name;// 姓名
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<Movie> movieList = new ArrayList<>();// 电影
	
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
	public List<Movie> getBookList() {
		return movieList;
	}
	public void setBookList(List<Movie> bookList) {
		this.movieList = movieList;
	}
	

}
