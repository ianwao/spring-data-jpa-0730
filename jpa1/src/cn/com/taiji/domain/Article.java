package cn.com.taiji.domain;



import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


/**
 * 
 * ClassName: Article
 * @Description: TODO
 * @Author 范彬慧
 * @CreatDate 2019年7月29日
 * 
 * @Transient表示该属性并非一个到数据库表的字段的映射，ORM框架将忽略该属性，ORM框架默认注解为@Basic
 */
@Entity  //持久化类
@Table(name="article")  //表名，可以不指定表明，不指定的话表明默认为类名
public class Article {
    @Id   //映射主键，也可以卸载属性的getter方法之前
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 生成主键的策略， IDENTITY：自增长策略，AUTO：jpa自动选择合适的策略，TABLE：通过表产生主键，....
    @Column(name = "id", nullable = false)  //列名
    private Integer id;
    
    @Column(nullable = false, length = 50) // 映射为字段，nullable：值不能为空，长度为50
    private String title;
    
    @Lob  // 大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch = FetchType.LAZY) // 懒加载
    @Column(nullable = false) // 映射为字段，值不能为空
    private String content;//文章全文内容
    
    //@Temporal：在进行属性映射的时候用来调整精度
    @Temporal(TemporalType.TIMESTAMP)//TIMESTAMP表示精确到秒
    private Date createdTime;//创建时间，精确到秒
    
   @Temporal(TemporalType.DATE) //DATE表示精确到天
    private Date birth;//生日，精确到日
   
   //Customer
   //映射单向多对一（n-1）的关联关系
   //使用@ManyToOne来映射多对一的关联关系，@JoinColumn 来映射外键
   //可以使用@ManyToOne的fech属性来修改默认的关联属性的加载策略
   @JoinColumn(name="customer_id")  //外键列的列名
   @ManyToOne(fetch=FetchType.LAZY)  //多对一  使用懒加载的方式
//   @Cascade(value= {CascadeType.ALL}) //设定级联关系
   private Customer customer; 

    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
    
}
