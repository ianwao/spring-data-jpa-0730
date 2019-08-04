package cn.com.taiji.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name="book_name",length=30)
	private String bookName;
	@Column(scale=2)
	private Float price;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="author_id")
	private Author author;
	@Column(name="date")
	private String date;
	@Column(name = "book_index")
	private String bookIndex;
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
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBookIndex() {
		return bookIndex;
	}
	public void setBookIndex(String bookIndex) {
		this.bookIndex = bookIndex;
	}
	

	

}
