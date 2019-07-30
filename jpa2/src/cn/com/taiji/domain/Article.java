package cn.com.taiji.domain;

import java.beans.Transient;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="article")
@NamedQuery(name="Article.search",query="select a.title from Article a where a.author.id=(select ac.id from Author ac where ac.id=2)")
//@NamedQuery(name="Article.search",query="select a.title from Article a where a.id=1")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(nullable = false, length = 50) // 映射为字段，值不能为空
    private String title;
    @Lob  // 大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch = FetchType.LAZY) // 懒加载
    @Column(nullable = false) // 映射为字段，值不能为空
    private String content;//文章全文内容
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=true
    		)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    //@JoinColumn(name="author_id")//设置在article表中的关联字段(外键)
    private Author author;//所属作者
    

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
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	@Transient//不需要映射为数据表的一列
	public String getInfo() {
		return title+";"+content;
	//@Temporal(TemporalType.TIMESTAMP)	//精确到秒
	//@Temporal(TemporalType.DATE)	//精确到天
	}
    
}
